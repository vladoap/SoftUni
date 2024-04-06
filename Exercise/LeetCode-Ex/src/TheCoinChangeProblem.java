import java.util.HashMap;
import java.util.List;

public class TheCoinChangeProblem {

    public static void main(String[] args) {

        int n = 3;
        List<Long> coins = List.of(8L,3L,1L,2L);

        System.out.println(getWays(n , coins));
    }

    public static long getWays(int n, List<Long> coins) {

        return getWays(n , coins, 0, new HashMap<>());
    }

    public static long getWays(long n, List<Long> coins, long coinIdx, HashMap<List<Long>, Long> memo) {
        if (n == 0) {
            return 1;
        }

        if (n > 0 && coinIdx == coins.size() || n < 0) {
            return 0;
        }

        List<Long> key = List.of(n , coinIdx);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        long totalWays = 0;
        for (int qty = 0; qty <= n; qty++) {
            long subAmount = n - coins.get((int) coinIdx) * qty;
            totalWays += getWays(subAmount, coins, coinIdx + 1, memo);
        }


        memo.put(key, totalWays);
        return totalWays;

    }
}
