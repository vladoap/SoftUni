public class IsSubsequence {


    public static void main(String[] args) {
        String s = "acb", t = "ahbgdc";
        System.out.println(isSubsequence(s, t));
    }

    public static boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        if (t.length() == 0) {
            return false;
        }

        int k = 0;
        for (int i = 0; i < t.length(); i++) {
            int index = t.indexOf(s.charAt(k++), i);
            if (index == -1) {
                return false;
            }
            i = index;

            if (k == s.length()) {
                return true;
            }

        }

        if (k < s.length()) {
            return false;
        }

        return true;
    }
}
