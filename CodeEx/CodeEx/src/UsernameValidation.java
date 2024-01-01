import java.util.Scanner;

public class UsernameValidation {
    public static String CodelandUsernameValidation(String str) {
        if (str.length() < 4 || str.length() > 25 ) {
            return "false";
        }

        if (!str.matches("[A-Za-z][A-Za-z0-9_]+[A-Za-z0-9]+")) {
            return "false";
        }
        return "true";
    }

    public static void main (String[] args) {
        // keep this function call here
        Scanner s = new Scanner(System.in);
        System.out.print(CodelandUsernameValidation(s.nextLine()));
    }
}
