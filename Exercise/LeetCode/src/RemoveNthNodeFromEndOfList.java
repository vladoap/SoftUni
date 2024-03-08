import java.util.ArrayList;
import java.util.List;

public class RemoveNthNodeFromEndOfList {

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
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        removeNthFromEnd(head, 2);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }

        List<ListNode> nodes = new ArrayList<>();

        ListNode current = head;
        while (current != null) {
            nodes.add(current);
            current = current.next;
        }

        ListNode nodeToRemove = nodes.get(nodes.size() - n);

        ListNode prev = null;
        if (n != nodes.size()) {
            prev = nodes.get(nodes.size() - n - 1);
        } else {
            return nodes.get(1);
        }

        prev.next = nodeToRemove.next;

        return nodes.get(0);
    }
}
