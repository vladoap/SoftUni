import java.util.*;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {

        int[] nums = {4, 10, 4, 3, 8, 9};
        System.out.println(lengthOfLIS(nums));
    }

    public static int lengthOfLIS(int[] nums) {

        List<Integer> sub = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int size = sub.size();
            if (sub.isEmpty() || sub.get(size - 1) < num) {
                sub.add(num);
            } else {
                int index = Collections.binarySearch(sub, num);
                if (index < 0) {
                    sub.set(-index - 1, num);

                }
            }
        }

        return sub.size();
    }
}
