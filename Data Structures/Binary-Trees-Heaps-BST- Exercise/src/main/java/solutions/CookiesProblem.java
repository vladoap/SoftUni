package solutions;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class CookiesProblem {
    public Integer solve(int requiredSweetness, int[] cookiesSweetness) {
        Queue<Integer> cookies = new PriorityQueue<>();

        for (int sweetness : cookiesSweetness) {
            cookies.offer(sweetness);
        }

        int currentMinSweetness = cookies.peek();
        int steps = 0;

        while (currentMinSweetness < requiredSweetness && cookies.size() > 1) {
            int firstSweetness = cookies.poll();
            int secondSweetness = cookies.poll();

            int combinedSweetness = firstSweetness + secondSweetness * 2;

            cookies.add(combinedSweetness);
            currentMinSweetness = cookies.peek();

            steps++;
        }

        return currentMinSweetness < requiredSweetness ? -1 : steps;

    }
}
