import java.util.Deque;
import java.util.Queue;

public class ReverseString {
    public static void main(String[] args) {
        String str = "String to reverse";

        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = chars.length - 1; i >= 0; i--) {
            sb.append(chars[i]);
        }

        System.out.println(sb.toString());

        System.out.println(new StringBuilder(str).reverse());
        System.err.println("test");

    }
}
