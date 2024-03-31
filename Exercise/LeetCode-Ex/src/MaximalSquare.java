public class MaximalSquare {

    public static void main(String[] args) {

        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};

        System.out.println(maximalSquare(matrix));

    }

    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int max = Integer.MIN_VALUE;
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];

        for (int row = 1; row < dp.length; row++) {
            for (int col = 1; col < dp[row].length; col++) {

                if (matrix[row -1][col - 1] == '1') {
                    dp[row][col] = Math.min(dp[row -1][col - 1], Math.min(dp[row][col -1], dp[row - 1][col])) + 1;
                    max = Math.max(max, dp[row][col]);
                }

            }

        }
        return max * max;
    }
}
