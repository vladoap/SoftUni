public class JumpGame2 {
    public static void main(String[] args) {

     int[] nums = {7,0,9,6,9,6,1,7,9,0,1,2,9,0,3};
     jump(nums);
    }

    public static int jump(int[] nums) {

        int end = 0;
        int reachable = 0;
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (reachable < nums[i] + i) {
                reachable = nums[i] + i;
            }
            if (reachable >= nums.length - 1) {
                count++;
                break;
            }

            if (i == end) {
                end = reachable;
                count++;
            }


        }

        return count;
    }
}
