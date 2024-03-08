public class RotateList {
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

        int k = 2;

        rotateRight(new ListNode(1), 1);

    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode current = head;

        int size = 0;
        while (current != null) {
            current = current.next;
            size++;
        }
        k = k % size;

        if (k == 0) {
            return head;
        }

        ListNode newHead = head;
        for (int i = 0; i < size - k ; i++) {
            newHead = newHead.next;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = newHead;

        while (newHead.next != null) {
            newHead = newHead.next;
        }

        current = head;
        for (int i = 0; i < size - k; i++) {
            newHead.next = current;
            newHead = current;
            current = current.next;

        }

        newHead.next = null;

        return dummy.next;
    }
}
