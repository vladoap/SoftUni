package implementations;

import interfaces.AbstractBinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTree<E> implements AbstractBinaryTree<E> {

    private E key;
    private BinaryTree<E> left;
    private BinaryTree<E> right;


    public BinaryTree(E key, BinaryTree<E> left, BinaryTree<E> right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return this.left;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return this.right;
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    @Override
    public String asIndentedPreOrder(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(getPadding(indent)).append(this.getKey());

        if (this.left != null) {
            String result = this.left.asIndentedPreOrder(indent + 2);
            sb.append(System.lineSeparator()).append(result);
        }

        if (this.right != null) {
            String result = this.right.asIndentedPreOrder(indent + 2);
            sb.append(System.lineSeparator()).append(result);
        }
        return sb.toString();
    }

    private String getPadding(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        return null;
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        return null;
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        return null;
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {

    }
}
