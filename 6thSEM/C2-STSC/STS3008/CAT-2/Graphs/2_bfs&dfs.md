## Problem Statement

You are given an **undirected graph** with **V vertices** and **E edges**.
The graph is represented using an **Adjacency List**.

Your task is to:

1. Perform **Depth First Search (DFS)** starting from vertex `0`.
2. Perform **Breadth First Search (BFS)** starting from vertex `0`.
3. Print the order of nodes visited by:

   * DFS
   * BFS

### Input Format

```
V E
src1 dest1
src2 dest2
...
srcE destE
```

* `V` → Number of vertices
* `E` → Number of edges
* Each edge connects two vertices.

### Output Format

```
DFS Traversal
BFS Traversal
```

---

# Code with Detailed Comments

```java
import java.util.*;

public class Main
{
    // BFS Traversal Function
    static void bfsTravsersal(ArrayList<Integer> list, int src, int V, ArrayList<ArrayList<Integer>> adjList, boolean vis[]) {
        
        // Queue is used in BFS (FIFO order)
        Queue<Integer> queue = new LinkedList<>();
        
        // Start from source node
        queue.add(src);
        
        // Continue until queue becomes empty
        while(!queue.isEmpty()){
            
            // Remove element from front of queue
            int temp = queue.poll();
            
            // If node is not visited
            if(!vis[temp]){
                
                // Add node to result list
                list.add(temp);
                
                // Mark node as visited
                vis[temp] = true;
            }
            
            // Traverse all neighbours of the node
            for(int i : adjList.get(temp)){
                
                // Add unvisited neighbours to queue
                if(!vis[i]) 
                    queue.add(i);
            }
        }
    }


    // DFS Traversal Function (Recursive)
    static void dfsTravsersal(ArrayList<Integer> list, int src, int V, ArrayList<ArrayList<Integer>> adjList, boolean vis[]) {
        
        // Mark current node visited
        vis[src] = true;
        
        // Add node to result list
        list.add(src);

        // Traverse all neighbours
        for(int i : adjList.get(src)) {
            
            // If neighbour not visited, visit recursively
            if(!vis[i]) {
                dfsTravsersal(list, i, V, adjList, vis);
            }
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Adjacency List representation of graph
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        // Number of vertices
        int V = sc.nextInt();
        
        // Number of edges
        int E = sc.nextInt();

        // Initialize adjacency list for each vertex
        for(int i= 0; i<V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Input edges
        for(int i=0; i<E; i++) {
            
            int src = sc.nextInt();
            int dest = sc.nextInt();

            // Since graph is undirected
            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }

        // List to store DFS result
        ArrayList<Integer> dfs = new ArrayList<>();
        
        // Visited array
        boolean vis[] = new boolean[V];

        // Perform DFS starting from node 0
        dfsTravsersal(dfs,0, V, adjList, vis);
        
        // List to store BFS result
        ArrayList<Integer> bfs = new ArrayList<>();
        
        // Reset visited array
        Arrays.fill(vis, false);
        
        // Perform BFS starting from node 0
        bfsTravsersal(bfs,0, V, adjList, vis);

        // Print DFS traversal
        for(int i : dfs) 
            System.out.print(i+"->");
        
        System.out.println();
        
        // Print BFS traversal
        for(int i : bfs) 
            System.out.print(i+"->");
    }
}
```

---

# Example Input

```
5 4
0 1
0 2
1 3
2 4
```

### Graph

```
     0
   /   \
  1     2
  |     |
  3     4
```

### Output

```
0->1->3->2->4->
0->1->2->3->4->
```

---

# Important Concept Difference

| Feature        | BFS              | DFS               |
| -------------- | ---------------- | ----------------- |
| Data Structure | Queue            | Recursion / Stack |
| Traversal      | Level by Level   | Depth First       |
| Shortest Path  | Yes (Unweighted) | No                |

---

# Common Mistakes Students Make

### 1️⃣ Forgetting to mark visited

Wrong:

```java
list.add(temp);
```

Correct:

```java
vis[temp] = true;
```

Otherwise **infinite loop happens**.

---

### 2️⃣ Adding visited node again in BFS

If you don't check visited before pushing:

Wrong

```java
queue.add(i);
```

Correct

```java
if(!vis[i]) queue.add(i);
```

Otherwise queue grows unnecessarily.

---

### 3️⃣ Forgetting to reset visited before BFS

You used DFS first.

So nodes become visited.

If you don't reset:

```java
Arrays.fill(vis, false);
```

Then BFS will output **nothing**.

---

### 4️⃣ Not initializing adjacency list

Wrong

```
ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
```

But forgetting:

```
adjList.add(new ArrayList<>());
```

This causes **NullPointerException**.

---

### 5️⃣ Stack Overflow in DFS

If graph is **very deep**, recursive DFS can cause stack overflow.

Iterative DFS with stack avoids this.

---

# Time Complexity

| Algorithm | Complexity |
| --------- | ---------- |
| BFS       | O(V + E)   |
| DFS       | O(V + E)   |

---

# One Small Improvement in Your Code (Professional Practice)

Better BFS practice:

Instead of marking visited **after polling**, mark **when inserting**.

Better version:

```java
queue.add(src);
vis[src] = true;
```

This prevents duplicate queue entries.

---

Here are **MCQs based on your BFS–DFS graph program**. These are the type professors usually ask in **Data Structures / Graphs exams**.

---

# MCQs on BFS and DFS

### 1. Which data structure is primarily used in BFS traversal?

A) Stack
B) Queue
C) Priority Queue
D) Linked List

✅ **Answer:** B) Queue

---

### 2. Which data structure is used internally in DFS recursion?

A) Queue
B) Stack
C) Heap
D) HashMap

✅ **Answer:** B) Stack
*(Recursion uses the call stack)*

---

### 3. What is the time complexity of BFS for a graph with V vertices and E edges?

A) O(V)
B) O(E)
C) O(V + E)
D) O(V × E)

✅ **Answer:** C) O(V + E)

---

### 4. What is the time complexity of DFS traversal?

A) O(V)
B) O(E)
C) O(V + E)
D) O(V²)

✅ **Answer:** C) O(V + E)

---

### 5. In the given code, what is the purpose of the `vis[]` array?

A) Stores adjacency list
B) Stores traversal result
C) Tracks visited nodes
D) Stores edge weights

✅ **Answer:** C) Tracks visited nodes

---

### 6. Which traversal explores nodes **level by level**?

A) DFS
B) BFS
C) Topological Sort
D) Dijkstra

✅ **Answer:** B) BFS

---

### 7. Which traversal goes **deep into a branch before backtracking**?

A) BFS
B) DFS
C) Prim's Algorithm
D) Kruskal's Algorithm

✅ **Answer:** B) DFS

---

### 8. Which traversal guarantees **shortest path in an unweighted graph**?

A) DFS
B) BFS
C) Both
D) None

✅ **Answer:** B) BFS

---

### 9. In the BFS function, which line removes an element from the queue?

A)

```
queue.add(src);
```

B)

```
queue.poll();
```

C)

```
queue.peek();
```

D)

```
queue.removeAll();
```

✅ **Answer:** B) `queue.poll();`

---

### 10. In DFS traversal, what causes the recursive call?

A)

```
list.add(src);
```

B)

```
vis[src] = true;
```

C)

```
dfsTravsersal(list, i, V, adjList, vis);
```

D)

```
adjList.get(src);
```

✅ **Answer:** C)

---

### 11. What will happen if we **do not reset the visited array before BFS**?

A) BFS will crash
B) BFS will run normally
C) BFS will skip nodes
D) Infinite loop

✅ **Answer:** C) BFS will skip nodes

---

### 12. What type of graph is created in this line?

```
adjList.get(src).add(dest);
adjList.get(dest).add(src);
```

A) Directed Graph
B) Undirected Graph
C) Weighted Graph
D) Cyclic Graph

✅ **Answer:** B) Undirected Graph

---

### 13. What will happen if we remove the visited condition in BFS?

```
if(!vis[i]) queue.add(i);
```

A) Faster execution
B) Infinite loop / repeated nodes
C) No output
D) Compilation error

✅ **Answer:** B) Infinite loop / repeated nodes

---

### 14. In the adjacency list representation, the space complexity is:

A) O(V²)
B) O(V + E)
C) O(E²)
D) O(V)

✅ **Answer:** B) O(V + E)

---

### 15. Which of the following graphs **cannot** be traversed without a visited array?

A) Tree
B) Directed Acyclic Graph
C) Graph with cycles
D) Binary Tree

✅ **Answer:** C) Graph with cycles

---

# Professor-Level MCQ (Very Common)

### 16. Which traversal is better for detecting cycles in a graph?

A) BFS
B) DFS
C) Both
D) None

✅ **Answer:** B) DFS

---

If you want, I can also give:

* **20 tricky MCQs professors love to ask**
* **Output prediction MCQs**
* **Code debugging MCQs (very common in exams)**

These are **much harder than normal MCQs**.
