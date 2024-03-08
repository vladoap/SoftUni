import java.util.HashMap;
import java.util.Map;

public class RansomNote {

    public static void main(String[] args) {

        String ransomNote = "aab";
        String magazine = "baa";

        System.out.println(canConstruct(ransomNote, magazine));

    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        for (char symbol : magazine.toCharArray()) {
            if (!map.containsKey(symbol)) {
                map.put(symbol, 1);
            } else {
                Integer count = map.get(symbol);
                map.put(symbol, count + 1);
            }
        }

        for (char symbol : ransomNote.toCharArray()) {

            if (!map.containsKey(symbol)) {
                return false;
            }
            Integer count = map.get(symbol);
            if (count == 1) {
                map.remove(symbol);
            } else {
                map.put(symbol, count - 1);
            }
        }

        return true;
    }
}
