import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

enum Utils {
    ;
    static Connection getSQLConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty(Constants.USER_KEY, Constants.USER_VALUE);
        properties.setProperty(Constants.PASS_KEY, Constants.PASS_VALUE);

        return DriverManager.getConnection(Constants.SQL_URL, properties);
    }
}
