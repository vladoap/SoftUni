public class JumpGame {

    public static void main(String[] args) {

        int[] nums = {3,2,1,0,4};
        boolean result = canJump(nums);

    }

    public static boolean canJump(int[] nums) {
        int reachable = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i > reachable) {
                return false;
            }
            if (reachable < i + nums[i]) {
                reachable = i + nums[i];
            }
        }

        return true;
    }
}
