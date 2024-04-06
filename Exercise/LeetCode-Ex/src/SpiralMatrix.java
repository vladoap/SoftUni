import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public static void main(String[] args) {

        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        spiralOrder(matrix);

    }

    public static List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();

        int colEnd = matrix[0].length - 1;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int rowBegin = 0;



        while (rowBegin <= rowEnd && colBegin <= colEnd) {

            for (int col = colBegin; col <= colEnd ; col++) {
                result.add(matrix[rowBegin][col]);
            }
            rowBegin++;

            for (int row = rowBegin; row <= rowEnd; row++) {
                result.add(matrix[row][colEnd]);
            }
            colEnd--;


            if (rowBegin <= rowEnd) {
                for (int col = colEnd; col >= colBegin; col--) {
                    result.add(matrix[rowEnd][col]);
                }
            }
            rowEnd--;


            if (colBegin <= colEnd) {
                for (int row = rowEnd; row >= rowBegin; row--) {
                    result.add(matrix[row][colBegin]);
                }
            }

            colBegin++;




        }

        return result;
    }
}
