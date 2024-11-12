package Week6;

import java.util.*;

public class MaxFlow {
    static class Edge {
        int to, capacity, flow;
        Edge reverse;

        Edge(int to, int capacity) {
            this.to = to;
            this.capacity = capacity;
            this.flow = 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read N and M
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        // Read source and sink
        int s = scanner.nextInt();
        int t = scanner.nextInt();

        // Adjust for 0-based indexing
        s--;
        t--;

        // Create the graph
        List<List<Edge>> graph = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        // Read edges
        for (int i = 0; i < M; i++) {
            int u = scanner.nextInt() - 1; // Adjust to 0-based index
            int v = scanner.nextInt() - 1; // Adjust to 0-based index
            int capacity = scanner.nextInt();

            Edge forwardEdge = new Edge(v, capacity);
            Edge backwardEdge = new Edge(u, 0); // Reverse edge with 0 capacity

            // Link forward and backward edges
            forwardEdge.reverse = backwardEdge;
            backwardEdge.reverse = forwardEdge;

            graph.get(u).add(forwardEdge);
            graph.get(v).add(backwardEdge); // Add reverse edge to the graph
        }

        // Calculate the maximum flow
        int maxFlow = edmondsKarp(graph, s, t);
        System.out.println(maxFlow);
    }

    private static int edmondsKarp(List<List<Edge>> graph, int source, int sink) {
        int maxFlow = 0;
        while (true) {
            int flow = bfs(graph, source, sink);
            if (flow == 0) {
                break; // No more augmenting path
            }
            maxFlow += flow;
        }
        return maxFlow;
    }

    private static int bfs(List<List<Edge>> graph, int source, int sink) {
        int[] parent = new int[graph.size()];
        Arrays.fill(parent, -1);
        parent[source] = -2; // Mark the source as visited

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{source, Integer.MAX_VALUE});

        while (!queue.isEmpty()) {
            int current = queue.peek()[0];
            int flow = queue.poll()[1];

            for (Edge edge : graph.get(current)) {
                // Check if the edge can accommodate more flow
                if (parent[edge.to] == -1 && edge.flow < edge.capacity) {
                    parent[edge.to] = current;
                    int newFlow = Math.min(flow, edge.capacity - edge.flow);
                    if (edge.to == sink) {
                        // If we reach the sink, return the flow
                        updateResidualGraph(graph, parent, newFlow, source, sink);
                        return newFlow;
                    }
                    queue.add(new int[]{edge.to, newFlow});
                }
            }
        }
        return 0; // No path found
    }

    private static void updateResidualGraph(List<List<Edge>> graph, int[] parent, int flow, int source, int sink) {
        int curr = sink;
        while (curr != source) {
            int prev = parent[curr];
            for (Edge edge : graph.get(prev)) {
                if (edge.to == curr) {
                    edge.flow += flow; // Update flow on the edge
                    edge.reverse.flow -= flow; // Update flow on the reverse edge
                    break;
                }
            }
            curr = prev;
        }
    }
}