import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MergeSortedArray {

    public static void main(String[] args) {

        int[] nums1 = {1, 2, 3, 0 ,0 ,0};
        int[] nums2 = {2, 5, 6};
        int m = 3;
        int n = 3;
        merge(nums1, m, nums2, n);

    }
    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        if (nums2.length == 0) {
            return;
        }

        int j = 0;
        for (int i = m; i < nums1.length; i++) {
            nums1[i] = nums2[j++];
        }

        Arrays.sort(nums1);


    }

}

