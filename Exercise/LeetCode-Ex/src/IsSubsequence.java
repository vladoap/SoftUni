import java.util.ArrayDeque;
import java.util.Queue;

public class IsSubsequence {

    public static void main(String[] args) {

        String s = "axc", t = "ahbgdc";
        System.out.println(isSubsequence(s, t));
    }
    public static boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        if (t.length() == 0) {
            return false;
        }
//        Queue<Character> queueS = new ArrayDeque<>();
//
//        for (char c : s.toCharArray()) {
//            queueS.offer(c);
//        }
//
//        for (int i = 0; i < t.length(); i++) {
//            if (queueS.isEmpty()) {
//                return true;
//            }
//            if (t.charAt(i) == queueS.peek()) {
//                queueS.poll();
//            }
//        }
//
//        return queueS.isEmpty();

        int index = 0;
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == s.charAt(index)) {
                index++;
            }

            if (s.length() == index) {
                return true;
            }
        }

        return false;

    }

}
