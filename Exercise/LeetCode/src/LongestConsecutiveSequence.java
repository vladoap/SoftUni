import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class LongestConsecutiveSequence {

    public static void main(String[] args) {

        int[] nums = {9,1,4,7,3,-1,0,5,8,-1,6};
        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums) {

        Map<Integer, Integer> map = new TreeMap<>();

        for (int num : nums) {
            map.putIfAbsent(num, 0);
        }

        int longestCount = 0;
        int count = 0;
        int prev = Integer.MIN_VALUE;
        for (int key : map.keySet()) {
            if (count == 0) {
                count++;
                prev = key;
                continue;
            }

            if (key != prev + 1) {
                longestCount = Math.max(longestCount, count);
                count = 1;
                prev = key;
                continue;
            }
            count++;
            prev = key;
        }


        return Math.max(longestCount, count);
    }
}
