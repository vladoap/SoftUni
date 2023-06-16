import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class P06PrintAllMinionNames {

    private static final String GET_COUNT_MINIONS = "select count(*) as count from minions;";
    private static final String GET_NAME_BY_ID = "select name from minions where id = ?;";


    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement getCountStatement = connection.prepareStatement(GET_COUNT_MINIONS);
        final ResultSet getCountSet = getCountStatement.executeQuery();
        getCountSet.next();
        final int countMinions = getCountSet.getInt(Constants.COLUMN_LABEL_COUNT);

        final PreparedStatement getNameStatement = connection.prepareStatement(GET_NAME_BY_ID);


        int correction = countMinions % 2 == 1 ? 0 : 1;

        for (int index = 0; index <= countMinions / 2 - correction; index++) {
            getNameStatement.setInt(1, index + 1);
            final ResultSet resultSetASC = getNameStatement.executeQuery();
            resultSetASC.next();
            final String nameASC = resultSetASC.getString(Constants.COLUMN_LABEL_NAME);
            System.out.println(nameASC);

            if ((countMinions / 2 == index)) {
                break;
            }

            getNameStatement.setInt(1, countMinions - index);
            final ResultSet resultSetDESC = getNameStatement.executeQuery();
            resultSetDESC.next();
            final String nameDESC = resultSetDESC.getString(Constants.COLUMN_LABEL_NAME);
            System.out.println(nameDESC);
        }
        connection.close();
    }
}
