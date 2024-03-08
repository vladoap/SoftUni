import java.util.ArrayList;
import java.util.List;

public class ReverseVowelsOfAString {

    public static void main(String[] args) {

        String s = "aA";
        System.out.println(reverseVowels(s));
    }

    public static String reverseVowels(String s) {
        List<Character> vowels = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (isVowel(c)) {
                vowels.add(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        int lastIndex = vowels.size() - 1;
        for (char c : s.toCharArray()) {
            if (!isVowel(c)) {
                sb.append(c);
            } else {
                sb.append(vowels.get(lastIndex--));
            }
        }

        return sb.toString();
    }

    private static boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
