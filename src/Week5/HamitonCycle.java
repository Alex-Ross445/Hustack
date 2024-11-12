package Week5;

import java.util.*;

public class HamitonCycle {
    private static int n, m;
    private static List<List<Integer>> graph;
    private static boolean[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt(); // Number of graphs

        for (int t = 0; t < T; t++) {
            n = scanner.nextInt();
            m = scanner.nextInt();
            graph = new ArrayList<>(n + 1);
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            // Read edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            // Initialize visited array
            visited = new boolean[n + 1];
            if (isHamiltonian()) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }

        scanner.close();
    }

    private static boolean isHamiltonian() {
        // Try to find a Hamiltonian cycle starting from the first vertex (1)
        visited[1] = true; // Start from vertex 1
        return dfs(1, 1); // Start DFS from vertex 1 with depth 1
    }

    private static boolean dfs(int curr, int depth) {
        if (depth == n) {
            // Check if we can return to the starting vertex
            for (int neighbor : graph.get(curr)) {
                if (neighbor == 1) {
                    return true; // Found a Hamiltonian cycle
                }
            }
            return false; // No cycle found
        }

        // Explore all neighbors
        for (int neighbor : graph.get(curr)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                if (dfs(neighbor, depth + 1)) {
                    return true; // Found a Hamiltonian cycle
                }
                visited[neighbor] = false; // Backtrack
            }
        }
        return false; // No cycle found in this path
    }
}