package Week6;

import java.util.*;

public class ShortestPathsTwoNodes {
    static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read n (number of nodes) and m (number of edges)
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // Create the graph as an adjacency list
        List<List<Edge>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // Read edges
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            graph.get(u).add(new Edge(v, w)); // Directed graph
        }

        // Read source and target nodes
        int s = scanner.nextInt();
        int t = scanner.nextInt();

        // Find the shortest path using Dijkstra's algorithm
        int shortestPathWeight = dijkstra(graph, n, s, t);
        System.out.println(shortestPathWeight);
    }

    private static int dijkstra(List<List<Edge>> graph, int n, int start, int target) {
        // Array to store the minimum distance from start to all nodes
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // Priority queue for the Dijkstra's algorithm
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{start, 0}); // {node, distance}

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentNode = current[0];
            int currentDist = current[1];

            // If we reach the target node, return the distance
            if (currentNode == target) {
                return currentDist;
            }

            // Explore neighbors
            for (Edge edge : graph.get(currentNode)) {
                int nextNode = edge.to;
                int newDist = currentDist + edge.weight;

                // If a shorter path to the neighbor is found
                if (newDist < dist[nextNode]) {
                    dist[nextNode] = newDist;
                    pq.offer(new int[]{nextNode, newDist});
                }
            }
        }

        // If the target node is unreachable, return -1
        return dist[target] == Integer.MAX_VALUE ? -1 : dist[target];
    }
}