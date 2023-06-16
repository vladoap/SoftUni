import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class P05RemoveVillain {

    private static final String DELETE_VILLAIN_BY_ID = "delete from villains where id = ?;";
    private static final String DELETE_MINIONS_VILLAINS = "delete from minions_villains where villain_id = ?;";
    private static final String COUNT_SERVING_MINIONS_TO_VILLAIN = "select count(*) as count from minions_villains where villain_id = ?;";
    private static final String GET_VILLAIN_NAME_BY_ID = "select name from villains where id = ?;";

    private static final String NO_VILLAIN_FORMAT = "No such villain was found";
    private static final String DELETED_VILLAIN_FORMAT = "%s was deleted%n%d minions released";


    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSQLConnection();
        connection.setAutoCommit(false);

        final int villainId = new Scanner(System.in).nextInt();

        final String villainName = getVillainName(connection, villainId, GET_VILLAIN_NAME_BY_ID);

        final int countServingMinions = countServingMinions(connection, villainId, COUNT_SERVING_MINIONS_TO_VILLAIN);

        deleteMinionsVillains(connection, villainId, DELETE_MINIONS_VILLAINS);

        deleteVillainById(connection, villainId, DELETE_VILLAIN_BY_ID);

        if (villainName == null) {
            System.out.printf(NO_VILLAIN_FORMAT);
            connection.rollback();
        } else {
            System.out.printf(DELETED_VILLAIN_FORMAT, villainName, countServingMinions);
            connection.commit();
        }

        connection.close();

    }

    private static void deleteMinionsVillains(Connection connection, int villainId, String deleteMinionsVillains) throws SQLException {
        final PreparedStatement deleteStatement = connection.prepareStatement(deleteMinionsVillains);
        deleteStatement.setInt(1, villainId);

        deleteStatement.executeUpdate();
    }

    private static void deleteVillainById(Connection connection, int villainId, String deleteVillainById) throws SQLException {
        final PreparedStatement deleteVillainStatement = connection.prepareStatement(deleteVillainById);
        deleteVillainStatement.setInt(1, villainId);

        deleteVillainStatement.executeUpdate();
    }

    private static int countServingMinions(Connection connection, int villainId, String countServingMinionsToVillain) throws SQLException {
        final PreparedStatement countMinionsStatement = connection.prepareStatement(countServingMinionsToVillain);
        countMinionsStatement.setInt(1, villainId);

        final ResultSet countMinionsSet = countMinionsStatement.executeQuery();
        countMinionsSet.next();

        return countMinionsSet.getInt(Constants.COLUMN_LABEL_COUNT);
    }

    private static String getVillainName(Connection connection, int villainId, String getVillainNameById) throws SQLException {
        final PreparedStatement getVillainNameStatement = connection.prepareStatement(getVillainNameById);
        getVillainNameStatement.setInt(1, villainId);

        final ResultSet villainNameSet = getVillainNameStatement.executeQuery();
        if (villainNameSet.next()) {
            return villainNameSet.getString(Constants.COLUMN_LABEL_NAME);
        }

        return null;
    }


}
