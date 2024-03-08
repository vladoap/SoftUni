public class IncreasingTripletSubsequence {

    public static void main(String[] args) {

        int[] nums = {2,1,5,0,4,6};
        System.out.println(increasingTriplet(nums));
    }

    public static boolean increasingTriplet(int[] nums) {

        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= first) {
                first = num;
            } else if (num <= second) {
                second = num;
            } else {
                return true;
            }
        }

        return false;
    }
}
