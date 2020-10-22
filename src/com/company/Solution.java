package com.company;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] array = new int[] {5,3,4,2,1,1,2,1,8,4,4,9,13,5,8};
        System.out.println();
    }

    // Time O(V + E)
    // Space O(V)
    public boolean isBipartite(List<GraphNode> graph) {
        // write your solution here
        if (graph == null) {
            return true;
        }
        HashMap<GraphNode, Integer> visited= new HashMap<>();
        for (GraphNode node : graph) {
            if (!BFS(node, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean BFS(GraphNode node, HashMap<GraphNode, Integer> visited) {
        if (visited.containsKey(node)) {
            return true;
        }
        visited.put(node, 1);
        Queue<GraphNode> queue = new ArrayDeque<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            GraphNode curr = queue.poll();
            int currGroup = visited.get(curr);
            int neiGroup = currGroup == 1 ? 0 : 1;
            for (GraphNode nei : node.neighbors) {
                if (visited.containsKey(nei) && visited.get(nei) != neiGroup) {
                    return false;
                } else {
                    queue.offer(nei);
                    visited.put(nei, neiGroup);
                }
            }
        }
        return true;
    }
}


