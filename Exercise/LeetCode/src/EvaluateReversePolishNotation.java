import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluateReversePolishNotation {

    public static void main(String[] args) {

        String[] tokens = {"4","13","5","/","+"};
        System.out.println(evalRPN(tokens));
    }

    public static int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            if (token.equals("+")) {
                int first = stack.pop();
                int second = stack.pop();
                stack.push(first + second);
            } else if (token.equals("-")) {
                int first = stack.pop();
                int second = stack.pop();
                stack.push(second - first);
            } else if (token.equals("*")) {
                int first = stack.pop();
                int second = stack.pop();
                stack.push(first * second);
            } else if (token.equals("/")) {
                int first = stack.pop();
                int second = stack.pop();
                stack.push(second / first);
            } else {
                int value = Integer.parseInt(token);
                stack.push(value);
            }
        }
        return stack.peek();
    }
}
