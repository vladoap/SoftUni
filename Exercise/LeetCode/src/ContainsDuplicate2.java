import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicate2 {

    public static void main(String[] args) {

        int[] nums = {1,2,3,1};
        int k = 3;

        System.out.println(containsNearbyDuplicate(nums, k));
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];
            if (!map.containsKey(currentNum)) {
                map.put(currentNum, i);
            } else {
                int index = map.get(currentNum);
                if (i - index <= k) {
                    return true;
                } else {
                    map.put(currentNum, i);
                }
            }
        }

        return false;

//        for (int i = 0; i < nums.length; i++) {
//
//            for (int j = i + 1; j < nums.length ; j++) {
//                if (j > i + k) {
//                    break;
//                }
//
//                if (nums[i] == nums[j]) {
//                    return true;
//                }
//            }
//        }
//
//        return false;
    }
}
