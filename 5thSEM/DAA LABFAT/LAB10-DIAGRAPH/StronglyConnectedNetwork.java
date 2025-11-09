import java.util.*;

public class StronglyConnectedNetwork {
    static int V;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> revGraph = new ArrayList<>();
    static boolean[] visited;

    // Add edge in both normal and reverse graph
    static void addEdge(int u, int v) {
        graph.get(u).add(v);
        revGraph.get(v).add(u);  // reverse edge while adding
    }

    // DFS to fill stack by finish order
    static void dfs1(int v, Stack<Integer> stack) {
        visited[v] = true;
        for (int nbr : graph.get(v)) {
            if (!visited[nbr])
                dfs1(nbr, stack);
        }
        stack.push(v);
    }

    // DFS on reverse graph to get one SCC
    static void dfs2(int v, ArrayList<Integer> component) {
        visited[v] = true;
        component.add(v);
        for (int nbr : revGraph.get(v)) {
            if (!visited[nbr])
                dfs2(nbr, component);
        }
    }

    // Find all SCCs
    static ArrayList<ArrayList<Integer>> findSCCs() {
        Stack<Integer> stack = new Stack<>();
        visited = new boolean[V + 1];

        // Step 1: Normal DFS to get finish order
        for (int i = 1; i <= V; i++) {
            if (!visited[i])
                dfs1(i, stack);
        }

        // Step 2: Reverse DFS to get SCCs
        visited = new boolean[V + 1];
        ArrayList<ArrayList<Integer>> sccList = new ArrayList<>();

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                ArrayList<Integer> component = new ArrayList<>();
                dfs2(node, component);
                sccList.add(component);
            }
        }
        return sccList;
    }

    // Calculate minimum edges required to make graph strongly connected
    static int minEdgesToMakeStronglyConnected(ArrayList<ArrayList<Integer>> sccList) {
        int sccCount = sccList.size();
        if (sccCount == 1) return 0;

        int[] indegree = new int[sccCount];
        int[] outdegree = new int[sccCount];
        int[] sccId = new int[V + 1];

        // Map node → its SCC ID
        for (int i = 0; i < sccCount; i++) {
            for (int node : sccList.get(i)) {
                sccId[node] = i;
            }
        }

        // Check edges between SCCs
        for (int u = 1; u <= V; u++) {
            for (int v : graph.get(u)) {
                if (sccId[u] != sccId[v]) {
                    outdegree[sccId[u]]++;
                    indegree[sccId[v]]++;
                }
            }
        }

        // Count number of source and sink SCCs
        int source = 0, sink = 0;
        for (int i = 0; i < sccCount; i++) {
            if (indegree[i] == 0) source++;
            if (outdegree[i] == 0) sink++;
        }

        // Minimum edges required
        return Math.max(source, sink);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        V = sc.nextInt(); // number of vertices
        int E = sc.nextInt(); // number of edges

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
            revGraph.add(new ArrayList<>());
        }

        // Take input and build both normal and reverse graphs
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(u, v);
        }

        // ---------- Question 1: Print SCCs ----------
        ArrayList<ArrayList<Integer>> sccList = findSCCs();

        System.out.println("Strongly Connected Components are:");
        for (ArrayList<Integer> component : sccList) {
            for (int node : component)
                System.out.print(node + " ");
            System.out.println();
        }

        // ---------- Question 2: Minimum edges ----------
        int minEdges = minEdgesToMakeStronglyConnected(sccList);
        System.out.println("Minimum edges needed to make strongly connected: " + minEdges);
    }
}
