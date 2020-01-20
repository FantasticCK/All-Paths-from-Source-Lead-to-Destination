package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

class Solution {
    public boolean leadsToDestination(int n, int[][] edges, int src, int des) {
        if (n == 0 || src >= n || des >= n)
            return false;
        int[] color = new int[n];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1];
            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(to);
        }
        if (!isFinal(graph, des))
            return false;
        return dfs(graph, n, src, des, color);
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, int n, int curr, int des, int[] color) {
        if (curr == des) {
            return true;
        }
        if (isFinal(graph, curr)) {
            return false;
        }

        color[curr] = 1;
        for (int i : graph.get(curr)) {
            if (color[i] == 2)
                continue;

            if (color[i] == 1 || !dfs(graph, n, i, des, color)) {
                return false;
            }
        }
        color[curr] = 2;
        return true;
    }

    private boolean isFinal(Map<Integer, List<Integer>> graph, int node) {
        return !graph.containsKey(node);
    }

}