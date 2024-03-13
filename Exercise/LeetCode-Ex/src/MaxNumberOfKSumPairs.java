import java.util.Arrays;

public class MaxNumberOfKSumPairs {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4};
        int k = 5;
        System.out.println(maxOperations(nums, k));
    }

    public static int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int start = 0;
        int end = nums.length - 1;
        int count = 0;
        while (start < end) {
            if (nums[start] + nums[end] == k) {
                start++;
                end--;
                count++;
            } else if (nums[start] + nums[end] > k) {
                end--;
            } else {
                start++;
            }
        }
        return count;

    }
}
