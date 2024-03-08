import java.util.HashMap;
import java.util.Map;

public class IntegerToRoman {

    public static void main(String[] args) {

        int num = 1994;
        String result = intToRoman(num);
        System.out.println();
    }

    public static String intToRoman(int num) {
        Map<Integer, Character> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        map.put(1, 'I');
        map.put(5, 'V');
        map.put(10, 'X');
        map.put(50, 'L');
        map.put(100, 'C');
        map.put(500, 'D');
        map.put(1000, 'M');

        while (num > 0) {
            if (num >= 1000) {
                sb.append(map.get(1000));
                num -= 1000;
            } else if (num >= 500) {
                sb.append(map.get(500));
                num -= 500;
            } else if (num >= 100) {
                sb.append(map.get(100));
                num -= 100;
            } else if (num >= 50) {
                sb.append(map.get(50));
                num -= 50;
            } else if (num >= 10) {
                sb.append(map.get(10));
                num -= 10;
            } else if (num >= 5) {
                sb.append(map.get(5));
                num -= 5;
            } else {
                sb.append(map.get(1));
                num -= 1;
            }
        }
        return sb.toString()
                .replace("DCCCC", "CM")
                .replace("CCCC", "CD")
                .replace("LXXXX", "XC")
                .replace("XXXX", "XL")
                .replace("VIIII", "IX")
                .replace("IIII", "IV");


    }
}
