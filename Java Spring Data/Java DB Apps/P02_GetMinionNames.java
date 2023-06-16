import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class P02_GetMinionNames {

    private static final String GET_MINIONS_NAME_AND_AGE_BY_VILLAIN_ID = "select m.name, m.age from villains v" +
            " join minions_villains mv on v.id = mv.villain_id" +
            " join minions m on m.id = mv.minion_id" +
            " where v.id = ?;";

    private static final String GET_VILLAIN_NAME_BY_ID = "select name from villains v where v.id = ?;";
    private static final String PRINT_FORMAT_HAS_VALID_VILLAIN = "%d. %s %d%n";
    private static final String PRINT_FORMAT_NO_VILLAIN = "No villain with ID %d exists in the database.";
    private static final String PRINT_FORMAT_VILLAIN_NAME = "Villain: %s%n";
    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement villainStatement = connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);

        final int villainId = new Scanner(System.in).nextInt();
        villainStatement.setInt(1, villainId);

        final ResultSet villainSet = villainStatement.executeQuery();


        if (!villainSet.next()) {
            System.out.printf(PRINT_FORMAT_NO_VILLAIN, villainId);
            return;
        }

        final String villainName = villainSet.getString(Constants.COLUMN_LABEL_NAME);
        System.out.printf(PRINT_FORMAT_VILLAIN_NAME, villainName);

        final PreparedStatement minionsStatement = connection.prepareStatement(GET_MINIONS_NAME_AND_AGE_BY_VILLAIN_ID);

        minionsStatement.setInt(1, villainId);

        final ResultSet minionsSet = minionsStatement.executeQuery();

        for (int index = 1; minionsSet.next(); index++) {
            final String minionName = minionsSet.getString(Constants.COLUMN_LABEL_NAME);
            final int minionAge = minionsSet.getInt(Constants.COLUMN_LABEL_AGE);
            System.out.printf(PRINT_FORMAT_HAS_VALID_VILLAIN, index, minionName, minionAge);
        }


        connection.close();
    }
}



