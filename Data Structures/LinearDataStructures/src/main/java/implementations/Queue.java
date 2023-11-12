package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {

    private Node<E> head;
    private int size;

    private static class Node<E> {
        E element;
        Node<E> next;

        private Node(E element) {
            this.element = element;
        }
    }

    @Override
    public void offer(E element) {
        Node<E> newElement = new Node<>(element);
        if (size == 0) {
            this.head = newElement;
        } else {
            Node<E> lastNode = this.head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }

            lastNode.next = newElement;
        }

        size++;
    }

    @Override
    public E poll() {
        ensureNotEmpty();
        Node<E> currentHead = this.head;

        this.head = this.head.next;
        currentHead.next = null;

        size--;
        return currentHead.element;
    }


    @Override
    public E peek() {
        ensureNotEmpty();
        return this.head.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E element = current.element;
                current = current.next;

                return element;
            }
        };
    }

    private void ensureNotEmpty() {
        if (size == 0) {
            throw new IllegalStateException("Empty queue.");
        }
    }
}
