package implementations;

import interfaces.Solvable;

import java.util.ArrayDeque;

public class BalancedParentheses implements Solvable {
    private String parentheses;

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
    }

    @Override
    public Boolean solve() {
        char[] parenthesesArray = parentheses.toCharArray();
       ArrayDeque<Character> stack = new ArrayDeque<>();

        for (char c : parenthesesArray) {
            Character top = stack.peek();
            if (top == null) {
                stack.push(c);
                continue;
            }
            if (top.equals('(') && c == ')') {
                stack.pop();
            } else if (top.equals('{') && c == '}') {
                stack.pop();
            } else if (top.equals('[') && c == ']') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}
