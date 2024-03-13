import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {

        String s = "abba"; // 5 - j = 4
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();


        int maxLength = Integer.MIN_VALUE;
        for (int i = 0, j = 0; i < s.toCharArray().length; i++) {
            char currentChar = s.charAt(i);
            if (!map.containsKey(currentChar)) {
                map.put(currentChar, i);
            } else {
                j = Math.max(j, map.get(currentChar) + 1);
                map.put(currentChar, i);
            }

            maxLength = Math.max(maxLength, i - j + 1);


        }


        return maxLength;

    }
}
