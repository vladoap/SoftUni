import java.util.HashMap;
import java.util.Map;

public class WordPattern {

    public static void main(String[] args) {

        String pattern = "abba";
        String s = "dog cat cat dog";

        System.out.println(wordPattern(pattern, s));
    }

    public static boolean wordPattern(String pattern, String s) {

        String[] words = s.split("\\s+");
        if (words.length != pattern.length()) {
            return false;
        }

        Map<Character, Integer> patternMap = new HashMap<>();
        Map<String, Integer> wordsMap = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char patternChar = pattern.charAt(i);
            String word = words[i];

            patternMap.putIfAbsent(patternChar, i);
            wordsMap.putIfAbsent(word, i);


            if (!patternMap.get(patternChar).equals(wordsMap.get(word))) {
                return false;
            }

        }

        return true;

    }

}
