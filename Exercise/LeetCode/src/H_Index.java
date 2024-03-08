import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class H_Index {

    public static void main(String[] args) {

        int[] nums = {10,9,8,7,6,5,4,3,2,1};
        int result = hIndex(nums);

    }

    public static int hIndex(int[] citations) {

       Arrays.sort(citations);

       int h = 0;
       for (int i = citations.length - 1; i >= 0; i--) {
           if (citations[i] > h) {
               h += 1;
           }
       }

       return h;
    }
}
