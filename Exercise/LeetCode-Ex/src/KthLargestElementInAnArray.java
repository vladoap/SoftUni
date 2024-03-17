import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KthLargestElementInAnArray {

    public static void main(String[] args) {

        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println(findKthLargest(nums, k));
    }

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

        for (int num : nums) {
            queue.offer(num);
        }

        for (int i = 1; i < k; i++) {
            queue.poll();
        }

        return queue.peek() == null ? -1 : queue.peek();

        // PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        //        for (int i = 0; i < k; i++) {
        //            minHeap.offer(nums[i]);
        //        }
        //
        //        for (int i = k; i < nums.length; i++) {
        //            if (nums[i] > minHeap.peek()) {
        //                minHeap.poll();
        //                minHeap.offer(nums[i]);
        //            }
        //        }
        //
        //        return minHeap.peek();
    }
}
