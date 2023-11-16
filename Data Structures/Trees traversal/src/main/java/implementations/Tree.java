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


    public Tree(E value, Tree<E>... subtrees) {
        this.value = value;
        this.parent = null;
        this.children = new ArrayList<>();

        for (Tree<E> subtree : subtrees) {
            this.children.add(subtree);
            subtree.parent = this;
        }
    }

    @Override
    public List<E> orderBfs() {
        List<E> result = new ArrayList<>();

        Deque<Tree<E>> deque = new ArrayDeque<>();
        deque.offer(this);

        while (!deque.isEmpty()) {
            Tree<E> element = deque.poll();

            if (element.value != null) {
                result.add(element.value);
            }

            for (Tree<E> child : element.children) {
                deque.offer(child);
            }
        }

        return result;
    }

    @Override
    public List<E> orderDfs() {
        List<E> result = new ArrayList<>();

        doDfs(this, result);

        return result;
    }


    public List<E> orderDfsStack() {
        List<E> result = new ArrayList<>();

        Deque<Tree<E>> deque = new ArrayDeque<>();
        deque.offer(this);

        while (!deque.isEmpty()) {
            Tree<E> element = deque.pop();

            result.add(element.value);

            for (Tree<E> child : element.children) {
                deque.push(child);
            }
        }

        return result;
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {
        Tree<E> element = this.findByParentKey(parentKey);

        if (element == null) {
            throw new IllegalArgumentException();
        }

        element.children.add(child);
        child.parent = element;
    }


    @Override
    public void removeNode(E nodeKey) {
        Tree<E> nodeToRemove = this.findByParentKey(nodeKey);

        if (nodeToRemove == null) {
            throw new IllegalArgumentException();
        }

        Tree<E> parent = nodeToRemove.parent;

        if (parent != null) {
            parent.children.remove(nodeToRemove);
        }

        for (Tree<E> child : nodeToRemove.children) {
            child.parent = null;
            child.children.clear();
        }

        nodeToRemove.parent = null;
        nodeToRemove.children.clear();
        nodeToRemove.value = null;


    }

    @Override
    public void swap(E firstKey, E secondKey) {
        Tree<E> firstNode = findByParentKey(firstKey);
        Tree<E> secondNode = findByParentKey(secondKey);

        if (firstNode == null || secondNode == null) {
            throw new IllegalArgumentException();
        }

        Tree<E> firstParent = firstNode.parent;
        Tree<E> secondParent = secondNode.parent;

        if (firstParent == null) {
            this.value = secondNode.value;
            this.parent = null;
            this.children = secondNode.children;
            return;
        } else if (secondParent == null) {
            this.value = firstNode.value;
            this.parent = null;
            this.children = firstNode.children;
            return;
        }

        firstNode.parent = secondParent;
        secondNode.parent = firstParent;

        int firstIndex = firstParent.children.indexOf(firstNode);
        int secondIndex = secondParent.children.indexOf(secondNode);

        firstParent.children.set(firstIndex, secondNode);
        secondParent.children.set(secondIndex, firstNode);


    }

    private Tree<E> findByParentKey(E parentKey) {
        Deque<Tree<E>> deque = new ArrayDeque<>();
        deque.offer(this);

        while (!deque.isEmpty()) {
            Tree<E> element = deque.poll();

            if (element.value.equals(parentKey)) {
                return element;
            }

            for (Tree<E> child : element.children) {
                deque.offer(child);
            }
        }

        return null;
    }

    private void doDfs(Tree<E> tree, List<E> result) {

        E element = tree.value;

        for (Tree<E> child : tree.children) {
            doDfs(child, result);
        }

        result.add(element);
    }

}



