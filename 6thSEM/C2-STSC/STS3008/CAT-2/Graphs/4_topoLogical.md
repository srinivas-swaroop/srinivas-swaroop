Here’s a **complete structured note** (as you like) for your Topological Sort (DFS) code 👇

---

# 📌 Problem Statement

Given a **Directed Acyclic Graph (DAG)** with `V` vertices and `E` edges, perform a **Topological Sort**.

👉 Topological Sort is a linear ordering of vertices such that:
**If there is an edge u → v, then u appears before v in the ordering.**

---

# 📥 Input Format

```text
V E
u1 v1
u2 v2
...
uE vE
```

* `V` → number of vertices (0 to V-1)
* `E` → number of edges
* Each line `u v` represents a directed edge `u → v`

---

# 📤 Output Format

```text
[Topological Order of vertices]
```

👉 Example:

```text
[5, 4, 2, 3, 1, 0]
```

---

# 📌 Example

### Input:

```text
6 6
5 2
5 0
4 0
4 1
2 3
3 1
```

### Output:

```text
[5, 4, 2, 3, 1, 0]
```

---

# 💡 Intuition

👉 “**Process dependencies first, then the node**”

* Go deep (DFS)
* Add node **after visiting all neighbors**
* Reverse result

---

# 🧠 Pseudocode

```text
for each node:
    if not visited:
        dfs(node)

dfs(node):
    mark visited
    for each neighbor:
        if not visited:
            dfs(neighbor)
    add node to list

reverse list
```

---

# ✅ Your Code (With Detailed Comments)

```java
import java.util.*;

public class Main{
    
    static void dfs(ArrayList<ArrayList<Integer>> adjList, ArrayList<Integer> ans, boolean vis[], int k){
        
        vis[k] = true;   // mark current node as visited
        
        // traverse all neighbors of k
        for(int i : adjList.get(k)){
            
            // ❌ MISTAKE IN YOUR CODE:
            // you wrote (!vis[k]) instead of (!vis[i])
            
            if(!vis[i]){   // correct condition
                dfs(adjList, ans, vis, i);   // go to neighbor
            }
        }
        
        // add node AFTER visiting all neighbors
        ans.add(k);
    }
    
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int V = sc.nextInt();   // number of vertices
		int E = sc.nextInt();   // number of edges
		
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
		
		// initialize adjacency list
		for(int i=0; i<V; i++) adjList.add(new ArrayList<>());
		
		boolean vis[] = new boolean[V];
		ArrayList<Integer> ans = new ArrayList<>();
		
		// input edges
		for(int i=0; i<E; i++){
		    int src = sc.nextInt();
		    int dest = sc.nextInt();
		    
		    adjList.get(src).add(dest);
		}
		
		// call DFS for all components
		for(int i=0; i<V; i++){
		    if(!vis[i]){
		        dfs(adjList, ans, vis, i);
		    }
		}
		
		Collections.reverse(ans);   // reverse result
		
		System.out.println(ans);
	}
}
```

---

# ⚠️ Common Mistakes (Very Important)

### 🔴 1. Using wrong visited check

```java
if(!vis[k]) ❌
if(!vis[i]) ✅
```

---

### 🔴 2. Calling DFS on wrong node

```java
dfs(..., k) ❌
dfs(..., i) ✅
```

---

### 🔴 3. Adding node before DFS

```java
ans.add(k); ❌ (before DFS)
```

👉 Must be:

```java
dfs(...)
ans.add(k); ✅
```

---

### 🔴 4. Forgetting reverse

👉 Output will be **wrong order**

---

### 🔴 5. Not handling disconnected graph

👉 Must loop over all vertices

---

---

# 🎯 20 Expected MCQs (with answers)

---

### 1. Topological sort is applicable to:

A) Undirected Graph
B) Directed Graph
C) Weighted Graph
D) Cyclic Graph

✅ **Answer: B**

---

### 2. Topological sort exists only if graph is:

A) Connected
B) Tree
C) DAG
D) Weighted

✅ **Answer: C**

---

### 3. In DFS topo sort, node is added:

A) Before DFS
B) During DFS
C) After DFS
D) Random

✅ **Answer: C**

---

### 4. Time complexity:

A) O(V²)
B) O(E²)
C) O(V + E)
D) O(log V)

✅ **Answer: C**

---

### 5. Space complexity:

A) O(1)
B) O(V)
C) O(E)
D) O(V+E)

✅ **Answer: D**

---

### 6. Why reverse result?

A) For sorting
B) To get correct topo order
C) For printing
D) No reason

✅ **Answer: B**

---

### 7. If graph has cycle:

A) Works normally
B) Infinite loop
C) No valid topo sort
D) Returns empty

✅ **Answer: C**

---

### 8. Which structure is used?

A) Queue
B) Stack behavior
C) Heap
D) Tree

✅ **Answer: B**

---

### 9. Kahn’s algorithm uses:

A) DFS
B) BFS
C) Greedy
D) DP

✅ **Answer: B**

---

### 10. In-degree is used in:

A) DFS
B) BFS topo
C) Both
D) None

✅ **Answer: B**

---

### 11. Edge u → v means:

A) v before u
B) u before v
C) both
D) none

✅ **Answer: B**

---

### 12. DFS topo uses:

A) Pre-order
B) Post-order
C) In-order
D) Level order

✅ **Answer: B**

---

### 13. Which is NOT required?

A) Visited array
B) Adjacency list
C) Priority queue
D) Recursion

✅ **Answer: C**

---

### 14. If node has no outgoing edges:

A) Skip
B) Add immediately
C) Error
D) Loop

✅ **Answer: B**

---

### 15. Valid topo sort can be:

A) Unique
B) Multiple
C) None
D) Fixed

✅ **Answer: B**

---

### 16. DFS stack behaves like:

A) Queue
B) Stack
C) Heap
D) Array

✅ **Answer: B**

---

### 17. Reverse step is equivalent to:

A) Queue
B) Stack pop
C) Sorting
D) BFS

✅ **Answer: B**

---

### 18. Graph with 0 edges:

A) No solution
B) Any order valid
C) Error
D) Infinite

✅ **Answer: B**

---

### 19. Topo sort is used in:

A) Scheduling
B) Shortest path
C) Sorting array
D) Searching

✅ **Answer: A**

---

### 20. DFS ensures:

A) Breadth traversal
B) Dependency resolution
C) Random order
D) Cycles removed

✅ **Answer: B**

---

# 🚀 Final Takeaway

```text
Go deep → finish dependencies → add node → reverse
```

---

If you want next:
👉 Cycle Detection (VERY IMPORTANT for interviews)
👉 Kahn’s Algorithm with dry run

Just say **next** 👍
