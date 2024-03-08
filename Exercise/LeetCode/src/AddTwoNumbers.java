import java.math.BigDecimal;

public class AddTwoNumbers {

    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(9);
        ListNode node3 = new ListNode(9);
        ListNode node4 = new ListNode(9);
        ListNode node5 = new ListNode(9);
        ListNode node6 = new ListNode(9);
        ListNode node7 = new ListNode(9);
        ListNode node8 = new ListNode(9);
        ListNode node9 = new ListNode(9);
        ListNode node10 = new ListNode(9);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = node10;

        ListNode n1 = new ListNode(9);



        addTwoNumbers(n1, node1);

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigDecimal sumL1 = BigDecimal.ZERO;
        BigDecimal multiplier = BigDecimal.ONE;
        ListNode currentL1 = l1;
        while (currentL1 != null) {
           sumL1 = sumL1.add(multiplier.multiply(BigDecimal.valueOf(currentL1.val)));
            multiplier = multiplier.multiply(BigDecimal.TEN);
            currentL1 = currentL1.next;
        }

        BigDecimal sumL2 = BigDecimal.ZERO;
         multiplier = BigDecimal.ONE;
        ListNode currentL2 = l2;
        while (currentL2 != null) {
            sumL2 = sumL2.add(multiplier.multiply(BigDecimal.valueOf(currentL2.val)));
            multiplier = multiplier.multiply(BigDecimal.TEN);
            currentL2 = currentL2.next;
        }

        BigDecimal totalSum = sumL1.add(sumL2);

        if (totalSum.equals(BigDecimal.ZERO)) {
            return new ListNode(0);
        }
        ListNode head = null;
        ListNode prev = null;

        while (totalSum.compareTo(BigDecimal.ZERO) > 0) {
            int lastDigit = totalSum.remainder(BigDecimal.TEN).intValue();
            totalSum = totalSum.divide(BigDecimal.TEN);
            ListNode newNode = new ListNode(lastDigit);

            if (head == null) {
                head = newNode;
                prev = newNode;
                continue;
            }


               prev.next = newNode;
               prev = newNode;

        }

        return head;

//        ListNode listNode = new ListNode(0);
//        ListNode currentNode = listNode;
//        int number = 0;
//        while (l1 != null || l2 != null || number != 0) {
//            int sum = 0;
//            if (l1 != null) {
//                sum += l1.val;
//                l1 = l1.next;
//            }
//            if (l2 != null) {
//                sum += l2.val;
//                l2 = l2.next;
//            }
//            sum += number;
//            number = sum / 10;
//            ListNode node = new ListNode(sum % 10);
//            currentNode.next = node;
//            currentNode = node;
//        }
//
//        return listNode.next;

    }
}

 class ListNode {
    int val;
    ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }}
