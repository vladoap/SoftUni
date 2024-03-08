import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
      int[] nums = {3,2,4};
      int target = 6;
      int[] result = twoSum(nums, target);

      for (int e : result) {
          System.out.print(e + " ");
      }


    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];

            if (!map.containsKey(diff)) {
               map.put(nums[i], i);
            } else {
                return new int[] {i, map.get(diff)};
            }
        }

        return new int[]{};
    }
}
