import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SortList {

    public static void main(String[] args) {

        char[][] board =
                {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                        , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                        , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                        , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                        , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                        , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                        , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                        , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                        , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        System.out.println(isValidSudoku(board));
    }

    public static boolean isValidSudoku(char[][] board) {

        Map<Integer, Set<Integer>> rows = new HashMap<>();
        Map<Integer, Set<Integer>> cols = new HashMap<>();
        Map<Integer, Set<Integer>> subBox = new HashMap<>();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
               if (Character.isDigit(board[row][col])) {
                   int num = board[row][col] - '0';
                   int currentSubBox = findSubBox(row, col);

                   rows.putIfAbsent(row, new HashSet<>());
                   cols.putIfAbsent(col, new HashSet<>());
                   subBox.putIfAbsent(currentSubBox, new HashSet<>());

                   if (rows.get(row).contains(num)) {
                       return false;
                   } else {
                       rows.get(row).add(num);
                   }

                   if (cols.get(col).contains(num)) {
                       return false;
                   } else {
                       cols.get(col).add(num);
                   }

                   if (subBox.get(currentSubBox).contains(num)) {
                       return false;
                   } else {
                       subBox.get(currentSubBox).add(num);
                   }

               }

            }
        }
        return true;
    }

    private static int findSubBox(int row, int col) {
        int subGridSize = 3;
        int subBoxRow = row / subGridSize;
        int subBoxCol = col / subGridSize;
        return (subBoxRow * 3) + subBoxCol + 1;

    }
}
