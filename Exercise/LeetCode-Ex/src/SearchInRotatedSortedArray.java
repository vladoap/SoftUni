public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};   // 4,5,6,7,8,0,1
        int target = 2;
        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target) {

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[start] <= nums[mid]) {
                // Left half is sorted
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                // Right half is sorted
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1;


    }
}
