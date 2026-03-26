## 📌 Problem Statement – Bellman Ford Algorithm

You are given a **directed weighted graph** with **V vertices** and **E edges**.
Each edge has a **source vertex (src)**, **destination vertex (dest)**, and **weight**.

Your task is to:

1. Find the **shortest distance from a given source vertex to all other vertices**.
2. If the graph contains a **negative weight cycle reachable from the source**, print **-1**.
3. If a vertex is **not reachable from the source**, print **-1 for that vertex**.

The algorithm must handle **negative edge weights**, which some algorithms (like Dijkstra) cannot handle.

---

# 📥 Input Format

1. First line → **V E**

   * V = number of vertices
   * E = number of edges

2. Next **E lines** →
   `src dest weight`

3. Last line → **source vertex**

---

# 📤 Output Format

* If **negative cycle exists** → print `-1`
* Otherwise print **shortest distance from source to every vertex**

---

# 📌 Example

### Input

```
5 8
0 1 -1
0 2 4
1 2 3
1 3 2
1 4 2
3 2 5
3 1 1
4 3 -3
0
```

### Output

```
0 -1 2 -2 1
```

### Explanation

Shortest distance from **source = 0**

| Vertex | Distance |
| ------ | -------- |
| 0      | 0        |
| 1      | -1       |
| 2      | 2        |
| 3      | -2       |
| 4      | 1        |

---

# 💻 Code Explained (Line by Line in Comments)

```java
import java.util.*;

// Edge class represents a graph edge
class Edge {
    int src;      // source vertex
    int dest;     // destination vertex
    int weight;   // edge weight

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

public class Main {

    // Bellman Ford algorithm
    public static void bellmanFord(int V, ArrayList<ArrayList<Edge>> adj, int source) {

        // Distance array to store shortest distance from source
        int[] dist = new int[V];

        // Initially set all distances to infinity
        Arrays.fill(dist, Integer.MAX_VALUE);

        // Distance to source itself is 0
        dist[source] = 0;

        // Relax all edges V-1 times
        // Because shortest path can contain at most V-1 edges
        for (int i = 1; i <= V - 1; i++) {

            // Traverse every vertex
            for (int u = 0; u < V; u++) {

                // Traverse all edges from that vertex
                for (Edge e : adj.get(u)) {

                    int src = e.src;
                    int dest = e.dest;
                    int weight = e.weight;

                    // Relaxation condition
                    // If distance to src is known and
                    // new shorter path to dest found
                    if (dist[src] != Integer.MAX_VALUE &&
                        dist[src] + weight < dist[dest]) {

                        // Update distance
                        dist[dest] = dist[src] + weight;
                    }
                }
            }
        }

        // Check for negative weight cycle
        // If we can still relax an edge,
        // then negative cycle exists
        for (int u = 0; u < V; u++) {

            for (Edge e : adj.get(u)) {

                int src = e.src;
                int dest = e.dest;
                int weight = e.weight;

                if (dist[src] != Integer.MAX_VALUE &&
                    dist[src] + weight < dist[dest]) {

                    // Negative cycle detected
                    System.out.println("-1");
                    return;
                }
            }
        }

        // Print shortest distances
        for (int i = 0; i < V; i++) {

            // If unreachable vertex
            if (dist[i] == Integer.MAX_VALUE)
                System.out.print("-1 ");
            else
                System.out.print(dist[i] + " ");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Number of vertices
        int V = sc.nextInt();

        // Number of edges
        int E = sc.nextInt();

        // Adjacency list to store graph
        ArrayList<ArrayList<Edge>> adj = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Input edges
        for (int i = 0; i < E; i++) {

            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();

            // Add edge to adjacency list
            adj.get(src).add(new Edge(src, dest, weight));
        }

        // Source vertex
        int source = sc.nextInt();

        // Run Bellman Ford
        bellmanFord(V, adj, source);

        sc.close();
    }
}
```

---

# ❗ Common Mistakes Students Make

### 1️⃣ Forgetting `dist[src] != Integer.MAX_VALUE`

Wrong

```java
if(dist[src] + weight < dist[dest])
```

Correct

```java
if(dist[src] != Integer.MAX_VALUE && dist[src] + weight < dist[dest])
```

Otherwise **Integer overflow occurs**.

---

### 2️⃣ Relaxing edges V times instead of V-1

Wrong

```java
for(int i=0;i<V;i++)
```

Correct

```
V-1 times
```

Because shortest path uses **maximum V-1 edges**.

---

### 3️⃣ Not checking negative cycle

Many students forget this step.

Bellman Ford **must detect negative cycles**.

---

### 4️⃣ Forgetting to initialize adjacency list

Wrong

```
ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
```

Correct

```
for(int i=0;i<V;i++)
adj.add(new ArrayList<>());
```

---

### 5️⃣ Confusing `src` and `u`

Students sometimes use

```
dist[u] + weight
```

Instead of

```
dist[src] + weight
```

---

# 🎯 MCQs (15)

### 1️⃣ Bellman Ford is used to find

A) Minimum Spanning Tree
B) Shortest path from single source
C) Longest path
D) BFS traversal

✅ Answer: **B**

---

### 2️⃣ Bellman Ford works with

A) Positive weights only
B) Negative weights only
C) Both positive and negative weights
D) Zero weights only

✅ Answer: **C**

---

### 3️⃣ Bellman Ford detects

A) Positive cycle
B) Negative cycle
C) Directed cycle
D) Undirected cycle

✅ Answer: **B**

---

### 4️⃣ Maximum relaxations needed

A) V
B) V+1
C) V-1
D) E

✅ Answer: **C**

---

### 5️⃣ Time complexity

A) O(V²)
B) O(E log V)
C) O(VE)
D) O(E²)

✅ Answer: **C**

---

### 6️⃣ Dijkstra fails when

A) Graph connected
B) Negative weight edges
C) Directed graph
D) Undirected graph

✅ Answer: **B**

---

### 7️⃣ Initial distance of source

A) 1
B) -1
C) Infinity
D) 0

✅ Answer: **D**

---

### 8️⃣ Initial distance of other vertices

A) 0
B) Infinity
C) -1
D) Weight

✅ Answer: **B**

---

### 9️⃣ If vertex unreachable

A) Print 0
B) Print -1
C) Print V
D) Ignore

✅ Answer: **B**

---

### 🔟 Relaxation means

A) Decreasing edges
B) Updating shortest distance
C) Removing nodes
D) Traversing BFS

✅ Answer: **B**

---

### 11️⃣ Bellman Ford is

A) Greedy
B) Dynamic programming idea
C) Backtracking
D) Divide & Conquer

✅ Answer: **B**

---

### 12️⃣ Negative cycle causes

A) Infinite shortest path
B) Unique path
C) BFS failure
D) DFS failure

✅ Answer: **A**

---

### 13️⃣ Graph representation used here

A) Matrix
B) Adjacency list
C) Heap
D) Stack

✅ Answer: **B**

---

### 14️⃣ Edge structure stores

A) src, dest, weight
B) only weight
C) only nodes
D) only edges

✅ Answer: **A**

---

### 15️⃣ If relaxation still possible after V-1 iterations

A) Graph disconnected
B) Negative cycle exists
C) Graph complete
D) BFS required

✅ Answer: **B**

---

💡 If you want, I can also give:

* **10 tricky Bellman-Ford MCQs professors ask in exams**
* **Dry run of this code step-by-step**
* **Visual graph explanation (which makes Bellman-Ford 10× easier)**
