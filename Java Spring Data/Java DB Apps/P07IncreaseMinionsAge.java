import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class P07IncreaseMinionsAge {
    private static final String UPDATE_MINIONS_AGE_AND_NAME = "update minions" +
            " set age = age + 1," +
            " name = lower(name)" +
            " where id = ?;";

    private static final String GET_MINIONS_AGE_AND_NAME = "select * from minions;";
    private static final String PRINT_FORMAT = "%s %d%n";

    public static void main(String[] args) throws SQLException {

        final Scanner scanner = new Scanner(System.in);

        final int[] minionsIDs = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();


        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement updateStatement = connection.prepareStatement(UPDATE_MINIONS_AGE_AND_NAME);
        for (int i = 0; i < minionsIDs.length; i++) {
            updateStatement.setInt(1, minionsIDs[i]);
            updateStatement.executeUpdate();
        }

        final PreparedStatement selectStatement = connection.prepareStatement(GET_MINIONS_AGE_AND_NAME);
        final ResultSet selectSet = selectStatement.executeQuery();

        while (selectSet.next()) {
           final String name = selectSet.getString(Constants.COLUMN_LABEL_NAME);
           final int age = selectSet.getInt(Constants.COLUMN_LABEL_AGE);

            System.out.printf(PRINT_FORMAT, name, age);
        }

        connection.close();
    }
}
