package implementations;

import interfaces.AbstractQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {

    List<E> elements;

    public PriorityQueue() {
        elements = new ArrayList<>();
    }

    @Override
    public int size() {
     return this.elements.size();
    }


    @Override
    public void add(E element) {
        this.elements.add(element);
        heapifyUp(this.elements.size() - 1);
    }

    @Override
    public E peek() {
        ensureNotEmpty();
        return this.elements.get(0);
    }

    @Override
    public E poll() {
        E elementToRemove = this.peek();
        Collections.swap(this.elements, 0, this.elements.size() - 1);
        this.elements.remove(this.elements.size() - 1);

        heapifyDown(0);

        return elementToRemove;
    }

    private void heapifyDown(int index) {

        while (getLeftChildIndex(index) < this.elements.size() && isLess(getAt(index), getAt(getLeftChildIndex(index)))) {
            int childIndex = getLeftChildIndex(index);
            int rightChildIndex = getRightChildIndex(index);

            if (getRightChildIndex(index) < this.elements.size() && isLess(getAt(childIndex), getAt(rightChildIndex))) {
                childIndex = rightChildIndex;
            }

            Collections.swap(this.elements, childIndex, index);
            index = childIndex;
        }

    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private void ensureNotEmpty() {
        if (this.elements.size() == 0) {
            throw new IllegalStateException();
        }
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private boolean isLess(E parent, E child) {
        return parent.compareTo(child) < 0;
    }

    private E getParentAt(int index) {
        index = (index - 1) / 2;
        return this.getAt(index);
    }

    private E getAt(int index) {
        return this.elements.get(index);
    }

    private void heapifyUp(int index) {

        while (index > 0 && isLess(getParentAt(index), getAt(index))) {
            int parentIndex = getParentIndex(index);
            Collections.swap(this.elements, parentIndex, index);

            index = parentIndex;
        }
    }
}
