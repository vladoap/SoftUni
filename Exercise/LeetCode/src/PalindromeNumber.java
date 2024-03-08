import java.util.ArrayList;
import java.util.List;

public class PalindromeNumber {

    public static void main(String[] args) {

        System.out.println(isPalindrome(121));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }  else if (x <= 9) {
            return true;
        }


        int initialX = x;
        List<Integer> list = new ArrayList<>();
        while (x > 0) {
            list.add(x % 10);
            x = x / 10;
        }

        int result = 0;
        int multiplier = 1;

        for (int i = list.size() - 1; i >= 0; i--) {
            result += list.get(i) * multiplier;
            multiplier *= 10;
        }

        if (result == initialX) {
            return true;
        } else {
            return false;
        }
    }
}
