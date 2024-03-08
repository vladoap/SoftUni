import java.util.*;

public class CopyListWithRandomPointer {

   static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public static void main(String[] args) {

    }

    public static Node copyRandomList(Node head) {
       if (head == null) {
           return null;
       }
            Map<Node, Node> map = new HashMap<>();

            Node current = head;
            while (current != null) {
                map.put(current, new Node(current.val));
                current = current.next;
            }


            current = head;
            while (current != null) {
                map.get(current).next = map.get(current.next);
                map.get(current).random = map.get(current.random);
                current = current.next;
            }

            return map.get(head);

    }
}
