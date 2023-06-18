import entities.Department;
import entities.User;
import orm.MyConnector;
import orm.core.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException {


        Connection connection = MyConnector.createConnection("root", "111111", "orm_demo");

        EntityManager<User> entityManager = new EntityManager<>(connection);

        User user = new User("Pesho", 45, LocalDate.of(2021, 6, 20));

        entityManager.persist(user);

        User foundUser = entityManager.findFirst(User.class, "age > 30");

        EntityManager<Department> entityManager2 = new EntityManager<>(connection);

        entityManager2.doCreate(Department.class);
        System.out.println();
    }
}
