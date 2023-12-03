package solutions;

import interfaces.Decrease;
import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinHeap<E extends Comparable<E> & Decrease<E>> implements Heap<E> {

    List<E> elements;

    public MinHeap() {
        this.elements = new ArrayList<>();
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

        while (index > 0 && isGreater(getParentAt(index), getAt(index))) {
            int parentIndex = getParentIndex(index);
            Collections.swap(this.elements, parentIndex, index);

            index = parentIndex;
        }

    }


    @Override
    public E peek() {
        ensureNotEmpty();
        return this.elements.get(0);
    }



    @Override
    public E poll() {
        ensureNotEmpty();
        E elementToRemove = this.peek();
        Collections.swap(this.elements, 0, this.elements.size() - 1);

        this.elements.remove(this.elements.size() - 1);

        heapifyDown(0);

        return elementToRemove;
    }

    private void heapifyDown(int index) {

          while (getLeftChildIndex(index) < this.elements.size() && isGreater(getAt(index), getLeftChildAt(index))) {
              int childIndex = getLeftChildIndex(index);
              int rightChildIndex = getRightChildIndex(index);

              if (rightChildIndex < this.elements.size() && isLess(getAt(rightChildIndex), getAt(childIndex))) {
                  childIndex = rightChildIndex;
              }

              Collections.swap(this.elements, childIndex, index);
              index = childIndex;
          }
    }

    private E getRightChildAt(int index) {
        int rightChildIndex = getRightChildIndex(index);
        return this.getAt(rightChildIndex);
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private E getLeftChildAt(int index) {
        int leftChildIndex = getLeftChildIndex(index);
        return this.getAt(leftChildIndex);
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    @Override
    public void decrease(E element) {
        int elementIndex = this.elements.indexOf(element);
        E heapElement = this.elements.get(elementIndex);

        heapElement.decrease();

        heapifyUp(elementIndex);
    }

    private boolean isGreater(E parent, E child) {
        return parent.compareTo(child) > 0;
    }

    private boolean isLess(E parent, E child) {
        return parent.compareTo(child) < 0;
    }

    private E getParentAt(int index) {
        int parentIndex = this.getParentIndex(index);
        return this.getAt(parentIndex);
    }

    private E getAt(int index) {
        return this.elements.get(index);
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private void ensureNotEmpty() {
        if (this.elements.isEmpty()) {
            throw new IllegalStateException();
        }
    }
}
