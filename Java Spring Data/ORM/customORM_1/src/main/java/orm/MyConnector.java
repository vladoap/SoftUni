package orm;

import orm.core.EntityManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnector {


    public static final String connectionString = "jdbc:mysql://localhost:3306/";

    public static Connection createConnection(String username, String password, String dbName) throws SQLException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);

//        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(connectionString + dbName, properties);

        return connection;
    }
}
