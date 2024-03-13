import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {

    public static void main(String[] args) {

        int[] nums = {0,1,2,4,5,7};
        List<String> result = summaryRanges(nums);
        System.out.println(String.join(", ", result));
    }

    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }

        if (nums.length == 1) {
            result.add(String.valueOf(nums[0]));
            return result;
        }

        int start = 0;
        int end = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                end++;
            } else {
                if (start != end) {
                    result.add(String.format("%d->%d", nums[start], nums[end]));
                } else {
                    result.add(String.format("%d", nums[start]));
                }
                start = i;
                end = i;
            }

        }

        if (start != end) {
            result.add(String.format("%d->%d", nums[start], nums[end]));
        } else {
            result.add(String.format("%d", nums[start]));
        }


        return result;
    }

}
