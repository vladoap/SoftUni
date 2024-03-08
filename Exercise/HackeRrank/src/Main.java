import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

     Scanner scanner = new Scanner(System.in);
     int n = Integer.parseInt(scanner.nextLine());

     Pattern p = Pattern.compile("<(.+)>([^<]+)<\\/\\1>");
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();

            Matcher m = p.matcher(input);
            boolean matchFound = false;
            while (m.find()) {
                System.out.println(m.group(2));
                matchFound = true;
            }

            if (!matchFound) {
                System.out.println("None");
            }

        }


    }
}

