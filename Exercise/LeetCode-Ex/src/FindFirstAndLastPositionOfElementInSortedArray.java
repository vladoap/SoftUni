public class FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        int[] nums = {3,3,3};
        int target = 3;
        int[] result = searchRange(nums, target);
        System.out.println();
    }

    public static int[] searchRange(int[] nums, int target) {

        int[] result = new int[] {-1,-1};


        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {

            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                int firstLeft = mid;
               while (firstLeft - 1 >= 0 && nums[firstLeft - 1] == target) {
                   firstLeft--;
               }

               int lastRight = mid;
               while (lastRight + 1 < nums.length && nums[lastRight + 1] == target) {
                   lastRight++;
               }

               result[0] = firstLeft;
               result[1] = lastRight;
                return result;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }

        }


        return result;
    }
}
