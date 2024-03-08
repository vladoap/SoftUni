import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindDuplicateCharacters {
    public static void main(String[] args) {

        String str = "asdfghjasdfaaapoi";

        char[] charArray = str.toCharArray();

        System.out.println("Duplicate characters are: ");

        Set<Character> result = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {

            for (int j = i + 1; j < str.length() ; j++) {
                if (charArray[i] == charArray[j]) {
                    result.add(charArray[i]);
                }
            }


        }


        result.forEach(c -> System.out.print(c + " "));

    }

}
