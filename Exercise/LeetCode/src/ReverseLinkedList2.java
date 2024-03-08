import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReverseLinkedList2 {

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

        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(4);
        node1.next.next.next.next = new ListNode(5);

        ListNode result = reverseBetween(node1, 2 ,4);
    }


    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }

        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;

        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        ListNode current = pre.next;
        ListNode previous = null;
        ListNode next = null;

        for (int i = 0; i <= right - left; i++) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        pre.next.next = current;
        pre.next = previous;


        return dummy.next;
    }
}
