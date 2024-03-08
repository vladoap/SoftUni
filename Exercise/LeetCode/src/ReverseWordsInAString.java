import java.util.ArrayList;
import java.util.List;

public class ReverseWordsInAString {

    public static void main(String[] args) {

         String s = "the sky is blue";
         String result = reverseWords(s);

        System.out.println();
    }

    public static String reverseWords(String s) {
          s = s.trim();

        String[] words = s.split("\\s+");
        String[] reversedWords = new String[words.length];

        for (int i = words.length - 1, j = 0; i >= 0; i--, j++) {
            reversedWords[j] = words[i];
        }

        StringBuilder sb = new StringBuilder();
        for (String word : reversedWords) {
            sb.append(word).append(" ");
        }

        return sb.toString().trim();
    }
}
