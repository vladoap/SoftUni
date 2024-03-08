import java.util.*;

public class GroupAnagrams {

    public static void main(String[] args) {

        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat" };
        List<List<String>> result = groupAnagrams(strs);

        for (List<String> list : result) {
            for (String word : list) {
                System.out.print(word + " ");
            }
            System.out.println();
        }
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String word : strs) {
            char[] wordArr = word.toCharArray();
            Arrays.sort(wordArr);
            String currentWord = String.valueOf(wordArr);

            map.putIfAbsent(currentWord, new ArrayList<>());
            map.get(currentWord).add(word);
        }

        result.addAll(map.values());
        return result;

    }
}
