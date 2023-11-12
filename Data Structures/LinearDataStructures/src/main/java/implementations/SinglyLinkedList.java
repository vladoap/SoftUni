package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {

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
    public void addFirst(E element) {
        Node<E> newElement = new Node<>(element);
        if (size != 0) {
            newElement.next = this.head;
        }
        this.head = newElement;

        size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newElement = new Node<>(element);
        if (size == 0) {
            this.head = newElement;
        } else {
            Node<E> current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newElement;
        }

        size++;
    }

    @Override
    public E removeFirst() {
        ensureNotEmpty();
        Node<E> current = this.head;
        this.head = this.head.next;

        current.next = null;
        size--;
        return current.element;
    }

    @Override
    public E removeLast() {
        ensureNotEmpty();
        Node<E> current = this.head;
        Node<E> prev = null;
        while (current.next != null) {
            prev = current;
            current = current.next;
        }
        this.head = prev;
        prev.next = null;
        size--;
        return current.element;
    }

    @Override
    public E getFirst() {
        ensureNotEmpty();
        return this.head.element;
    }

    private void ensureNotEmpty() {
        if (size == 0) {
            throw new IllegalStateException("Empty list.");
        }
    }

    @Override
    public E getLast() {
        ensureNotEmpty();
        Node<E> current = this.head;
        while (current.next != null) {
            current = current.next;
        }

        return current.element;
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
        return null;
    }
}
