import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class P01_GetVillainsNames {

    private static final String GET_VILLAINS_NAMES = "select v.name, count(distinct mv.minion_id) as count" +
            " from villains v join minions_villains mv on v.id = mv.villain_id\n" +
            " GROUP BY v.id" +
            " HAVING  count > ?" +
            " ORDER BY count DESC;";

    private static final String COLUMN_LABEL_COUNT = "count";

    private static final String PRINT_FORMAT = "%s %d%n";

    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement allVillainsNames = connection.prepareStatement(GET_VILLAINS_NAMES);
        allVillainsNames.setInt(1, 15);

        final ResultSet villainNamesSet = allVillainsNames.executeQuery();

        while (villainNamesSet.next()) {

            final String villainName = villainNamesSet.getString(Constants.COLUMN_LABEL_NAME);
            final int minionsCount = villainNamesSet.getInt(COLUMN_LABEL_COUNT);

            System.out.printf(PRINT_FORMAT, villainName, minionsCount);
        }
        connection.close();
    }
}
