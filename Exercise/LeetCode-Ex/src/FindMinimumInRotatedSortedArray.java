public class FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {

        int[] nums = {11,13,15,17};  // 5,6,1,2,3
        System.out.println(findMin(nums));

    }

    public static int findMin(int[] nums) {

        int start = 0;
        int end = nums.length - 1;
        int min = Integer.MAX_VALUE;

        while (start <= end) {

            int mid = start + (end - start) / 2;

            if (nums[start] <= nums[mid]) {
                // left is sorted
                int leftMin = nums[start];
                if (leftMin < min) {
                    min = leftMin;
                }
                start = mid + 1;

            } else {
                // right is sorted
                int rightMin = nums[mid];
                if (rightMin < min) {
                    min = rightMin;
                }
                end = mid - 1;

            }
        }


        return min;
    }
}
