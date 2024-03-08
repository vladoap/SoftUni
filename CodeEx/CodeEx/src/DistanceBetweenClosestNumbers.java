import java.util.Arrays;
import java.util.HashMap;

public class DistanceBetweenClosestNumbers {

    static int distClosestNumbers(int[] numbers) {
        Arrays.sort(numbers);


        int rangeBetween = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i + 1] - numbers[i] < rangeBetween) {
                rangeBetween = numbers[i + 1] - numbers[i];
            }
        }

        return rangeBetween;

    }

    public static void main(String[] args) {
        int[] testArray = {3, 9, 50, 15, 99, 7, 98, 65};
        int result = distClosestNumbers(testArray);
        System.out.println(result); // Expected result is 1 (the 2 closest numbers are 98 and 99)
    }

   
}
