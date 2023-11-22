package implementations;

import java.util.ArrayDeque;
import java.util.Deque;

public class TheMatrix {
    private char[][] matrix;
    private char fillChar;
    private char toBeReplaced;
    private int startRow;
    private int startCol;

    public TheMatrix(char[][] matrix, char fillChar, int startRow, int startCol) {
        this.matrix = matrix;
        this.fillChar = fillChar;
        this.startRow = startRow;
        this.startCol = startCol;
        this.toBeReplaced = this.matrix[this.startRow][this.startCol];
    }

//    public void solve() {
//        Deque<int[]> deque = new ArrayDeque<>();
//
//        deque.push(new int[]{startRow, startCol});
//
//        while (!deque.isEmpty()) {
//            int[] position = deque.pop();
//
//            int row = position[0];
//            int col = position[1];
//
//            matrix[row][col] = fillChar;
//
//            if (isInBounds(row + 1, col) && matrix[row + 1][col] == toBeReplaced) {
//                deque.push(new int[]{row + 1, col});
//            }
//
//            if (isInBounds(row - 1, col) && matrix[row - 1][col] == toBeReplaced) {
//                deque.push(new int[]{row - 1, col});
//            }
//
//            if (isInBounds(row, col + 1) && matrix[row][col + 1] == toBeReplaced) {
//                deque.push(new int[]{row, col + 1});
//            }
//
//            if (isInBounds(row, col - 1) && matrix[row][col - 1] == toBeReplaced) {
//                deque.push(new int[]{row, col - 1});
//            }
//
//        }
//    }

    public void solve() {
        fillMatrix(startRow, startCol);
    }

    private void fillMatrix(int row, int col) {
        if (isOutOfBounds(row, col) || matrix[row][col] != toBeReplaced) {
            return;
        }

        matrix[row][col] = fillChar;

        fillMatrix(row + 1, col);
        fillMatrix(row - 1, col);
        fillMatrix(row, col + 1);
        fillMatrix(row, col - 1);

    }


    public String toOutputString() {
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                   sb.append(matrix[r][c]);
            }

            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    private boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }

    private boolean isInBounds(int row, int col) {
        return !isOutOfBounds(row, col);
    }
}
