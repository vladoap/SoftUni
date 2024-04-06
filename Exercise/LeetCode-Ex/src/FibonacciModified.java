import java.math.BigInteger;
import java.util.HashMap;

public class FibonacciModified {

    public static void main(String[] args) {

        int t1 = 0;
        int t2 = 1;
        int n = 10;
        fibonacciModified(t1, t2, n, new HashMap<>());
    }

    public static BigInteger fibonacciModified(int t1, int t2, int n, HashMap<Integer, BigInteger> memo) {

        if (n == 1) {
            return BigInteger.valueOf(t1);
        }

        if (n == 2) {
            return BigInteger.valueOf(t2);
        }

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        BigInteger secondBefore = fibonacciModified(t1, t2, n - 2, memo);
        BigInteger firstBefore = fibonacciModified(t1, t2, n - 1, memo);
        BigInteger current = secondBefore.add(firstBefore.multiply(firstBefore));



            memo.put(n, current);
            return current;

    }
}
