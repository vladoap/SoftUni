public class ZigzagConversion {

    public static void main(String[] args) {


        String word = "PAYPALISHIRING";
        int numRows = 3;

        System.out.println(convert(word, numRows));
    }

    public static String convert(String s, int numRows) {
        Character[][] matrix = new Character[numRows][s.length()];

        int col = 0; // 0
        int row = 0; // 2
        int index = 0; // 3

        while (index < s.length()) {

            while (row < matrix.length) {
                if (index == s.length()) {
                    break;
                }
                matrix[row++][col] = s.charAt(index++);
            }

            row = matrix.length - 1;
            while (row > 1) {
                if (index == s.length()) {
                    break;
                }
                matrix[--row][++col] = s.charAt(index++);
            }

            row = 0;
            col++;

        }

        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c] != null) {
                    sb.append(matrix[r][c]);
                }
            }
        }

        return sb.toString();

    }
}
