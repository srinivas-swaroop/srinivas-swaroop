I’ll structure it exactly in the **exam-ready format** you asked: **Problem Statement → Code → Time & Space Complexity → MCQs → Common Mistakes → Syntax cautions.**

---

# 1. Problem Statement

You are given a **binary tree in level order format** where `"null"` represents a missing node.

Your task is to **print the vertical order traversal of the binary tree**.

### Vertical Order Traversal

Each node is assigned a **horizontal distance (HD)** from the root.

Rules:

```
Root → HD = 0
Left child → HD - 1
Right child → HD + 1
```

Nodes that share the **same horizontal distance belong to the same vertical column**.

Print the nodes **column by column from leftmost column to rightmost column**.

---

## Input Format

A single line containing the **level order traversal** of the tree.

```
Values separated by spaces
"null" indicates missing node
```

Example

```
1 2 3 4 5 null 6
```

---

## Output Format

Print the **vertical order traversal of the tree**.

```
Nodes separated by space
```

---

## Example

Input

```
1 2 3 4 5 null 6
```

Tree

```
        1
       / \
      2   3
     / \   \
    4   5   6
```

Horizontal distances

```
HD -2 : 4
HD -1 : 2
HD  0 : 1 5
HD +1 : 3
HD +2 : 6
```

Output

```
4 2 1 5 3 6
```

---

# 2. Complete Code

```java
import java.util.*;

public class Main {

    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
            left = right = null;
        }
    }

    static class Pair {
        Node node;
        int hd;

        Pair(Node n, int h) {
            node = n;
            hd = h;
        }
    }

    public static Node buildTree(String[] values) {

        if (values.length == 0 || values[0].equals("null"))
            return null;

        Node root = new Node(Integer.parseInt(values[0]));

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        int i = 1;

        while (!q.isEmpty() && i < values.length) {

            Node curr = q.poll();

            if (i < values.length && !values[i].equals("null")) {
                curr.left = new Node(Integer.parseInt(values[i]));
                q.add(curr.left);
            }
            i++;

            if (i < values.length && !values[i].equals("null")) {
                curr.right = new Node(Integer.parseInt(values[i]));
                q.add(curr.right);
            }
            i++;
        }

        return root;
    }

    static void horizontalDistanceView(Node root) {

        if (root == null)
            return;

        Map<Integer, List<Integer>> map = new TreeMap<>();

        Queue<Pair> q = new LinkedList<>();

        q.add(new Pair(root, 0));

        while (!q.isEmpty()) {

            Pair p = q.poll();
            Node curr = p.node;
            int h = p.hd;

            if (!map.containsKey(h)) {
                map.put(h, new ArrayList<>());
            }

            map.get(h).add(curr.data);

            if (curr.left != null) {
                q.add(new Pair(curr.left, h - 1));
            }

            if (curr.right != null) {
                q.add(new Pair(curr.right, h + 1));
            }
        }

        for (List<Integer> list : map.values()) {
            for (int val : list) {
                System.out.print(val + " ");
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] tree = sc.nextLine().split(" ");

        Node root = buildTree(tree);

        horizontalDistanceView(root);
    }
}
```

---

# 3. Time Complexity (TC)

Traversal visits **every node once**.

```
Tree traversal = O(n)
TreeMap insertion = O(log n)
```

Total complexity

```
O(n log n)
```

---

# 4. Space Complexity (SC)

Memory used for:

```
Queue → O(n)
Map → O(n)
Tree nodes → O(n)
```

Total

```
O(n)
```

---

# 5. MCQs (Exam Style)

### Question 1

What traversal is used in this algorithm?

A) DFS
B) Inorder
C) BFS ✅
D) Postorder

---

### Question 2

Horizontal distance of root node is

A) -1
B) 1
C) 0 ✅
D) depends on tree

---

### Question 3

Why is `TreeMap` used instead of `HashMap`?

A) Faster access
B) Maintains sorted keys ✅
C) Uses less memory
D) Supports duplicates

---

### Question 4

Horizontal distance of left child is

A) hd + 1
B) hd - 1 ✅
C) hd
D) 0

---

### Question 5

Time complexity of this algorithm is

A) O(n)
B) O(n²)
C) O(n log n) ✅
D) O(log n)

---

### Question 6

Which data structure is used to store nodes by horizontal distance?

A) Array
B) TreeMap ✅
C) Stack
D) Heap

---

### Question 7

What is stored in the `Pair` class?

A) Node and parent
B) Node and level
C) Node and horizontal distance ✅
D) Node and height

---

### Question 8

Which traversal method builds the tree here?

A) Level order ✅
B) Preorder
C) Postorder
D) Inorder

---

### Question 9

Worst case space complexity

A) O(log n)
B) O(n) ✅
C) O(n²)
D) O(1)

---

### Question 10

If `HashMap` is used instead of `TreeMap`, what happens?

A) Code fails
B) Output becomes unsorted ✅
C) Runtime error
D) Memory overflow

---

# 6. Common Mistakes Students Make

### Mistake 1

Forgetting **null check**

```
if(root == null)
```

This causes **NullPointerException**.

---

### Mistake 2

Using

```
HashMap instead of TreeMap
```

Result

```
Columns printed in random order
```

---

### Mistake 3

Forgetting index increment in buildTree

```
i++
```

This breaks tree construction.

---

### Mistake 4

Accessing array without boundary check

Wrong

```
values[i]
```

Correct

```
i < values.length
```

---

### Mistake 5

Not checking `"null"` values

This causes **NumberFormatException**.

---

### Mistake 6

Confusing horizontal distance rule

Correct rule

```
Left → hd - 1
Right → hd + 1
```

Students often reverse it.

---

# 7. Complex Syntax to Be Careful With

### 1 Queue Declaration

Correct

```
Queue<Node> q = new LinkedList<>();
```

Wrong

```
Queue<Node> q = new Queue<>();
```

Queue is **interface**, not class.

---

### 2 TreeMap Declaration

Correct

```
Map<Integer, List<Integer>> map = new TreeMap<>();
```

---

### 3 Parsing Integer

```
Integer.parseInt(values[i])
```

Fails if value is `"null"`.

So always check:

```
!values[i].equals("null")
```

---

### 4 Generic Lists

Correct

```
new ArrayList<>()
```

Wrong

```
new ArrayList()
```

(Generics warning)

---

### 5 Queue Pair Insert

Correct

```
q.add(new Pair(root,0))
```

---

# 8. Important Exam Insight

This same template can solve:

```
Top View of Binary Tree
Bottom View of Binary Tree
Vertical Sum
Vertical Traversal
```

Just **change map logic slightly**.

---

If you want, I can also show you the **5 most common tree coding questions asked in computer-based exams (and they all reuse this exact template)**.
