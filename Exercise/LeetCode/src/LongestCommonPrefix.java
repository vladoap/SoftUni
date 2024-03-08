public class LongestCommonPrefix {

    public static void main(String[] args) {
       String[] strs = {"ab", "a"};
        String result = longestCommonPrefix(strs);

        System.out.println();
    }

    public static String longestCommonPrefix(String[] strs) {
        StringBuilder prefix = new StringBuilder();

        for (int i = 0; i < strs[0].length(); i++) {

            char symbol = strs[0].charAt(i);

            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= i  || strs[j].charAt(i) != symbol) {
                    return prefix.toString();
                }
            }

            prefix.append(symbol);
        }


        return prefix.toString();

    }
}
