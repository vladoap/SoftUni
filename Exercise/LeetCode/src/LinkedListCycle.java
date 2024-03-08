import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {

    public static void main(String[] args) {


    }

    public static boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode current = head;

        while (current != null) {
            if (set.contains(current)) {
                return true;
            }
            set.add(current);
            current = current.next;

        }

        return false;
    }
}
