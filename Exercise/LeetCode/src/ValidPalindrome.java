public class ValidPalindrome {

    public static void main(String[] args) {

    String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
    }

    public static boolean isPalindrome(String s) {

        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (Character.isAlphabetic(c) || Character.isDigit(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }

//        s = s.replaceAll("[^A-Za-z0-9]+", "").toLowerCase();
        int start = 0;
        int end = sb.length() - 1;


        while (start < end) {
            if (sb.charAt(start) != sb.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }
}
