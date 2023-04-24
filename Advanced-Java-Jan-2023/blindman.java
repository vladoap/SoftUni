import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class blindman {
    static int playerRow = -1;
    static int playerCol = -1;
    static int countTouchedOpponents = 0;
    static int countMoves = 0;
    static int totalOpponents = 0;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Integer>
        int[] dimensions = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rows = dimensions[0];
        int cols = dimensions[1];
        char[][] playground = new char[rows][cols];

        for (int r = 0; r < rows; r++) {
            char[] singleRow = scan.nextLine().replaceAll("\\s+", "").toCharArray();
            for (int c = 0; c < cols; c++) {
                if (c < singleRow.length) {
                    playground[r][c] = singleRow[c];
                } else {
                    playground[r][c] = '-';
                }
                if (playground[r][c] == 'B') {
                    playerRow = r;
                    playerCol = c;
                }
                if (playground[r][c] == 'P') {
                    totalOpponents++;
                }
            }
        }


        String command = scan.nextLine();
        while (!command.equals("Finish")) {

            switch (command) {
                case "up":
                    playerMove(playground, command, -1, 0);
                    break;
                case "down":
                    playerMove(playground, command, 1, 0);
                    break;
                case "left":
                    playerMove(playground, command, 0, -1);
                    break;
                case "right":
                    playerMove(playground, command, 0, 1);
                    break;
            }
            if (totalOpponents <= 0) {
                break;
            }

            command = scan.nextLine();
        }

        System.out.println("Game over!");
        System.out.printf("Touched opponents: %d Moves made: %d%n", countTouchedOpponents, countMoves);



    }

    private static void playerMove(char[][] playground, String command, int r, int c) {
        int nextRow = playerRow + r;
        int nextCol = playerCol + c;

        if (isInBounds(playground, nextRow, nextCol)) {
            playground[playerRow][playerCol] = '-';
            if ((playground[nextRow][nextCol] != 'O') && playground[nextRow][nextCol] == 'P') {
                countMoves++;
                countTouchedOpponents++;
                totalOpponents--;
                playground[nextRow][nextCol] = '-';
                playerRow = nextRow;
                playerCol = nextCol;
            } else if ((playground[nextRow][nextCol] != 'O') && playground[nextRow][nextCol] != 'P') {
                countMoves++;
                playerRow = nextRow;
                playerCol = nextCol;
            }
            playground[playerRow][playerCol] = 'B';

        }
    }

    private static boolean isInBounds(char[][] playground, int r, int c) {
        return r >= 0 && r < playground.length && c >= 0 && c < playground[r].length;
    }
}
