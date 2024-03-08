import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

class MinStack {

    public static void main(String[] args) {

        MinStack minStack = new MinStack();
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        minStack.getMin(); // return -3
        minStack.pop();
        minStack.getMin(); // return -2
    }

   Deque<Integer> minStack;
    Deque<Integer> stack;

    public  MinStack() {
        minStack = new ArrayDeque<>();
        stack = new ArrayDeque<>();
    }

    public void push(int val) {
       stack.push(val);

       if (minStack.isEmpty() || val <= minStack.peek()) {
           minStack.push(val);
       }
    }

    public void pop() {
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }

    public int top() {
      return stack.peek();
    }

    public int getMin() {
       return minStack.peek();
    }
}