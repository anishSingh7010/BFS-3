// TC: O(v + e)
// SC: O(v)

// Approach: In a Bfs manner, keep cloning nodes and it's neighbors
// and put them in a map.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

class CloneGraph {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        visited.add(node);
        queue.add(node);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            cloneNode(map, current);

            // create neighbors
            for (Node neighbor : current.neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                }

                visited.add(neighbor);
                cloneNode(map, neighbor);
                // put in current's clone's neighbors attr
                map.get(current).neighbors.add(map.get(neighbor));
            }
        }

        return map.get(node);
    }

    private void cloneNode(Map<Node, Node> map, Node node) {
        if (!map.containsKey(node)) {
            // clone node
            Node clonedNode = new Node(node.val);
            map.put(node, clonedNode);
        }
    }
}