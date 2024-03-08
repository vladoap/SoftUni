public class CanPlaceFlowers {


    public static void main(String[] args) {
       int[] flowerbed = {0,0,1,0,0};
       int n = 1;

        System.out.println(canPlaceFlowers(flowerbed, n));
    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {

        for (int i = 0; i < flowerbed.length; i++) {
            if (n == 0) {
                break;
            }

            if (flowerbed[i] == 1) {
                i++;
            } else if (i + 1 < flowerbed.length && flowerbed[i + 1] == 1) {

            } else {
                flowerbed[i] = 1;
                i++;
                n--;
            }

        }

        return n == 0;
    }
}
