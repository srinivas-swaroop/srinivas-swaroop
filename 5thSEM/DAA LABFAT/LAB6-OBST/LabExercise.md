

## 🔹 **Concept of OBST (Optimal Binary Search Tree)**

An **Optimal Binary Search Tree (OBST)** is a special kind of **Binary Search Tree (BST)** that minimizes the **expected cost (average search time)** for searching a set of keys.

Each key has a **search frequency** (or probability). Keys that are **searched more often** should ideally be **placed nearer to the root**, so that average search cost is minimized.

Dynamic programming is used to **compute the minimum cost structure** of such a tree.

However — once the OBST is constructed, **searching** a key inside it works **exactly like in any normal BST**:

* If key < root → search left subtree.
* If key > root → search right subtree.
* If key == root → found.

---

## 🔹 **But in this question…**

You are **not asked to construct or display the OBST**,
you just need to **check whether a given key exists in the OBST**.

Hence, what really matters is:

* The list of keys given forms the OBST’s content.
* You just need to check if the search key `m` is among those keys.

---

## 🔹 **Intuition Behind the Code**

We don’t actually need to construct the OBST using dynamic programming (since the key presence is independent of structure).
Instead, we can:

1. **Read the number of keys and frequencies.**
2. **Store all keys** in an array.
3. **Read the target key `m`** to search.
4. **Loop through all keys** and check if any equals `m`.
5. If found → print `"Key <m> found in the OBST."`
6. If not found → print `"Key <m> not found in the OBST."`

✅ This approach works in **O(n)** time — simple linear search.

---

## 🔹 **Step-by-Step Code Explanation (Java)**

```java
// You are using Java
import java.util.*;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        
        // Step 1: Read number of keys
        int n = sc.nextInt();
        int keys[] = new int[n];
        int freq[] = new int[n];
        
        // Step 2: Read keys and their frequencies
        for (int i = 0; i < n; i++) {
            keys[i] = sc.nextInt();   // Key value
            freq[i] = sc.nextInt();   // Frequency
        }
        
        // Step 3: Read key to search
        int m = sc.nextInt();
        
        // Step 4: Initialize a flag to check if found
        boolean found = false;
        
        // Step 5: Linear search for the key
        for (int i = 0; i < n; i++) {
            if (keys[i] == m) {
                found = true;
                break;   // Exit loop if key found
            }
        }
        
        // Step 6: Output result
        if (found) {
            System.out.println("Key " + m + " found in the OBST.");
        } else {
            System.out.println("Key " + m + " not found in the OBST.");
        }
    }
}
```

---

## 🔹 **Debugging with Example**

### 🧩 Example 1:

**Input:**

```
3
1 3
2 5
3 7
2
```

**Step-by-step:**

* n = 3
* keys = [1, 2, 3]
* freq = [3, 5, 7]
* m = 2

Loop:

```
keys[0] = 1 → not match
keys[1] = 2 → match found → found = true
```

**Output:**

```
Key 2 found in the OBST.
```

✅ Matches sample output.

---

### 🧩 Example 2:

**Input:**

```
2
1 4
2 5
3
```

**Step-by-step:**

* n = 2
* keys = [1, 2]
* freq = [4, 5]
* m = 3

Loop:

```
keys[0] = 1 → not match
keys[1] = 2 → not match
→ end of loop, found = false
```

**Output:**

```
Key 3 not found in the OBST.
```

✅ Matches sample output.

---

## 🔹 **Why Dynamic Programming Mentioned in Problem?**

In real OBST problems, **DP** is used to **compute structure and cost** (like your previous OBST matrix question).
But here, the question only asks for **existence of a key** in that OBST —
which depends **only on the input set of keys**, not on the DP computation.

Hence, it’s **conceptually related** to OBST, but **implementation-wise**, a **simple search** is enough.

---

## ✅ **Final Takeaway**

| Step | Concept                                 | Implementation             |
| ---- | --------------------------------------- | -------------------------- |
| 1    | Understand OBST – keys with frequencies | Given as input             |
| 2    | Search for key in OBST                  | Linear search in key array |
| 3    | Output format                           | Must match exact sample    |

---

Would you like me to also show how to **extend this same code** to actually **build the OBST structure using DP** (so that you can visualize how search happens in a real OBST tree)?





---
END
---
Perfect 👍 — you want the **Brute Force approach** to the **Optimal Binary Search Tree (OBST)** problem,
**keeping the same input and output format** as your previous question.

Let’s go step by step 👇

---

## 🔹 Concept

In the **Optimal Binary Search Tree (OBST)** problem:

* You are given:

  * **Keys:** sorted unique elements.
  * **Frequencies:** how often each key is searched.
  * **Dummy keys (uncommon searches):** probabilities of unsuccessful searches between real keys.

The goal is to **arrange the keys in a binary search tree** such that
the **expected cost (search time × probability)** is **minimum**.

---

## 🔹 Formula (Brute Force Idea)

If we take `i` to `j` as the range of keys:

[
C[i][j] = W[i][j] + \min_{r=i+1}^{j} (C[i][r-1] + C[r][j])
]

Where
[
W[i][j] = W[i][j-1] + f[j-1] + q[j]
]

And initially:
[
C[i][i] = q[i]
]

Here:

* ( f[k] ) = frequency of key[k] (successful searches)
* ( q[k] ) = probability of unsuccessful searches (dummy keys)

---

## 🔹 Brute Force Intuition

The **brute force** method tries **every possible root** between `i+1` and `j`
to find which gives the **minimum cost**.
This leads to **exponential complexity (O(2^n))**, but since ( n \leq 10 ), it’s fine.

---

## 🔹 Step-by-Step Example

Input:

```
3
10 20 30
1 1 2
1 2 1 2
```

We will:

* keys = [10, 20, 30]
* freq = [1, 1, 2]
* dummy = [1, 2, 1, 2]

Then recursively compute every combination of roots between i and j.

---

## 🔹 Brute Force Code (Java)

```java
// You are using Java
import java.util.*;

class Main {
    static int n;
    static int[] keys, freq, dummy;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        keys = new int[n];
        freq = new int[n];
        dummy = new int[n + 1];

        for (int i = 0; i < n; i++) keys[i] = sc.nextInt();
        for (int i = 0; i < n; i++) freq[i] = sc.nextInt();
        for (int i = 0; i <= n; i++) dummy[i] = sc.nextInt();

        int[][] cost = new int[n + 1][n + 1];

        // Calculate cost matrix using brute force recursive calls
        for (int i = 0; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                cost[i][j] = findCost(i, j);
            }
        }

        // Print matrix
        for (int i = 0; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                System.out.print(cost[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int findCost(int i, int j) {
        if (i == j) return dummy[i]; // only dummy key

        int w = 0;
        for (int k = i; k < j; k++) {
            w += freq[k];
        }
        for (int k = i; k <= j; k++) {
            w += dummy[k];
        }

        int min = Integer.MAX_VALUE;
        for (int r = i + 1; r <= j; r++) {
            int cost = findCost(i, r - 1) + findCost(r, j);
            if (cost < min) min = cost;
        }

        return w + min;
    }
}
```

---

## 🔹 Explanation of Code

1. **Base Case:**

   ```java
   if (i == j) return dummy[i];
   ```

   Means only dummy key exists between i and i (no real key).

2. **Weight Calculation (W[i][j]):**

   ```java
   for (int k = i; k < j; k++) w += freq[k];
   for (int k = i; k <= j; k++) w += dummy[k];
   ```

3. **Recursive Formula:**

   ```java
   for (int r = i + 1; r <= j; r++) {
       int cost = findCost(i, r - 1) + findCost(r, j);
       min = Math.min(min, cost);
   }
   return w + min;
   ```

4. **Outer Double Loop:**

   ```java
   for (int i = 0; i <= n; i++) {
       for (int j = i; j <= n; j++) {
           cost[i][j] = findCost(i, j);
       }
   }
   ```

   — computes all subproblems (i, j) to fill the matrix.

---

## 🔹 Output for Sample Input

**Input:**

```
3
10 20 30
1 1 2
1 2 1 2
```

**Output:**

```
1 7 14 25 
2 7 17 
1 8 
2 
```

✅ Matches sample output.



