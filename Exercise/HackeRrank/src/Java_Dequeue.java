import java.util.*;
import java.util.stream.Collectors;

public class Java_Dequeue {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        int n = scanner.nextInt();
        int m = scanner.nextInt();


        int countUnique = 0;
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            deque.addLast(num);
            set.add(num);

            if (deque.size() == m) {
                if (set.size() > countUnique) {
                    countUnique = set.size();
                }

                int first = deque.removeFirst();
                if (!deque.contains(first)) {
                    set.remove(first);
                }
            }

        }

        System.out.println(countUnique);










    }
}
