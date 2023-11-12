package implementations;

import interfaces.List;

import java.sql.Array;
import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {

    private static final int INITIAL_ARRAY_LENGTH = 7;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[INITIAL_ARRAY_LENGTH];
    }

    @Override
    public boolean add(E element) {
        ensureLength();
        elements[size++] = element;
        return true;
    }


    @Override
    public boolean add(int index, E element) {
        validateIndex(index);
        ensureLength();

        for (int i = size; i > index ; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        validateIndex(index);
        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        E oldElement = get(index);
        elements[index] = element;

        return oldElement;
    }

    @Override
    public E remove(int index) {
        validateIndex(index);

        E element = get(index);
        elements[index] = null;
        size--;
        shift(index);
        return element;
    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index " + index);
        }
    }

    private void ensureLength() {
        if (size == elements.length) {
            grow();
        } else if (size < elements.length / 3) {
            shrink();
        }
    }

    private void shrink() {
        elements = Arrays.copyOf(elements, elements.length / 2);
    }

    private void grow() {
        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    private void shift(int index) {
        for (int i = index; i < size ; i++) {
            elements[i] = elements[i + 1];
        }

        elements[size] = null;
    }

}
