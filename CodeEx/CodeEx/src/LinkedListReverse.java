class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListReverse {
    public static Node reverseList(Node head) {
         Node current = head;
         Node prev = null;
         Node next = null;

         while (current != null) {
             next = current.next;
             current.next = prev;
             prev = current;
             current = next;
         }

         return prev;
    }

    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        System.out.println("Original Linked List:");
        printList(head);

        head = reverseList(head);

        System.out.println("Reversed Linked List:");
        printList(head);
    }
}

