public class MaximumLengthOfRepeatedSubarray {

    public static void main(String[] args) {

        int[] nums1 = {0,0,0,0,0,0,1,0,0,0};
        int[] nums2 = {0,0,0,0,0,0,0,1,0,0};

        System.out.println(findLength(nums1, nums2));
    }

    public static int findLength(int[] nums1, int[] nums2) {

        int maxLength = Integer.MIN_VALUE;
        for (int i = 0; i < nums1.length; i++) {

            int currentMaxLength = 0;
            int currentLength = 0;
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i + currentLength] != nums2[j]) {
                    currentMaxLength = Math.max(currentMaxLength, currentLength);
                    currentLength = 0;
                } else {
                    currentLength++;
                }

                if (i + currentLength >= nums1.length) {
                    break;
                }
            }
            currentMaxLength = Math.max(currentMaxLength, currentLength);
            maxLength = Math.max(maxLength, currentMaxLength);
        }

        return maxLength;
    }

}
