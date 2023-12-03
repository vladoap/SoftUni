package solutions;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private int value;
    private BinaryTree first;
    private BinaryTree second;


    public BinaryTree(int key, BinaryTree first, BinaryTree second) {
      this.value = key;
      this.first = first;
      this.second = second;
    }

    public Integer findLowestCommonAncestor(int first, int second) {
       List<Integer> firstPath = findPath(first);
       List<Integer> secondPath = findPath(second);

        int smallerPath = Math.min(firstPath.size(), secondPath.size());

        int i = 0;
        for (; i < smallerPath; i++) {
            if (!firstPath.get(i).equals(secondPath.get(i))) {
                break;
            }
        }

        if (i == 0) {
            return 0;
        }
        return firstPath.get(i - 1);
    }

    private List<Integer> findPath(int element) {
        List<Integer> result = new ArrayList<>();
        
        findNodePath(this, element, result);
        
        return result;
    }

    private boolean findNodePath(BinaryTree binaryTree, int element, List<Integer> currentPath) {
        if (binaryTree == null) {
            return false;
        }

        if (binaryTree.value == element) {
            return true;
        }

        currentPath.add(binaryTree.value);

        if (findNodePath(binaryTree.first, element, currentPath)) {
            return true;
        }
        if (findNodePath(binaryTree.second, element, currentPath)) {
            return true;
        }

        currentPath.remove(Integer.valueOf(binaryTree.value));
        return false;
    }

    public List<Integer> topView() {
        return null;
    }
}
