package Week5;

import java.util.*;

public class DFSGraph {
    private static List<List<Integer>> graph;
    private static boolean[] visited;
    private static List<Integer> dfsOrder;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of nodes and edges
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // Initialize the adjacency list
        graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // Read edges and populate the graph
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // Sort the adjacency list for lexicographic order
        for (int i = 1; i <= n; i++) {
            Collections.sort(graph.get(i));
        }

        // Initialize visited array and DFS order list
        visited = new boolean[n + 1];
        dfsOrder = new ArrayList<>();

        // Perform DFS starting from the smallest node (1)
        for (int start = 1; start <= n; start++) {
            if (!visited[start]) {
                dfs(start);
            }
        }

        // Output the DFS order
        for (int i = 0; i < dfsOrder.size(); i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(dfsOrder.get(i));
        }

        scanner.close();
    }

    private static void dfs(int node) {
        visited[node] = true;
        dfsOrder.add(node);

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
}