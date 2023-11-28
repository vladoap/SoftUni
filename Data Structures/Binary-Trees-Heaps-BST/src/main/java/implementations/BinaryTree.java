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
        List<AbstractBinaryTree<E>> result = new ArrayList<>();

        preOrderDfsTraverse(this, result);

        return result;
    }

    private void preOrderDfsTraverse(BinaryTree<E> tree, List<AbstractBinaryTree<E>> result) {
        result.add(tree);
        if (tree.getLeft() != null) {
            preOrderDfsTraverse(tree.left, result);
        }

        if (tree.getRight() != null) {
            preOrderDfsTraverse(tree.right, result);
        }
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {

        List<AbstractBinaryTree<E>> result = new ArrayList<>();

        inOrderDfsTraverse(this, result);

        return result;
    }

    private void inOrderDfsTraverse(BinaryTree<E> tree, List<AbstractBinaryTree<E>> result) {
        if (tree.getLeft() != null) {
            inOrderDfsTraverse(tree.left, result);
        }
        result.add(tree);

        if (tree.getRight() != null) {
            inOrderDfsTraverse(tree.right, result);
        }
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();

        postOrderDfsTraverse(this, result);

        return result;
    }

    private void postOrderDfsTraverse(BinaryTree<E> tree, List<AbstractBinaryTree<E>> result) {
        if (tree.getLeft() != null) {
            postOrderDfsTraverse(tree.left, result);
        }

        if (tree.getRight() != null) {
            postOrderDfsTraverse(tree.right, result);
        }
        result.add(tree);
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {

        inOrderForEach(this, consumer);
    }

    private void inOrderForEach(BinaryTree<E> tree, Consumer<E> consumer) {
        if (tree.getLeft() != null) {
            inOrderForEach(tree.left, consumer);
        }

        consumer.accept(tree.getKey());

        if (tree.getRight() != null) {
            inOrderForEach(tree.right, consumer);
        }
    }
}
