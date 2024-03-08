public class FindTheIndexOfTheFirstOccurrenceInAString {

    public static void main(String[] args) {
       String haystack = "mississippi", needle = "issipi";

       int index = strStr(haystack, needle);

        System.out.println(index);
    }

    public static int strStr(String haystack, String needle) {
//       return haystack.indexOf(needle);
        if (needle.length() > haystack.length()) {
            return -1;
        }

        if (needle.equals(haystack)) {
            return 0;
        }

        for (int i = 0; i < haystack.length(); i++) {


            for (int j = 0; j < needle.length(); j++) {
                if (haystack.length() <= i + j) {
                    break;
                }
                if (needle.charAt(j) != haystack.charAt(i + j)) {
                    break;
                }

                if (j == needle.length() - 1) {
                    return i;
                }
            }

        }

        return -1;
    }
}
