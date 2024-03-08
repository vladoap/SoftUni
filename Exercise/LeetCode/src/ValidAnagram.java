import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    public static void main(String[] args) {

        String s = "anagram";
        String t = "nagaram";

        System.out.println(isAnagram(s, t));
    }

    public static boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        for (char symbol : s.toCharArray()) {
            if (map.containsKey(symbol)) {
                int count = map.get(symbol);
                map.put(symbol, count + 1);
            } else {
                map.put(symbol, 1);
            }
        }

        for (char symbol : t.toCharArray()) {
            if (map.containsKey(symbol)) {
                int value = map.get(symbol);
                if (value == 1) {
                    map.remove(symbol);
                } else {
                    map.put(symbol, value - 1);
                }
            } else {
                return false;
            }
        }

        return true;

    }
}
