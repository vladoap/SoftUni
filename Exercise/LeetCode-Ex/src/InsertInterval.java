import java.util.Arrays;
import java.util.Comparator;

public class InsertInterval {

    public static void main(String[] args) {

        int[][] intervals = {{1,5}};
        int[] newInterval = {2,3};

        int[][] result = insert(intervals, newInterval);
        System.out.println();
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if (newInterval == null) {
            return intervals;
        }


        int[][] result = Arrays.copyOf(intervals, intervals.length + 1);
        result[intervals.length] = newInterval;
        Arrays.sort(result, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });

        int countRows = 0;

        for (int row = 0; row < result.length; row++) {

            int[] currentInterval = new int[2];
            if (row == result.length - 1 || result[row][1] < result[row + 1][0]) {
                currentInterval[0] = result[row][0];
                currentInterval[1] = result[row][1];

            } else {
                while (row < result.length - 1 && result[row][1] >= result[row + 1][0]) {
                    currentInterval[0] = Math.min(result[row][0], result[row + 1][0]);
                    currentInterval[1] = Math.max(result[row][1], result[row + 1][1]);
                    row++;
                    result[row] = currentInterval;
                }
            }
            result[countRows++] = currentInterval;
        }

        return Arrays.copyOf(result, countRows);

    }

}
