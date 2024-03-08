import java.util.Arrays;

public class RotateArray {

    public static void main(String[] args) {

        int[] nums = {1,2,3,4,5,6,7};   // 5,6,7,1,2,3,4   // 7,6,5 4,3,2,1
//        int[] nums = {-1, -100, 3, 99};
        int k = 3;
        rotate(nums, k);
    }

    public static void rotate(int[] nums, int k) {
        k = k % nums.length;

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        }

    private static void reverse(int[] nums, int start, int end) {

        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }


}

