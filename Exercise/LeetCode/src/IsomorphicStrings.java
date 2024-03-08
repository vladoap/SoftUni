import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IsomorphicStrings {

    public static void main(String[] args) {
        String s = "paper";
        String t = "title";

        System.out.println(isIsomorphic(s, t));
    }
    public static boolean isIsomorphic(String s, String t) {
          if (s.length() != t.length()) {
              return false;
          }

        Map<Character, Integer> tElements = new HashMap<>();
        Map<Character, Integer> sElements = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char tChar = t.charAt(i);
            char sChar = s.charAt(i);

            tElements.putIfAbsent(tChar, i);
            sElements.putIfAbsent(sChar, i);


            if (!tElements.get(tChar).equals(sElements.get(sChar))) {
                return false;
            }
        }

        return true;




    }
}
