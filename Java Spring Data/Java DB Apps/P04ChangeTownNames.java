import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P04ChangeTownNames {
    private static final String UPDATE_TOWNS_BY_COUNTRY = "update towns" +
            " set name = upper(name)" +
            " where country = ?;";

    private static final String GET_TOWNS_BY_COUNTRY = "select name from towns" +
            " where country = ?;";
    private static final String AFFECTED_TOWNS_FORMAT = "%d town names were affected.%n";
    private static final String NO_AFFECTED_TOWNS_FORMAT = "No town names were affected.%n";
    private static final String PRINT_TOWNS_FORMAT = "[%s]";



    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        final String country = new Scanner(System.in).nextLine();
        final PreparedStatement updateTownsStatement = connection.prepareStatement(UPDATE_TOWNS_BY_COUNTRY);
        updateTownsStatement.setString(1, country);

        final int rowsAffected = updateTownsStatement.executeUpdate();


        final PreparedStatement getTownsStatement = connection.prepareStatement(GET_TOWNS_BY_COUNTRY);
        getTownsStatement.setString(1, country);

        final ResultSet getTownsSet = getTownsStatement.executeQuery();

        List<String> upperCaseTowns = new ArrayList<>();
        while (getTownsSet.next()) {
              upperCaseTowns.add(getTownsSet.getString(Constants.COLUMN_LABEL_NAME));
        }

        if (rowsAffected > 0) {
            System.out.printf(AFFECTED_TOWNS_FORMAT, rowsAffected);
            System.out.printf(PRINT_TOWNS_FORMAT, String.join(", ", upperCaseTowns));
        } else {
            System.out.printf(NO_AFFECTED_TOWNS_FORMAT);
        }


        connection.close();
    }
}
