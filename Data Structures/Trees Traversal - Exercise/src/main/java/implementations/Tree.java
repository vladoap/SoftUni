package implementations;

import interfaces.AbstractTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {

    private E value;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E value, Tree<E>... children) {
        this.value = value;
        this.children = new ArrayList<>();

        for (Tree<E> child : children) {
            child.setParent(this);
            this.children.add(child);
        }
    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
         this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public E getKey() {
        return this.value;
    }

    @Override
    public String getAsString() {
        StringBuilder sb = new StringBuilder();

        doDfs(this, sb, 0);

       return sb.toString().trim();
    }

    private void doDfs(Tree<E> tree, StringBuilder sb, int indent) {
        sb
                .append(getPadding(indent))
                .append(tree.getKey())
                .append(System.lineSeparator());

        for (Tree<E> child : tree.children) {
            doDfs(child, sb, indent + 2);
        }

    }

    private String getPadding(int indent) {
        StringBuilder sb = new StringBuilder();

        sb.append(" ".repeat(Math.max(0, indent)));
        return sb.toString();
    }

    @Override
    public List<E> getLeafKeys() {
        List<E> leafs = new ArrayList<>();

        doBfs(this, leafs);

        return leafs;
    }

    private void doBfs(Tree<E> tree, List<E> leafs) {
        Deque<Tree<E>> deque = new ArrayDeque<>();
        deque.offer(tree);

        while (!deque.isEmpty()) {
            Tree<E> element = deque.poll();

            if (element.children.size() == 0) {
                leafs.add(element.getKey());
            }

            for (Tree<E> child : element.children) {
                deque.offer(child);
            }
        }
    }

    @Override
    public List<E> getMiddleKeys() {
        return null;
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
        return null;
    }

    @Override
    public List<E> getLongestPath() {
        return null;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        return null;
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        return null;
    }
}



