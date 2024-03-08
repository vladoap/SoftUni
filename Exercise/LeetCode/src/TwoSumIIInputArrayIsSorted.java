import java.util.Arrays;

public class TwoSumIIInputArrayIsSorted {

    public static void main(String[] args) {
        // 2, 7 , 11 , 15 ,20 , 25, 30    i = 26
        // 2 , 3 , 4 ,10, 15, 20, 25  i= 45;

        int[] numbers = {2,3,4};
        int target = 6;

        int[] result = twoSum(numbers, target);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    public static int[] twoSum(int[] numbers, int target) {

         int start = 0;
         int end = numbers.length - 1;

         while (start < end) {

             if (numbers[start] + numbers[end] > target) {
                 end--;
             } else if (numbers[start] + numbers[end] < target) {
                 start++;
             } else {
                 return new int[] {++start, ++end};
             }
         }

         return null;

        }

    }

