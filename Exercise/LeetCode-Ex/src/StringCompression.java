import java.util.*;

public class StringCompression {

    public static void main(String[] args) {
        char[] chars = {'a', 'a', 'a', 'b', 'b', 'a', 'a'};
        System.out.println(compress(chars));
    }

    public static int compress(char[] chars) {

        Map<Character, Integer> map = new HashMap<>();
        int result = 0;
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            char currentChar = chars[i];
            if (!map.containsKey(currentChar)) {
                map.put(currentChar, 1);
            } else {
                int value = map.get(currentChar);
                map.put(currentChar, value + 1);
            }

            if (i == chars.length - 1 || currentChar != chars[i + 1]) {
                int value = map.get(currentChar);
                if (value == 1) {
                    chars[index++] = currentChar;
                } else {
                    chars[index++] = currentChar;
                    if (value < 10) {
                        chars[index++] = (char) (value + '0');
                    } else {
                        Deque<Integer> stack = new ArrayDeque<>();
                        while (value > 0) {
                            int lastDigit = value % 10;
                            value = value / 10;
                            stack.push(lastDigit);
                        }

                        while (!stack.isEmpty()) {
                            int element = stack.pop();
                            chars[index++] = (char) (element + '0');
                        }
                    }
                }
                map.remove(currentChar);
            }

        }


        return index;
    }
}
