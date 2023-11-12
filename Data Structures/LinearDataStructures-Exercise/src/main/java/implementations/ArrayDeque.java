package implementations;

import interfaces.Deque;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {

    private final int DEFAULT_CAPACITY = 7;
    private int head;
    private int tail;
    private int size;
    private Object[] elements;

    public ArrayDeque() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.head = this.elements.length / 2;
        this.tail = this.head;
    }

    @Override
    public void add(E element) {
       addLast(element);
    }


    @Override
    public void offer(E element) {
         addLast(element);
    }

    @Override
    public void addFirst(E element) {
         if (this.size == 0) {
             addLast(element);
         } else {
             if (this.head == 0) {
                 grow();
             }
             this.elements[--this.head] = element;
             this.size++;
         }
    }

    @Override
    public void addLast(E element) {
        if (this.size == 0) {
            this.elements[this.tail] = element;
        } else {
            if (this.tail == this.elements.length - 1) {
                grow();
            }
            elements[++tail] = element;
        }
        this.size++;
    }

    @Override
    public void push(E element) {
          addFirst(element);
    }

    @Override
    public void insert(int index, E element) {
        int realIndex = this.head + index;
        ensureValidIndex(realIndex);

        if (realIndex - this.head < this.tail - realIndex) {
            insertAndShiftLeft(index, element);
        } else {
            insertAndShiftRight(index, element);
        }
        size++;

    }

    @Override
    public void set(int index, E element) {
        int realIndex = this.head + index;
        ensureValidIndex(realIndex);

        this.elements[realIndex] = element;
    }

    @Override
    public E peek() {
        if (this.size != 0) {
          return this.getAt(this.head);
        }
        return null;
    }

    @Override
    public E poll() {
       if (this.size != 0) {
        return this.removeFirst();
       }
        return null;
    }

    @Override
    public E pop() {
        if (this.size != 0) {
            return this.removeFirst();
        }
        return null;
    }

    @Override
    public E get(int index) {
        int realIndex = this.head + index;
       ensureValidIndex(realIndex);
        return this.getAt(realIndex);
    }

    @Override
    public E get(Object object) {
        if (isEmpty()) {
            return null;
        }
        for (int i = this.head; i <= this.tail ; i++) {
            if (this.elements[i].equals(object)) {
                return this.getAt(i);
            }
        }
        return null;
    }

    @Override
    public E remove(int index) {
        int realIndex = this.head + index;
        ensureValidIndex(realIndex);
        E element = this.getAt(realIndex);
        if (realIndex - this.head < this.tail - realIndex) {
            removeAndShiftRight(realIndex, element);
        } else {
            removeAndShiftLeft(realIndex, element);
        }
        size--;

        return element;
    }

    @Override
    public E remove(Object object) {
        if (isEmpty()) {
            return null;
        }
        int index = getIndexOf(object);
        if (index == -1) {
            return null;
        }
        return this.remove(index);
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E element = this.getAt(this.head);
        this.elements[this.head++] = null;
        size--;
        return element;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        E element = this.getAt(this.tail);
        this.elements[this.tail--] = null;
        size--;
        return element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.elements.length;
    }

    @Override
    public void trimToSize() {
        this.elements = Arrays.copyOf(this.elements, this.size);
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = head;
            @Override
            public boolean hasNext() {
                return this.index <= tail;
            }

            @Override
            public E next() {
                return getAt(index++);
            }
        };
    }

    private void grow() {
        int newCapacity = this.elements.length * 2 + 1;
        Object[] newElements = new Object[newCapacity];

        int middle = newCapacity / 2;

        int begin = middle - size / 2;

        int end = middle + size / 2;
        int index = this.head;

        for (int i = begin; i < end; i++) {
            newElements[i] = this.elements[index++];
        }

        this.elements = newElements;
        this.head = begin;
        this.tail = end - 1;
    }

    @SuppressWarnings("unchecked")
    private E getAt(int index) {
        return (E) this.elements[index];
    }

    private void ensureValidIndex(int index) {
        if (index < this.head || index > this.tail) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
    }

    private void removeAndShiftRight(int index, E element) {
        for (int i = index; i > this.head ; i--) {
            this.elements[i] = this.elements[--i];
        }

        this.elements[this.head++] = null;
    }

    private void removeAndShiftLeft(int index, E element) {
        for (int i = index; i < this.tail ; i++) {
            this.elements[i] = this.elements[++i];
        }

        this.elements[this.tail--] = null;
    }

    private int getIndexOf(Object object) {
        for (int i = this.head; i <= this.tail ; i++) {
            if (this.elements[i].equals(object)) {
                return i - this.head;
            }
        }
        return -1;
    }


    private void insertAndShiftLeft(int index, E element) {
        if (this.head == 0) {
            grow();
        }
        int realIndex = index + this.head;

        for (int i = this.head; i <= realIndex ; i++) {
            this.elements[i - 1] = this.elements[i];
        }

        this.elements[realIndex] = element;
        this.head--;
    }

    private void insertAndShiftRight(int index, E element) {
        if (this.tail == this.elements.length - 1) {
            grow();
        }
        int realIndex = index + this.head;

        for (int i = this.tail; i >= realIndex ; i--) {
            this.elements[i + 1] = this.elements[i];
        }

        this.elements[realIndex] = element;
        this.tail++;
    }
}
