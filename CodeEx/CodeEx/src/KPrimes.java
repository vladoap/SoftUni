import java.util.ArrayList;
import java.util.List;

public class KPrimes {

    private static boolean isPrime(long number) {
        if (number < 2) {
            return false;
        }

        for (long i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static long[] countKprimes(int k, long start, long end) {
        List<Long> result = new ArrayList<>();
        for (long i = start; i <= end; i++) {
            if (i == 0) {
                continue;
            }
            int factorCount = 0;
            long current = i;

            for (long prime = 2; prime <= current; prime++) {
                while (isPrime(prime) && current % prime == 0) {
                    current /= prime;
                    factorCount++;
                }
            }

            if (current == 1 && factorCount == k) {
                result.add(i);
            }
        }

        long[] array = result.stream().mapToLong(Long::longValue).toArray();

        return array.length > 0 ? array : null;
    }

    public static void main(String[] args) {
        long[] longs = countKprimes(5, 500L, 600L);

        System.out.println();
    }


}
