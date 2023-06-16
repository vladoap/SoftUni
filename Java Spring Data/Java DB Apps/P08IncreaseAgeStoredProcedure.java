import java.sql.*;
import java.util.Scanner;

public class P08IncreaseAgeStoredProcedure {

    private static final String PROCEDURE_INCREASE_AGE_BY_1 = "create procedure usp_get_older(minion_id int)" +
            " begin" +
            " update minions" +
            " set age = age + 1" +
            " where id = minion_id;" +
            " end;";
    private static final String CALL_PROCEDURE_INCREASE_AGE_BY_1 = "call usp_get_older(?);";
    private static final String GET_MINION_BY_ID = "select name, age from minions where id = ?;";
    private static final String PRINT_FORMAT = "%s %d";

    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSQLConnection();
        final PreparedStatement createProcedureStatement = connection.prepareStatement(PROCEDURE_INCREASE_AGE_BY_1);
        createProcedureStatement.executeUpdate();

        final int minionId = new Scanner(System.in).nextInt();

        final PreparedStatement callProcedureStatement = connection.prepareStatement(CALL_PROCEDURE_INCREASE_AGE_BY_1);
        callProcedureStatement.setInt(1, minionId);
        callProcedureStatement.executeUpdate();

       final PreparedStatement selectStatement = connection.prepareStatement(GET_MINION_BY_ID);
       selectStatement.setInt(1, minionId);
        final ResultSet selectSet = selectStatement.executeQuery();
        selectSet.next();

        final String minionName = selectSet.getString(Constants.COLUMN_LABEL_NAME);
        final int minionAge = selectSet.getInt(Constants.COLUMN_LABEL_AGE);
        System.out.printf(PRINT_FORMAT, minionName, minionAge);
    }
}
