public class HouseRobber {

    public static void main(String[] args) {

        int[] nums = {2, 1, 1, 2};   // 2 ,1 ,1 , 1, , 1, 2, 3, 6
        System.out.println(rob(nums));
    }

    public static int rob(int[] nums) {
        Integer[] memo = new Integer[100];

       return findMaxPath(nums, memo, nums.length - 1);

    }

    private static int findMaxPath(int[] nums, Integer[] memo, int i) {
        if (i < 0) {
            return 0;
        }

        if (memo[i] != null) {
            return memo[i];
        }

       int result = Math.max(findMaxPath(nums,memo, i - 2) + nums[i], findMaxPath(nums, memo, i - 1));
        memo[i] = result;

        return result;
    }
}
