import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BalancedParenthesis {
    public static String BracketMatcher(String str) {
        if (!str.contains("(") && !str.contains(")")) {
            return "1";
        }

        Deque<Character> stack = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            if (c != '(' && c != ')') {
                continue;
            }
            Character top = stack.peek();

            if (top == null) {
                stack.push(c);
                continue;
            }
            if (top == '(' || c == ')') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }


        return stack.isEmpty() ? "1" : "0";
    }

    public static void main (String[] args) {
        // keep this function call here
        Scanner s = new Scanner(System.in);
        System.out.print(BracketMatcher(s.nextLine()));
    }
}
