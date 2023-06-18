package orm.core;

import orm.annotation.Column;
import orm.annotation.Entity;
import orm.annotation.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManager<E> implements DbContext<E> {

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    private Field getId(Class entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .filter(e -> e.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity does not have primary key"));
    }

    private int getIdLast(String tableName, E entity, Field primaryKey) throws SQLException {
        String columnNamePK = primaryKey.getName();
        String sql = "SELECT " + columnNamePK + " FROM " + tableName + " ORDER BY " + columnNamePK + " DESC LIMIT 1;";

        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        if (resultSet.next()) {
            int id = resultSet.getInt(columnNamePK);
            return id;
        }
        return 0;
    }

    public boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field primaryKey = getId(entity.getClass());
        primaryKey.setAccessible(true);
        Object value = primaryKey.get(entity);

        if (value == null || (long) value <= 0) {
            return doInsert(entity, primaryKey);
        }

        return doUpdate(entity,primaryKey);
    }

    public void doCreate(Class<E> entityClass) throws SQLException {
        String tableName = getTableName(entityClass);
        String sql = String.format("CREATE TABLE %s ( id INT PRIMARY KEY AUTO_INCREMENT, %s);",
                tableName,
                getAllFieldsAndDataTypes(entityClass));

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }

    private String getAllFieldsAndDataTypes(Class<E> entityClass) {
        StringBuilder sb = new StringBuilder();
        Field[] fields = Arrays.stream(entityClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class) && !field.isAnnotationPresent(Id.class))
                .toArray(Field[]::new);

        Arrays.stream(fields).forEach(field -> sb.append(getNameAndDateTypeOfField(field)));

        return sb.substring(0, sb.length() - 2);
    }

    private String getNameAndDateTypeOfField(Field field) {
        final String fieldName = field.getAnnotation(Column.class).name();
        final Class<?> fieldType = field.getType();

        String sqlType;

        if (fieldType == int.class) {
            sqlType = "INT";
        } else if (fieldType == LocalDate.class) {
            sqlType = "DATE";
        } else {
            sqlType = "VARCHAR(255)";
        }
        return fieldName + " " + sqlType + ", ";
    }

    private boolean doUpdate(E entity, Field primaryKey) throws SQLException, IllegalAccessException {
        String tableName = getTableName(entity.getClass());
        String sqlQuery = "UPDATE " + tableName + " SET ";

        Field[] fields = entity.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                String columnName = field.getAnnotation(Column.class).name();
                field.setAccessible(true);
                Object value = field.get(entity);
                if (field.getType() == String.class || field.getType() == LocalDate.class) {
                    sqlQuery += columnName + " = " + "\"" + value + "\", ";
                } else {
                    sqlQuery += columnName + " = " + value + ", ";
                }
            }
        }
        sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2);
        sqlQuery += " WHERE id = " + primaryKey.get(entity) + ";";

        return connection.prepareStatement(sqlQuery).execute();
    }

    private boolean doInsert(E entity, Field primaryKey) throws IllegalAccessException, SQLException {
        String tableName = getTableName(entity.getClass());

        String fieldNamesWithoutId = getFieldNamesWithoutId(entity.getClass());
        String fieldValuesWithoutId = getFieldValuesWithoutId(entity);

        String sqlQuery = String.format("INSERT INTO %s(%s) VALUES (%s);",
                tableName,
                fieldNamesWithoutId,
                fieldValuesWithoutId);


        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        return preparedStatement.executeUpdate() == 1;

    }

    private String getFieldValuesWithoutId(E entity) {
        Class<?> entityClass = entity.getClass();
        return Arrays.stream(entityClass.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class) && !f.isAnnotationPresent(Id.class))
                .map(f -> {
                    f.setAccessible(true);
                    try {
                        Object value = f.get(entity);
                        return String.format("'%s'", value.toString());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.joining(", "));
    }

    private String getFieldNamesWithoutId(Class<?> entityClass) {
       return Arrays.stream(entityClass.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class) && !f.isAnnotationPresent(Id.class))
                .map(f -> f.getAnnotation(Column.class).name())
                .collect(Collectors.joining(", "));

    }


    private String getTableName(Class<?> entity){

        return entity.getAnnotation(Entity.class).name();
    }

    @Override
    public Iterable<E> find(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return find(table, null);
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String tableName = getTableName(table);
        String whereClause = where == null ? "" : " WHERE " + where;

        String sql = String.format("SELECT * FROM %s %s;",
                tableName,
                whereClause);

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<E> entities = new ArrayList<>();
        while (resultSet.next()) {
            E entity = createEntity(table, resultSet);
            entities.add(entity);
        }

        return entities;
    }

    @Override
    public E findFirst(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return findFirst(table, null);
    }

    @Override
    public E findFirst(Class<E> table, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String tableName = getTableName(table);
        String whereClause = where == null ? "" : " WHERE " + where;

        String sql = String.format("SELECT * FROM %s %s LIMIT 1;",
                tableName, whereClause);

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return createEntity(table, resultSet);
        }

        return null;
    }

    private E createEntity(Class<E> table, ResultSet resultSet) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        E entity = table.getDeclaredConstructor().newInstance();

        Arrays.stream(table.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .forEach(f -> {
                    try {
                        fillFieldData(entity, f, resultSet);
                    }catch (SQLException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });

        return entity;
    }

    private void fillFieldData(E entity, Field field, ResultSet resultSet) throws IllegalAccessException, SQLException {
        field.setAccessible(true);

        String columnName = field.getAnnotation(Column.class).name();
        Class<?> fieldType = field.getType();

        Object value;
        if (fieldType == int.class) {
            value = resultSet.getInt(columnName);
        } else if (fieldType == LocalDate.class) {
            String stringDate = resultSet.getString(columnName);
            value = LocalDate.parse(stringDate);
        } else {
            value = resultSet.getString(columnName);
        }

        field.set(entity, value);
    }
}
