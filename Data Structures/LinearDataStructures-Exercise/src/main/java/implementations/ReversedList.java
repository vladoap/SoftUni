package implementations;

import interfaces.ReversedListIntf;

import java.util.Arrays;
import java.util.Iterator;

public class ReversedList<E> implements ReversedListIntf<E> {

    private static final int DEFAULT_CAPACITY = 2;
    private int size;
    private Object[] elements;

    public ReversedList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(E element) {
        if (this.size == this.elements.length) {
            grow();
        }
        this.elements[size++] = element;
    }

    @Override
    public E removeAt(int index) {
        validateIndex(index);
        
        E element = get(index);
        this.elements[this.size - 1 - index] = null;
        size--;
        shift(index);
        return element;
    }

    private void shift(int index) {
        for (int i = index; i > 0 ; i--) {
            this.elements[i] = this.elements[i - 1];
        }
        this.elements[0] = null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        validateIndex(index);
        return (E) this.elements[size - 1 - index];
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index " + index);
        }
    }

    @Override
    public int capacity() {
        return this.elements.length;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = size - 1;
            @Override
            public boolean hasNext() {
                return index > 0;
            }

            @Override
            public E next() {
                return get(index);
            }
        };
    }

    private void grow() {
        this.elements = Arrays.copyOf(this.elements, this.elements.length * 2);
    }
}
