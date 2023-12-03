import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.function.Consumer;

import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;

    public BinarySearchTree(Node<E> root) {
        this.root = root;
    }

    public BinarySearchTree() {
    }

    public static class Node<E> {
        private E value;
        private Node<E> leftChild;
        private Node<E> rightChild;
        private int count;

        public Node(E value) {
            this.value = value;
            this.count = 1;
        }

        public Node<E> getLeft() {
            return this.leftChild;
        }

        public Node<E> getRight() {
            return this.rightChild;
        }

        public E getValue() {
            return this.value;
        }
    }

    public void eachInOrder(Consumer<E> consumer) {
        eachInOrderTraverse(this.root, consumer);
    }

    private void eachInOrderTraverse(Node<E> tree, Consumer<E> consumer) {
        if (tree.getLeft() != null) {
            eachInOrderTraverse(tree.getLeft(), consumer);
        }

        consumer.accept(tree.getValue());
        if (tree.getRight() != null) {
            eachInOrderTraverse(tree.getRight(), consumer);
        }
    }

    public Node<E> getRoot() {
        return this.root;
    }

    public void insert(E element) {
        if (this.root == null) {
            this.root = new Node<>(element);
        } else {
            insertInto(this.root, element);
        }
    }

    private void insertInto(Node<E> node, E element) {
        if (isGreater(element, node.getValue())) {
            if (node.getRight() == null) {
                node.rightChild = new Node<>(element);
            } else {
                insertInto(node.getRight(), element);
            }
        } else if (isLess(element, node.getValue())) {
            if (node.getLeft() == null) {
                node.leftChild = new Node<>(element);
            } else {
                insertInto(node.getLeft(), element);
            }
        }

        node.count++;
    }

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

    public BinarySearchTree<E> search(E element) {
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

    public List<E> range(E lower, E upper) {
        List<E> result = new ArrayList<>();

        if (this.root == null) {
            return result;
        }

        Deque<Node<E>> deque = new ArrayDeque<>();
        deque.offer(this.root);

        while (!deque.isEmpty()) {
            Node<E> current = deque.poll();

            if (current.getLeft() != null) {
                deque.offer(current.getLeft());
            }
            if (current.getRight() != null) {
                deque.offer(current.getRight());
            }

            if (isLess(lower, current.getValue()) && isGreater(upper, current.getValue())) {
                result.add(current.getValue());
            } else if (isEqual(lower, current.getValue()) || isEqual(upper, current.getValue())) {
                result.add(current.getValue());
            }

        }
        return result;
    }

    public void deleteMin() {
        if (this.root == null) {
            throw new IllegalArgumentException();
        }

        if (this.root.getLeft() == null) {
            this.root = this.root.getRight();
            return;
        }

        Node<E> current = this.root;

        while (current.getLeft().getLeft() != null) {
            current.count--;
            current = current.getLeft();
        }

        current.count--;
        current.leftChild = current.getLeft().getRight();

    }

    public void deleteMax() {
        if (this.root == null) {
            throw new IllegalArgumentException();
        }

        if (this.root.getRight() == null) {
            this.root = this.root.getLeft();
            return;
        }

        Node<E> current = this.root;

        while (current.getRight().getRight() != null) {
            current.count--;
            current = current.getRight();
        }

        current.count--;
        current.rightChild = current.getRight().getLeft();
    }

    public int count() {
        return this.root == null ? 0 : this.root.count;
    }

    public int rank(E element) {
        return rankNode(this.root, element);
    }

    private int rankNode(Node<E> node, E element) {
        if (node == null) {
            return 0;
        }

        if (isLess(element, node.getValue())) {
            return rankNode(node.getLeft(), element);
        } else if (isEqual(element, node.getValue())) {
            return node.getLeft() == null  ? 0 : node.getLeft().count;
        }
            return node.getLeft().count + 1 + rankNode(node.getRight(), element);

    }

    public E ceil(E element) {
        if (this.root == null) {
            return null;
        }

        Node<E> nearestBigger = null;
        Node<E> current = this.root;

        while (current != null){
            if (isGreater(element, current.getValue())) {
                current = current.getRight();
            } else if (isLess(element, current.getValue())) {
                nearestBigger = current;
                current = current.getLeft();
            } else {
                Node<E> right = current.getRight();
                if (right != null && nearestBigger != null) {
                    nearestBigger = isLess(right.getValue(), nearestBigger.getValue()) ? right : nearestBigger;
                } else if (nearestBigger == null){
                    nearestBigger = right;
                }
                break;
            }
        }

        return nearestBigger == null ? null : nearestBigger.getValue();

    }

    public E floor(E element) {
        if (this.root == null) {
            return null;
        }

        Node<E> nearestSmaller = null;
        Node<E> current = this.root;

        while (current != null){
            if (isGreater(element, current.getValue())) {
                nearestSmaller = current;
                current = current.getRight();
            } else if (isLess(element, current.getValue())) {
                current = current.getLeft();
            } else {
                Node<E> left = current.getLeft();
                if (left != null && nearestSmaller != null) {
                    nearestSmaller = isGreater(left.getValue(), nearestSmaller.getValue()) ? left : nearestSmaller;
                } else if (nearestSmaller == null){
                    nearestSmaller = left;
                }
                break;
            }
        }

        return nearestSmaller == null ? null : nearestSmaller.getValue();

    }

    private boolean isLess(E key, E parentKey) {
        return key.compareTo(parentKey) < 0;
    }

    private boolean isGreater(E key, E parentKey) {
        return key.compareTo(parentKey) > 0;
    }

    private boolean isEqual(E key, E parentKey) {
        return key.compareTo(parentKey) == 0;
    }
}
