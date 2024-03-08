import java.util.*;

public class Java_Stack {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            Deque<Character> stack = new ArrayDeque<>();

            for (char e : input.toCharArray()) {

                if (e == '(' || e == '{' || e == '[') {
                    stack.push(e);
                } else {
                    if (stack.isEmpty()) {

                        break;
                    }

                    char prevChar = stack.peek();
                    if (prevChar == '(' && e == ')' || prevChar == '{' && e == '}' || prevChar == '[' && e == ']') {
                        stack.pop();
                    } else {
                        break;
                    }
                }
            }

            System.out.println(stack.isEmpty());

        }
    }
}
