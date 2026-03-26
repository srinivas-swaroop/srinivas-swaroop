Swaroop, below is the **Bellman–Ford algorithm version using the SAME structure and input style as your code**.
I only changed the **algorithm part**, while keeping:

* `Node` class
* `List<List<Node>> graph` adjacency list
* Same **Scanner input format**
* Same **output format**

---

# Bellman-Ford Version (Using Your Structure)

```java
import java.util.*;

class Main {

    static class Node {
        int vertex, weight;

        Node(int v, int w) {
            this.vertex = v;
            this.weight = w;
        }
    }

    public static int[] bellmanFord(List<List<Node>> graph, int src) {

        int V = graph.size();
        int[] dist = new int[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Relax edges V-1 times
        for (int i = 0; i < V - 1; i++) {

            for (int u = 0; u < V; u++) {

                for (Node neighbor : graph.get(u)) {

                    int v = neighbor.vertex;
                    int weight = neighbor.weight;

                    if (dist[u] != Integer.MAX_VALUE &&
                        dist[u] + weight < dist[v]) {

                        dist[v] = dist[u] + weight;
                    }
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int V = scanner.nextInt();
        int E = scanner.nextInt();
        int maxWeight = scanner.nextInt(); // kept to match your input format

        List<List<Node>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {

            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();

            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }

        int source = scanner.nextInt();
        scanner.close();

        int[] distances = bellmanFord(graph, source);

        for (int i = 0; i < distances.length; i++) {
            System.out.println("To " + i + " -> " +
                (distances[i] == Integer.MAX_VALUE ? "INF" : distances[i]));
        }
    }
}
```

---

# Input Format (Same as Your Program)

Example:

```
4 4 3
0 1 2
0 2 1
1 3 3
2 3 2
0
```

Meaning:

```
V = 4
E = 4
maxWeight = 3
edges...
source = 0
```

---

# Output

```
To 0 -> 0
To 1 -> 2
To 2 -> 1
To 3 -> 3
```

---

# What Changed from Dial's Algorithm

| Dial's Algorithm         | Bellman-Ford                    |
| ------------------------ | ------------------------------- |
| Uses **buckets**         | Uses **edge relaxation**        |
| Needs **maxWeight**      | Does not need it                |
| Faster for small weights | Works with **negative weights** |

---

✅ **Important observation (for understanding algorithms):**

Your Dial code actually **misses this line** inside relaxation:

```java
buckets.get(newDist).add(v);
```

Without this, **Dial’s algorithm will not work correctly**.

---

If you want, I can also show you a **very clean 15-line Bellman-Ford version professors expect in exams** (much easier to write in paper).

---

# Dial’s Algorithm MCQs

### 1. Dial’s Algorithm is an optimization of which algorithm?

A) Bellman–Ford algorithm
B) Dijkstra's algorithm ✅
C) Floyd–Warshall algorithm
D) Prim's algorithm

---

### 2. Dial’s Algorithm is used to solve which problem?

A) Minimum Spanning Tree
B) Network Flow
C) Single Source Shortest Path ✅
D) Graph Coloring

---

### 3. Dial’s Algorithm works correctly when edge weights are:

A) Negative
B) Positive and negative
C) Non-negative integers ✅
D) Floating point numbers

---

### 4. Which data structure is mainly used in Dial’s Algorithm?

A) Stack
B) Priority Queue
C) Buckets (array of lists) ✅
D) Binary Search Tree

---

### 5. The buckets in Dial’s Algorithm represent:

A) Vertex IDs
B) Edge counts
C) Distance values ✅
D) Graph levels

---

### 6. The number of buckets in Dial’s Algorithm is typically:

A) V + E
B) V × V
C) maxWeight × V + 1 ✅
D) E × logV

---

### 7. Time complexity of Dial’s Algorithm is approximately:

A) O(VE)
B) O(V + E + W) or O(WV + E) ✅
C) O(E log V)
D) O(V²)

(W = maximum edge weight)

---

### 8. Dial’s Algorithm replaces which structure used in Dijkstra’s Algorithm?

A) Stack
B) Queue
C) Priority Queue ✅
D) Hash Table

---

### 9. Dial’s Algorithm is most efficient when:

A) Graph has negative weights
B) Graph has very large weights
C) Graph has small integer weights ✅
D) Graph is disconnected

---

### 10. Dial’s Algorithm belongs to which category of algorithms?

A) Greedy Algorithm ✅
B) Dynamic Programming
C) Divide and Conquer
D) Backtracking

---

### 11. If a graph contains negative edge weights, Dial’s Algorithm will:

A) Work correctly
B) Fail to produce correct shortest paths ✅
C) Convert weights to positive
D) Detect negative cycles

---

### 12. Which algorithm should be used instead of Dial’s Algorithm if negative edges exist?

A) Kruskal's algorithm
B) Bellman–Ford algorithm ✅
C) Breadth‑First Search
D) Prim's algorithm

---

### 13. Dial’s Algorithm processes vertices in order of:

A) Vertex number
B) Increasing distance from source ✅
C) Decreasing edge weight
D) Random order

---

### 14. Dial’s Algorithm can be considered a variation of:

A) BFS
B) DFS
C) Dijkstra’s Algorithm ✅
D) Topological Sort

---

### 15. In Dial’s Algorithm, each bucket stores:

A) Edges
B) Vertices with the same distance value ✅
C) Graph components
D) Edge weights only

---

### 16. Dial’s Algorithm performs relaxation similar to:

A) Bellman–Ford algorithm
B) Dijkstra's algorithm ✅
C) Prim's algorithm
D) Kruskal's algorithm

---

### 17. Dial’s Algorithm is faster than Dijkstra when:

A) Edge weights are very large
B) Edge weights are small integers ✅
C) Graph has negative weights
D) Graph has cycles

---

### 18. Dial’s Algorithm is mainly used in:

A) Shortest path in weighted graphs with small weights ✅
B) Sorting numbers
C) Searching arrays
D) Database indexing

---

### 19. Which of the following statements is TRUE?

A) Dial’s Algorithm uses dynamic programming
B) Dial’s Algorithm uses buckets instead of priority queue ✅
C) Dial’s Algorithm works for negative edges
D) Dial’s Algorithm is slower than Bellman-Ford

---

### 20. Dial’s Algorithm is best suited for graphs where:

A) Edge weights are arbitrary real numbers
B) Edge weights are small non-negative integers ✅
C) Graph has negative cycles
D) Graph is unweighted

---

✅ If you want, I can also give **10 tricky MCQs professors usually ask in viva** (they mix **Dial, Dijkstra, Bellman-Ford**, and many students answer wrong).
