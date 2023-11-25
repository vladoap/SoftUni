package implementations;

import interfaces.AbstractTree;

import java.util.*;
import java.util.stream.Collectors;

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

        doDfsAsString(this, sb, 0);

       return sb.toString().trim();
    }


    @Override
    public List<E> getLeafKeys() {
        List<E> leafs = new ArrayList<>();

        doBfsForLeafKeys(this, leafs);

        return leafs;
    }


    @Override
    public List<E> getMiddleKeys() {
        List<E> leafs = new ArrayList<>();

        doBfsForMiddleKeys(this, leafs);

        return leafs;
    }



    @Override
    public Tree<E> getDeepestLeftmostNode() {
        List<Tree<E>> trees = traverseWithDfs();

        int maxDepth = 0;
        Tree<E> deppestedTree = null;

        for (Tree<E> tree : trees) {
            if (tree.isALeaf()) {
                int currentDepth = getTreeDepth(tree);
                if (currentDepth > maxDepth) {
                    maxDepth = currentDepth;
                    deppestedTree = tree;
                }
            }
        }

        return deppestedTree;
    }



    @Override
    public List<E> getLongestPath() {
        Tree<E> deepestNode = getDeepestLeftmostNode();

        List<E> keys = new ArrayList<>();

        while (deepestNode != null) {
            keys.add(deepestNode.getKey());
            deepestNode = deepestNode.getParent();
        }

        Collections.reverse(keys);

        return keys;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        List<Tree<E>> trees = traverseWithDfs();

        List<List<E>> sumOfPaths = new ArrayList<>();

        for (Tree<E> tree : trees) {
            if (tree.isALeaf()) {
              List<E> keys = getPathKeys(tree);

                int currentSum = keys.stream().mapToInt(e -> Integer.parseInt(e.toString())).sum();
                if (currentSum == sum) {
                    Collections.reverse(keys);
                    sumOfPaths.add(keys);
                }
            }
        }

        return sumOfPaths;
    }



    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        List<Tree<E>> trees = traverseWithDfs();

        List<Tree<E>> treesWithGivenSum = new ArrayList<>();

        for (Tree<E> tree : trees) {
            int currentSum = getKeysSum(tree);

                if (currentSum == sum) {
                    treesWithGivenSum.add(tree);
                }
        }

        return treesWithGivenSum;
    }

    private int getKeysSum(Tree<E> tree) {
        List<E> treeKeys = traverseTreeKeysWithDfs(tree);

        int sum = treeKeys.stream().mapToInt(k -> Integer.parseInt(k.toString())).sum();

        return sum;
    }

    private List<E> traverseTreeKeysWithDfs(Tree<E> tree) {
        List<Tree<E>> trees = new ArrayList<>();

        doDfs(tree, trees);

        return trees.stream().map(Tree::getKey).collect(Collectors.toList());
    }

    private void doBfsForLeafKeys(Tree<E> tree, List<E> leafs) {
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


    private void doBfsForMiddleKeys(Tree<E> tree, List<E> leafs) {
        Deque<Tree<E>> deque = new ArrayDeque<>();
        deque.offer(tree);

        while (!deque.isEmpty()) {
            Tree<E> element = deque.poll();

            if (element.getParent() != null && element.children.size() != 0) {
                leafs.add(element.getKey());
            }

            for (Tree<E> child : element.children) {
                deque.offer(child);
            }
        }
    }

    private int getTreeDepth(Tree<E> tree) {
        int counter = 0;
        Tree<E> current = tree;

        while (current != null) {
            counter++;
            current = current.parent;
        }

        return counter;
    }

    private boolean isALeaf() {
        return this.parent != null && this.children.size() == 0;
    }

    private List<Tree<E>> traverseWithDfs() {
        List<Tree<E>> trees = new ArrayList<>();

        doDfs(this, trees);
        return trees;
    }

    private void doDfs(Tree<E> tree, List<Tree<E>> trees) {
        trees.add(tree);

        for (Tree<E> child : tree.children) {
            doDfs(child, trees);
        }
    }

    private List<E> getPathKeys(Tree<E> tree) {
        List<E> pathKeys = new ArrayList<>();

        Tree<E> current = tree;
        while (current != null) {
            pathKeys.add(current.getKey());
            current = current.parent;
        }

        return pathKeys;
    }

    private void doDfsAsString(Tree<E> tree, StringBuilder sb, int indent) {
        sb
                .append(getPadding(indent))
                .append(tree.getKey())
                .append(System.lineSeparator());

        for (Tree<E> child : tree.children) {
            doDfsAsString(child, sb, indent + 2);
        }

    }

    private String getPadding(int indent) {
        StringBuilder sb = new StringBuilder();

        sb.append(" ".repeat(Math.max(0, indent)));
        return sb.toString();
    }


}



