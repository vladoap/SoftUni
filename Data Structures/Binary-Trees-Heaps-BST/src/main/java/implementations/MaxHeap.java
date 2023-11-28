package implementations;

import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {

    List<E> elements;

    public MaxHeap() {
        elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.heapifyUp(this.elements.size() - 1);
    }


    private void heapifyUp(int index) {

        while (index > 0 && isLess(getParentAt(index), getAt(index))) {
            int parentIndex = getParentIndex(index);
            Collections.swap(this.elements, parentIndex, index);

            index = parentIndex;
        }
    }

    private boolean isLess(E parent, E child) {
        return parent.compareTo(child) < 0;
    }

    private E getParentAt(int index) {
         index = (index - 1) / 2;
         return this.getAt(index);
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private E getAt(int index) {
        return this.elements.get(index);
    }

    @Override
    public E peek() {
        ensureNotEmpty();
        return this.elements.get(0);
    }

    private void ensureNotEmpty() {
        if (this.elements.isEmpty()) {
            throw new IllegalStateException();
        }
    }
}
