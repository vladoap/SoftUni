public class ClimbingStairs {

    public static void main(String[] args) {

        int n = 4;  // 0,1,1,2,3,5
        System.out.println(climbStairs(n));
    }

    public static int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
