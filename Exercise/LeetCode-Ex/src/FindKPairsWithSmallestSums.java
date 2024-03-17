import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsWithSmallestSums {

    public static void main(String[] args) {

        int[] nums1 = {1,2,4,5,6};  // pq = 4 , 5, 7, 8 , 9
        int[] nums2 = {3,5,7,9};
        int k = 3;
        kSmallestPairs(nums1, nums2, k);
    }

    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        List<List<Integer>> result = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        for (int num : nums1) {
            pq.offer(new int[] {num + nums2[0], 0});
        }

        while (k > 0 && !pq.isEmpty()) {
            int[] arr = pq.poll();
            int sum = arr[0]; // 4
            int pos = arr[1]; // 0

            List<Integer> currentPair = new ArrayList<>();
            currentPair.add(sum - nums2[pos]);
            currentPair.add(nums2[pos]);
            result.add(currentPair);

            if (pos + 1 < nums2.length) {
                pq.offer(new int[]{sum - nums2[pos] + nums2[pos + 1], pos + 1});
            }

            k--;

        }

        return result;
    }

}
