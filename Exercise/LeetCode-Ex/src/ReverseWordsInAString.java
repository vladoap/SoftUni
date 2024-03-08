import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReverseWordsInAString {

    public static void main(String[] args) {
            String s = "  hello world  ";
        System.out.println(reverseWords(s.trim()));
    }

    public static String reverseWords(String s) {


        String[] words = s.split("\\s+");
        int lastIndex = words.length - 1;
        for (int i = 0; i <= words.length / 2; i++) {
            String lastWord = words[lastIndex];
            words[lastIndex] = words[i];
            words[i] = lastWord;
            lastIndex--;
        }

        String result = String.join(" ", words);

        return result;

    }
}
