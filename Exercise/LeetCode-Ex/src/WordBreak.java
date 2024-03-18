import java.util.*;

public class WordBreak {

    public static void main(String[] args) {

        String s = "leetcode";
        List<String> wordDict = new ArrayList<>(List.of("leet", "code"));
        System.out.println(wordBreak(s, wordDict));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {

        Set<String> words = new HashSet<>(wordDict);
        boolean[] f = new boolean[s.length() + 1];
        f[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (String str : wordDict) {
                if (str.length() <= i) {
                    if (f[i - str.length()]) {
                        if (s.substring(i - str.length(), i).equals(str)) {
                            f[i] = true;
                            break;
                        }
                    }
                }
            }
        }
        return f[s.length()];

    }
}
