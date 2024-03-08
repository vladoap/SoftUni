public class MinimumSizeSubarraySum {

    public static void main(String[] args) {

        int target = 7;
        int[] nums = {2,3,1,2,4,3};

        System.out.println(minSubArrayLen(target, nums));

    }

    public static int minSubArrayLen(int target, int[] nums) {

        int sum = 0;
        int min = Integer.MAX_VALUE;
        int k = 0;
        for (int i = 0; i < nums.length; i++) {

            sum += nums[i];

            while (sum >= target) {
                min = Math.min(min, i - k + 1);
                sum -= nums[k];
                k++;
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
