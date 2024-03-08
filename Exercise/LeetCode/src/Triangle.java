import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle {

    private static Integer[][] memo;
    public static void main(String[] args) {

        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));

        System.out.println(minimumTotal(triangle));
    }
    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        memo = new Integer[n][n];

        return dfs(0, 0,  triangle);
    }

    private static int dfs(int level, int index, List<List<Integer>> triangle) {
        if (memo[level][index] != null) {
            return memo[level][index];
        }

        int path = triangle.get(level).get(index);
        if (level < triangle.size() - 1) {
            path += Math.min(dfs(level + 1, index, triangle), dfs(level + 1, index + 1, triangle));
        }

        memo[level][index] = path;
        return path;
    }
}
