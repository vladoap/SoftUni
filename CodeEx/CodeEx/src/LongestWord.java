import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LongestWord {

    public static String LongestWord(String sen) {
        String result = sen.replaceAll("[^A-Za-z0-9]+", " ");

        String[] array = Arrays.stream(result.split("\\s+")).toArray(String[]::new);

        String longestWord = null;
        for (String word : array) {
            if (longestWord == null) {
                longestWord = word;
                continue;
            }
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }
        return longestWord;
    }

    public static void main (String[] args) {
        // keep this function call here
        Scanner s = new Scanner(System.in);
        System.out.print(LongestWord(s.nextLine()));
    }
}
