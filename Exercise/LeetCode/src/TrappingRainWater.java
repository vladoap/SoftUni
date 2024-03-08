import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class TrappingRainWater {

    public static void main(String[] args) {

        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};

        int result = trap(height);
    }


    public static int trap(int[] height) {
        if (height.length < 2) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();

        int maxWater = 0;
        int i = 0;
        while (i < height.length) {
            if (stack.isEmpty() || height[i] <= height[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int bot = stack.pop();

                if (!stack.isEmpty()) {
                    int minHeight = Math.min(height[i], height[stack.peek()]);
                     maxWater += (minHeight - height[bot]) * (i - stack.peek() - 1);
                }

            }
        }


        return maxWater;
    }
}
