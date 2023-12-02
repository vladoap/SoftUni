package implementations;

import interfaces.AbstractBinarySearchTree;

public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {

    private Node<E> root;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Node<E> root) {
        this.root = root;
    }



    @Override
    public void insert(E element) {
         Node<E> newNode = new Node<>(element);
         if (this.root == null) {
             this.root = newNode;
             return;
         }

         Node<E> parent = null;
         Node<E> current = this.root;

         while (current != null) {
             if (isLess(element, current.value)) {
                 parent = current;
                 current = current.leftChild;
             } else if (isGreater(element, current.value)) {
                 parent = current;
                 current = current.rightChild;
             } else {
                 return;
             }
         }

         if (isLess(element, parent.value)) {
             parent.leftChild = newNode;
         } else if (isGreater(element, parent.value)) {
             parent.rightChild = newNode;
         }

    }

    @Override
    public boolean contains(E element) {
        Node<E> current = this.root;

        while (current != null) {
            if (isLess(element, current.value)) {
                current = current.leftChild;
            } else if (isGreater(element, current.value)) {
                current = current.rightChild;
            } else {
                break;
            }
        }

        return current != null;
    }

    @Override
    public AbstractBinarySearchTree<E> search(E element) {
        Node<E> current = this.root;

        while (current != null) {
            if (isLess(element, current.value)) {
                current = current.leftChild;
            } else if (isGreater(element, current.value)) {
                current = current.rightChild;
            } else {
                break;
            }
        }

        return new BinarySearchTree<>(current);
    }

    @Override
    public Node<E> getRoot() {
        return this.root;
    }

    @Override
    public Node<E> getLeft() {
        return this.root.leftChild;
    }

    @Override
    public Node<E> getRight() {
        return this.root.rightChild;
    }

    @Override
    public E getValue() {
       return this.root.value;
    }

    public boolean isLess(E key, E parentKey) {
        return key.compareTo(parentKey) < 0;
    }

    public boolean isGreater(E key, E parentKey) {
        return key.compareTo(parentKey) > 0;
    }

    public boolean isEqual(E key, E parentKey) {
        return key.compareTo(parentKey) == 0;
    }
}
