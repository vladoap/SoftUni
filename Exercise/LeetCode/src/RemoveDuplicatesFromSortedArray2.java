public class RemoveDuplicatesFromSortedArray2 {

    public static void main(String[] args) {

        int[] nums = {1,1,1,2,2,3};
//        int[] nums = {0,0,1,1,1,1,2,3,3};
        removeDuplicates(nums);
    }

    public static int removeDuplicates(int[] nums) {

        int count = 2;
        for (int i = 2; i < nums.length; i++) {

            if (nums[i] != nums[i - 2]) {
                nums[count] = nums[i];
                count++;
            }
        }

        return count;

    }
}
