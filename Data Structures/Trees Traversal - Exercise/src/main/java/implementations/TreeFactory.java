package implementations;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class TreeFactory {
    private Map<Integer, Tree<Integer>> nodesByKeys;

    public TreeFactory() {
        this.nodesByKeys = new LinkedHashMap<>();
    }

    public Tree<Integer> createTreeFromStrings(String[] input) {

        for (String line : input) {
            int[] keysAndValues = Arrays.stream(line.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int key = keysAndValues[0];
            int node = keysAndValues[1];

            addEdge(key, node);
        }
        return getRoot();
    }

    private Tree<Integer> getRoot() {
        for (Tree<Integer> node : nodesByKeys.values()) {
            if (node.getParent() == null) {
                return node;
            }
        }
        return null;
    }

    public Tree<Integer> createNodeByKey(int key) {
        nodesByKeys.putIfAbsent(key, new Tree<>(key));
        return nodesByKeys.get(key);
    }

    public void addEdge(int parent, int child) {
        Tree<Integer> parentNode = createNodeByKey(parent);
        Tree<Integer> childNode = createNodeByKey(child);

        parentNode.addChild(childNode);
        childNode.setParent(parentNode);
    }
}



