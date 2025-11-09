import java.util.*;

public class lab1 {
    static class Edge {
        int to, weight;
        Edge(int t, int w) {
            to = t;
            weight = w;
        }
    }

    static void dijkstra(int n, List<List<Edge>> graph, int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Min-heap based on distance
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{src, 0});

        boolean[] visited = new boolean[n];

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];

            if (visited[u]) continue;
            visited[u] = true;

            for (Edge e : graph.get(u)) {
                int v = e.to;
                int w = e.weight;

                // Relaxation step
                if (!visited[v] && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        // Print results
        for (int i = 0; i < n; i++) {
            System.out.println(i + " " + dist[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // number of routers
        int m = sc.nextInt(); // number of links

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int r1 = sc.nextInt();
            int r2 = sc.nextInt();
            int w = sc.nextInt();
            graph.get(r1).add(new Edge(r2, w));
            graph.get(r2).add(new Edge(r1, w)); // undirected
        }

        int src = sc.nextInt();
        int dest = sc.nextInt(); // not directly used

        dijkstra(n, graph, src);
    }
}
