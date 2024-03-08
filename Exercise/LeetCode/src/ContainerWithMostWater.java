import java.util.Stack;

public class ContainerWithMostWater {

    public static void main(String[] args) {
         int[] height = {1,8,6,2,5,4,8,3,7};

        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height) {

        int mostWater = 0;
        int start = 0;
        int end = height.length - 1;

        while (start < end) {
            int currentWater = (end - start) * Math.min(height[start], height[end]);

            if (currentWater > mostWater) {
                mostWater = currentWater;
            }
            if (height[start] <= height[end]) {
                start++;
            } else {
                end--;
            }
        }

        return mostWater;
    }
}
