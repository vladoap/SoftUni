import java.util.HashSet;
import java.util.Set;

public class GreatestCommonDivisorOfStrings {

    public static void main(String[] args) {

        String str1 = "ABCABC";
        String str2 = "ABC";

        System.out.println(gcdOfStrings(str1, str2));
    }

    public static String gcdOfStrings(String str1, String str2) {

        Set<Character> charsStr1 = new HashSet<>();
        Set<Character> charsStr2 = new HashSet<>();
        for (char c : str1.toCharArray()) {
            charsStr1.add(c);
        }

        for (char c : str2.toCharArray()) {
            charsStr2.add(c);
        }

        for (char c : str2.toCharArray()) {
            if (charsStr1.contains(c)) {
                charsStr1.remove(c);
            }
        }


    return null;
    }
}
