import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

    public static void main(String[] args) {

        int n = 2;
        System.out.println(isHappy(n));

    }

    public static boolean isHappy(int n) {
        if (n == 1){
            return true;
        }

        Set<Integer> unique = new HashSet<>();

        while (n > 1) {
            int num = n;


            int sum = 0;
            while (num > 0) {
                int digit = num % 10;
                num = num / 10;
                sum += Math.pow(digit, 2);
            }

            n = sum;
            if (unique.contains(n)) {
                return false;
            } else {
                unique.add(n);
            }

        }

        return true;
    }
    }
