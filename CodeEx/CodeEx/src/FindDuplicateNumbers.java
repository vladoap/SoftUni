import java.util.*;

public class FindDuplicateNumbers {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2, 1, 4, 5, 4};
        List<Integer> duplicates = findDuplicates(nums);
        System.out.println(duplicates);

    }

    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> duplicates = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int number : nums) {
            int count = 0;
            if (map.get(number) != null) {
                count = map.get(number);
            }
            map.put(number, count + 1);
        }


        for (var entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                duplicates.add(entry.getKey());
            }
        }
        return duplicates;
    }
}
