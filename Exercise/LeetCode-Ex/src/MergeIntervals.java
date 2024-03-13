import java.util.Arrays;
import java.util.Comparator;

public class MergeIntervals {

    public static void main(String[] args) {
        int[][] intervals = {{1,3}, {2,6}, {8,10}, {15,18}};
        System.out.println(merge(intervals).toString());
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 1) {
            return intervals;
        }

        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });
        int countRows = 0;

        for (int row = 0; row < intervals.length; row++) {

            int[] currentInterval = new int[2];
            if (row < intervals.length - 1 && intervals[row][1] >= intervals[row + 1][0]) {
                while (row < intervals.length - 1 && intervals[row][1] >= intervals[row + 1][0]) {
                    currentInterval[0] = Math.min(intervals[row][0], intervals[row + 1][0]);
                    currentInterval[1] = Math.max(intervals[row][1],intervals[row + 1][1]);
                    row++;
                    intervals[row] = currentInterval;
                }

            } else {
                currentInterval[0] = intervals[row][0];
                currentInterval[1] = intervals[row][1];
            }
            intervals[countRows++] = currentInterval;

        }
        int[][] result = Arrays.copyOf(intervals, countRows);

        return result;
    }
}
