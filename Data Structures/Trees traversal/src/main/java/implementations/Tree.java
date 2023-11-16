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

            result.add(element.value);

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

    private void doDfs(Tree<E> tree, List<E> result) {

        E element = tree.value;

        for (Tree<E> child : tree.children) {
            doDfs(child, result);
        }

        result.add(element);
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

    }
	
	@Override
    public void removeNode(E nodeKey) {

    }

    @Override
    public void swap(E firstKey, E secondKey) {

    }
}



