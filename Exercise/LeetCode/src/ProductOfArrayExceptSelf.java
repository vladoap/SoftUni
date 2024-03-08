import java.util.Arrays;
import java.util.stream.IntStream;

public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {

        int[] nums = {-1,1,0,-3,3};

        int[] result = productExceptSelf(nums);

        System.out.println();
    }

    public static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int left = 1;
            int right = 1;


            for (int j = i + 1; j < nums.length ; j++) {
                right *= nums[j];
            }

            for (int k = 0; k < i; k++) {
                left *= nums[k];
            }

            result[i] = left * right;
        }

        return result;
    }
}
