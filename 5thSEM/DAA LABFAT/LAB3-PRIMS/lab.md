Absolutely ✅ — you can **add a comparator directly** inside the `PriorityQueue` declaration instead of implementing `Comparable` in the `Edge` class.
This makes the code even simpler and avoids the need for the `compareTo()` method.

Here’s the updated version 👇

---

## ✅ **Prim’s Algorithm using `PriorityQueue` with Comparator + Boolean + Edge Printing**

```java
import java.util.*;

class Edge {
    int vertex;
    int weight;
    int parent;  // to track the source vertex

    Edge(int v, int w, int p) {
        vertex = v;
        weight = w;
        parent = p;
    }
}

public class PrimPQ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of vertices
        int[][] cost = new int[n][n];

        // read cost matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
            }
        }

        boolean[] vis = new boolean[n];
        // ✅ comparator added directly here
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);

        int totalCost = 0;
        List<String> mstEdges = new ArrayList<>();

        // start from vertex 0
        pq.add(new Edge(0, 0, -1));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (vis[curr.vertex]) continue;

            vis[curr.vertex] = true;
            totalCost += curr.weight;

            if (curr.parent != -1) {
                mstEdges.add(curr.parent + " - " + curr.vertex + "    " + curr.weight);
            }

            // explore neighbors
            for (int v = 0; v < n; v++) {
                if (!vis[v] && cost[curr.vertex][v] != 0) {
                    pq.add(new Edge(v, cost[curr.vertex][v], curr.vertex));
                }
            }
        }

        System.out.println("Edge   Weight");
        for (String e : mstEdges) {
            System.out.println(e);
        }
        System.out.println("Total Cost: " + totalCost);
    }
}
```

---

### 🧩 **Input**

```
3
0 2 3
2 0 4
3 4 0
```

### 🖨️ **Output**

```
Edge   Weight
0 - 1    2
0 - 2    3
Total Cost: 5
```

---

✅ **Key Difference:**
Now the line

```java
PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
```

sorts edges by weight **without** needing `implements Comparable<Edge>` in the `Edge` class.

Would you like me to extend this version to also print the **Spanning Tree Matrix** (like in your previous problem statements)?


---

END
---

Perfect 👍 — let’s do **Prim’s Algorithm using a Priority Queue** + a simple `boolean[] vis` array — it’s the **cleanest and easiest optimal version** (better than the matrix loop one).

This one efficiently finds the **Minimum Spanning Tree (MST)** for your fiber network problem.

---

## 🌿 **Prim’s Algorithm using Priority Queue + Boolean Array**

### ✅ **Concept**

* Use a **Min-Heap (PriorityQueue)** to always pick the **smallest edge** leading to an unvisited node.
* Keep a **boolean `vis[]`** array to mark included vertices.
* Add the cost of every selected edge to the total MST cost.

---

### 💻 **Code (Simple and Clear)**

```java
import java.util.*;

class Edge implements Comparable<Edge> {
    int vertex;
    int weight;

    Edge(int v, int w) {
        vertex = v;
        weight = w;
    }

    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

public class PrimPQ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of vertices
        int[][] cost = new int[n][n];

        // read cost matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
            }
        }

        boolean[] vis = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int totalCost = 0;

        // start from vertex 0
        pq.add(new Edge(0, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (vis[curr.vertex]) continue;  // already included

            vis[curr.vertex] = true;  // mark as visited
            totalCost += curr.weight;

            // explore neighbors
            for (int v = 0; v < n; v++) {
                if (!vis[v] && cost[curr.vertex][v] != 0) {
                    pq.add(new Edge(v, cost[curr.vertex][v]));
                }
            }
        }

        System.out.println("Minimum Cost of Fiber Connection: " + totalCost);
    }
}
```

---

### 🧮 **Input Example**

```
4
0 2 3 3
2 0 0 2
3 0 0 4
3 2 4 0
```

### 🖨️ **Output**

```
Minimum Cost of Fiber Connection: 7
```

---

### 💡 **Explanation**

| Step       | Picked Edge | Added Cost | Total |
| ---------- | ----------- | ---------- | ----- |
| Start      | (0,0)       | 0          | 0     |
| Pick (0,1) | 2           | 2          |       |
| Pick (0,2) | 3           | 5          |       |
| Pick (1,3) | 2           | 7          |       |

✅ **MST cost = 7**

---

### ⚙️ **Time Complexity**

`O(E log V)` — efficient even for larger graphs.

---

Would you like me to make a **version that also prints the actual edges** included in the MST (like “0–1 (2)”, “1–3 (2)”, etc.)? It helps visualize the network.
