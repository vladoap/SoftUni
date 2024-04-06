import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Candies {

    public static void main(String[] args) {

        List<Integer> arr = List.of(4,6,4,5,6,2);

        System.out.println(candies(arr.size(), arr));
    }

    public static BigInteger candies(int n, List<Integer> arr) {


        Map<Integer, Integer> candiesIdx = new HashMap<>();

        for (int i = 0; i < arr.size(); i++) {
            candiesIdx.put(i, arr.get(i));
        }

        List<Map.Entry<Integer, Integer>> sortedMap = candiesIdx.entrySet().stream().sorted(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        }).collect(Collectors.toList());



        long[] candies = new long[arr.size()];
        Arrays.fill(candies, 1);

        for (var rating : sortedMap) {
            int idx = rating.getKey();


            if (idx < arr.size() - 1 && arr.get(idx) > arr.get(idx + 1)) {
                candies[idx] = Math.max(candies[idx], candies[idx + 1] + 1);
            }

            if (idx > 0 && arr.get(idx) > arr.get(idx - 1)) {
                candies[idx] = Math.max(candies[idx], candies[idx - 1] + 1);
            }


        }

        BigInteger result = BigInteger.valueOf(Arrays.stream(candies).sum());


        return result;


    }
}
