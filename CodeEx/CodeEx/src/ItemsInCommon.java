import java.util.HashMap;

public class ItemsInCommon {

    public static void main(String[] args) {

        int[] array1 = {1, 3, 5};
        int[] array2 = {2, 4, 5};

        System.out.println(itemInCommon(array1, array2));

        /*
            EXPECTED OUTPUT:
            ----------------
            true

        */

    }

    private static boolean itemInCommon(int[] array1, int[] array2) {
        HashMap<Integer, Boolean> hashMap = new HashMap<>();

        for (int element : array1) {
            hashMap.put(element, true);
        }

        for (int element : array2) {
            if (hashMap.get(element) != null) {
                return true;
            }
        }

        return false;

    }
}
