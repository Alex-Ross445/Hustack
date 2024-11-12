package Week6;

import java.util.*;

public class ShortestPathsAllPair {

    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read n (number of nodes) and m (number of edges)
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // Initialize distance matrix
        long[][] dist = new long[n + 1][n + 1];

        // Fill the distance matrix
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    dist[i][j] = 0; // Distance to itself is 0
                } else {
                    dist[i][j] = INF; // Initially set to infinity
                }
            }
        }

        // Read the edges and update the distance matrix
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            dist[u][v] = Math.min(dist[u][v], w); // In case of multiple edges, take the minimum weight
        }

        // Floyd-Warshall algorithm
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] < INF && dist[k][j] < INF) { // Check for overflow
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // Output the result
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("-1 ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }
}
