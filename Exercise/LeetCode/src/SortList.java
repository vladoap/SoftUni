import java.util.Comparator;
import java.util.PriorityQueue;

public class SortList {

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private static class ListComparator implements Comparator<ListNode> {

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return Integer.compare(o1.val, o2.val);
        }
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        sortList(head);
    }

    public static ListNode sortList(ListNode head) {
        ListComparator comparator = new ListComparator();
        PriorityQueue<ListNode> queue = new PriorityQueue<>(comparator);

        ListNode current = head;

        while (current != null) {
            queue.offer(current);
            current = current.next;
        }

        ListNode dummy = new ListNode(0);
        ListNode newHead = dummy;

        while (!queue.isEmpty()) {
            newHead.next = queue.poll();
            newHead = newHead.next;

        }
        newHead.next = null;

        return dummy.next;
    }
}
