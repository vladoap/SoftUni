import org.w3c.dom.CDATASection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class P03AddMinion {

    private static final String GET_TOWN_ID_BY_NAME = "SELECT t.id from towns t where t.name = ?;";
    private static final String INSERT_TOWN_IF_MISSING = "insert into towns(name) values (?);";
    private static final String GET_VILLAIN_ID_BY_NAME = "select v.id from villains v where v.name = ?;";
    private static final String INSERT_VILLAIN_IF_MISSING = "insert into villains(name, evilness_factor) values (?, ?);";
    private static final String INSERT_MINION = "insert into minions(name, age, town_id) values (?, ?, ?);";
    private static final String INSERT_MINIONS_VILLAINS = "insert into minions_villains(minion_id, villain_id) values (?, ?);";
    private static final String GET_MINION_ID_BY_NAME = "select m.id from minions m where m.name = ?;";

    private static final String ADDED_VILLAIN_FORMAT = "Villain %s was added to the database.%n";
    private static final String ADDED_TOWN_FORMAT = "Town %s was added to the database.%n";
    private static final String ADDED_MINION_TO_VILLAIN_FORMAT = "Successfully added %s to be minion of %s.%n";

    private static final String EVILNESS_FACTOR = "evil";

    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSQLConnection();

        Scanner scanner = new Scanner(System.in);
        final String[] minionsData = scanner.nextLine().split("\\s+");
        final String minionName = minionsData[1];
        final int minionAge = Integer.parseInt(minionsData[2]);
        final String minionTown = minionsData[3];

        final String villainName = scanner.nextLine().split("\\s+")[1];

        int townId = getId(connection,
                GET_TOWN_ID_BY_NAME,
                INSERT_TOWN_IF_MISSING,
                List.of(minionTown),
                ADDED_TOWN_FORMAT);

        int villainId = getId(connection,
                GET_VILLAIN_ID_BY_NAME,
                INSERT_VILLAIN_IF_MISSING,
                List.of(villainName, EVILNESS_FACTOR),
                ADDED_VILLAIN_FORMAT);



        final PreparedStatement insertMinionStatement = connection.prepareStatement(INSERT_MINION);
        insertMinionStatement.setString(1, minionName);
        insertMinionStatement.setInt(2, minionAge);
        insertMinionStatement.setInt(3, townId);

        insertMinionStatement.executeUpdate();


        final PreparedStatement minionIdByNameStatement = connection.prepareStatement(GET_MINION_ID_BY_NAME);
        minionIdByNameStatement.setString(1, minionName);
        final ResultSet minionIdSet = minionIdByNameStatement.executeQuery();
        minionIdSet.next();
        final int minionId = minionIdSet.getInt(Constants.COLUMN_LABEL_ID);

        final PreparedStatement minionsVillainsStatement = connection.prepareStatement(INSERT_MINIONS_VILLAINS);
        minionsVillainsStatement.setInt(1, minionId);
        minionsVillainsStatement.setInt(2, villainId);
        minionsVillainsStatement.executeUpdate();

        System.out.printf(ADDED_MINION_TO_VILLAIN_FORMAT, minionName, villainName);

        connection.close();

    }

    private static int getId(Connection connection, String selectQuery, String updateQuery,
                             List<String> arguments, String printFormat) throws SQLException {
        final String name = arguments.get(0);
        final PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        selectStatement.setString(1, name);

        final ResultSet resultSet = selectStatement.executeQuery();

        if (!resultSet.next()) {
            final PreparedStatement insertStatement = connection.prepareStatement(updateQuery);
            for (int index = 1; index <= arguments.size(); index++) {
                insertStatement.setString(index, arguments.get(index - 1));
            }

            insertStatement.executeUpdate();

            final ResultSet newResultSet = selectStatement.executeQuery();
            newResultSet.next();

            System.out.printf(printFormat, name);

            return newResultSet.getInt(Constants.COLUMN_LABEL_ID);
        }

        return resultSet.getInt(Constants.COLUMN_LABEL_ID);
    }

}
