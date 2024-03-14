import java.util.Arrays;
import java.util.Comparator;

public class MinimumNumberArrowsBurstBalloons {

    public static void main(String[] args) {

        int[][] points = {{77171087,133597895},{45117276,135064454},{80695788,90089372},{91705403,110208054},{52392754,127005153},{53999932,118094992},{11549676,55543044},{43947739,128157751},{55636226,105334812},{69348094,125645633}};
        System.out.println(findMinArrowShots(points));
    }

    public static int findMinArrowShots(int[][] points) {
        if (points == null) {
            return 0;
        }

        Arrays.sort(points, (a, b) -> {
            if (a[0] != b[0]) {
               return Integer.compare(a[0], b[0]);
            } else {
                return Integer.compare(a[1], b[1]);
            }
        });

        int count = 1;
        int end = points[0][1];
        for (int row = 1; row < points.length; row++) {

            if (points[row][0] <= end) {
                end = Math.min(end, points[row][1]);
            } else {
                count++;
                end = points[row][1];
            }

        }

        return count;
    }
}
