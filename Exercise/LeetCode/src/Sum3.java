import java.util.*;

public class Sum3 {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};  // -4 -1 -1 0 1 2

        List<List<Integer>> lists = threeSum(nums);

        System.out.println();
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();

        Arrays.sort(nums);
        int target = 0;
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum == target) {
                    set.add(List.of(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                } else if (sum < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }

        result.addAll(set);

        return result;

    }
}
