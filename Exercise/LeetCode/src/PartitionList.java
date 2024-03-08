import java.util.Comparator;
import java.util.PriorityQueue;

public class PartitionList {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);

        int x = 3;

        partition(head, x);
    }

    public static ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        ListNode leftDummy = new ListNode(0);
        ListNode rightDummy = new ListNode(0);

        ListNode left = leftDummy;
        ListNode right = rightDummy;



        ListNode current = head;
        while (current != null) {
            if (current.val < x) {
                left.next = current;
                left = left.next;
            } else {
               right.next = current;
               right = right.next;
            }

            current = current.next;
        }

        right.next = null;
        left.next = rightDummy.next;

        return leftDummy.next;
    }
}
