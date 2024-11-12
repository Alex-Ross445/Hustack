package Week5;

import java.util.*;

public class BFSGraph {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of nodes and edges
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // Create an adjacency list for the graph
        List<List<Integer>> graph = new ArrayList<>(n + 1);
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

        // Sort the adjacency list to ensure lexicographic order
        for (int i = 1; i <= n; i++) {
            Collections.sort(graph.get(i));
        }

        // Perform BFS
        boolean[] visited = new boolean[n + 1];
        List<Integer> bfsOrder = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        // Start BFS from the smallest node (1)
        for (int start = 1; start <= n; start++) {
            if (!visited[start]) {
                queue.offer(start);
                visited[start] = true;

                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    bfsOrder.add(node);

                    for (int neighbor : graph.get(node)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.offer(neighbor);
                        }
                    }
                }
            }
        }

        // Output the BFS order
        for (int i = 0; i < bfsOrder.size(); i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(bfsOrder.get(i));
        }
        scanner.close();
    }
}
