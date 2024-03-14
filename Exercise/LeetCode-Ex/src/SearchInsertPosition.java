public class SearchInsertPosition {

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 2;
        System.out.println(searchInsert(nums, target));
    }

    public static int searchInsert(int[] nums, int target) {
          if (nums[nums.length - 1] < target) {
              return nums.length;
          }



        int start = 0;
        int end = nums.length - 1;
        while (start < end) {

            int middleIndex = (start + end) / 2;
            int middle = nums[middleIndex];
            if (target > middle) {
                start = middleIndex + 1;
            } else if (target < middle) {
                end = middleIndex;
            } else {
                return middleIndex;
            }
        }

        return start;

    }
}
