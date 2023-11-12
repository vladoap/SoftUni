package implementations;

import interfaces.AbstractStack;

import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {

    private int size;
    private Node<E> top;

    private static class Node<E>  {
         E element;
         Node<E> prev;

        private Node(E element) {
            this.element = element;
        }
    }


    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(element);
        if (size != 0) {
            newNode.prev = this.top;
        }
        this.top = newNode;

        size++;
    }

    @Override
    public E pop() {
        ensureNotEmpty();

        Node<E> currentTop = this.top;

        this.top = currentTop.prev;
        currentTop.prev = null;

        size--;
        return currentTop.element;
    }

    @Override
    public E peek() {
        ensureNotEmpty();
        return this.top.element;
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
            private Node<E> current = top;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E element = current.element;
                current = current.prev;
                return element;
            }
        };
    }

    private void ensureNotEmpty() {
        if (size == 0) {
            throw new IllegalStateException("Empty stack.");
        }
    }
}
