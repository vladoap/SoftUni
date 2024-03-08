public class RemoveDuplicatesFromSortedList2 {

    public static class ListNode {
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
      head.next.next.next = new ListNode(3);
      head.next.next.next.next = new ListNode(4);
      head.next.next.next.next.next = new ListNode(4);
      head.next.next.next.next.next.next = new ListNode(5);

      deleteDuplicates(head);
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;

        ListNode current = head;
        while (current != null) {

            boolean foundDuplicates = false;
            while (current.next != null && current.val == current.next.val) {
                current = current.next;
                foundDuplicates = true;
            }
            if (foundDuplicates) {
                prev.next = current.next;
            } else {
                prev = current;
            }
            current = current.next;
        }

        return dummy.next;
    }
}
