import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {

        int[] nums = {-3,-1,0,0,0,3,3};
        removeDuplicates(nums);
    }

    public static int removeDuplicates(int[] nums) {

        Set<Integer> unique = new LinkedHashSet<>();
        for (int i = 0; i < nums.length; i++) {
            unique.add(nums[i]);
        }

        int count = 0;
        for (Integer num : unique) {
            nums[count++] = num;
        }

        return count;
    }
}
