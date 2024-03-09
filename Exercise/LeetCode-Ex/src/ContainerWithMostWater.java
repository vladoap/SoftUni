public class ContainerWithMostWater {

    public static void main(String[] args) {
       int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height) {

        int start = 0;
        int end = height.length - 1;
        int maxWater = Integer.MIN_VALUE;
        while (start < end) {
            int width = end - start;
            int minHeight = Math.min(height[start], height[end]);
            int currentWater = width * minHeight;
            if (currentWater > maxWater) {
                maxWater = currentWater;
            }

          if (height[start] > height[end]) {
              end--;
          } else if (height[start] <= height[end]){
              start++;
          }
        }

        return maxWater;
    }
}
