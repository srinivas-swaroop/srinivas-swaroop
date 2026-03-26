Below is the **exam-ready structured explanation** for the given program (Top View of Binary Tree). I’ll keep it **precise, structured, and focused for computer-based exams**.

---

# 1. Problem Statement

You are given the **level-order representation of a binary tree**, where `"null"` represents a missing node.

Your task is to **print the Top View of the Binary Tree**.

### What is Top View?

The **top view** of a binary tree consists of the nodes visible when the tree is viewed **from above**.

Each node has a **horizontal distance (HD)** from the root.

Rules:

```
Root → HD = 0
Left child → HD - 1
Right child → HD + 1
```

For every horizontal distance, **only the first node encountered during level order traversal (BFS)** is part of the **top view**.

---

### Example

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

Horizontal Distances

```
HD -2 : 4
HD -1 : 2
HD  0 : 1
HD +1 : 3
HD +2 : 6
```

Output

```
4 2 1 3 6
```

---

# 2. Complete Code

```java
import java.util.*;

public class Main {

    static class Node {
        Node left;
        Node right;
        int val;

        Node(int val) {
            this.val = val;
        }
    }

    static class Pair {
        Node nd;
        int level;

        Pair(Node nd, int level) {
            this.nd = nd;
            this.level = level;
        }
    }

    static Node buildTree(String tree[]) {

        if (tree.length == 0 || tree[0].equals("null"))
            return null;

        Queue<Node> queue = new LinkedList<>();

        Node head = new Node(Integer.parseInt(tree[0]));

        queue.add(head);

        int i = 1;

        while (!queue.isEmpty() && i < tree.length) {

            Node temp = queue.poll();

            if (!tree[i].equals("null")) {

                Node leftNode = new Node(Integer.parseInt(tree[i]));

                temp.left = leftNode;

                queue.add(leftNode);
            }

            i++;

            if (i < tree.length && !tree[i].equals("null")) {

                Node rightNode = new Node(Integer.parseInt(tree[i]));

                temp.right = rightNode;

                queue.add(rightNode);
            }

            i++;
        }

        return head;
    }

    static List<Integer> topView(Node head) {

        ArrayList<Integer> list = new ArrayList<>();

        Map<Integer, Integer> map = new TreeMap<>();

        Queue<Pair> queue = new LinkedList<>();

        queue.add(new Pair(head, 0));

        while (!queue.isEmpty()) {

            Pair temp = queue.poll();

            int level = temp.level;

            int value = temp.nd.val;

            if (!map.containsKey(level))
                map.put(level, value);

            if (temp.nd.left != null)
                queue.add(new Pair(temp.nd.left, level - 1));

            if (temp.nd.right != null)
                queue.add(new Pair(temp.nd.right, level + 1));
        }

        list.addAll(map.values());

        return list;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        String tree[] = s.split(" ");

        Node head = buildTree(tree);

        List<Integer> list = topView(head);

        for (int i : list)
            System.out.print(i + " ");
    }
}
```

---

# 3. Time Complexity (TC)

Traversal visits every node once.

Operations:

```
BFS traversal → O(n)
TreeMap insertion → O(log n)
```

Final Complexity

```
O(n log n)
```

---

# 4. Space Complexity (SC)

Memory used by:

```
Queue → O(n)
TreeMap → O(n)
Tree nodes → O(n)
```

Total

```
O(n)
```

---

# 5. MCQs (Exam Style)

### Question 1

What traversal is used to compute top view?

A) DFS
B) Inorder
C) BFS ✅
D) Postorder

---

### Question 2

Horizontal distance of root is

A) -1
B) 0 ✅
C) 1
D) depends on tree

---

### Question 3

Which data structure ensures sorted horizontal distances?

A) HashMap
B) TreeMap ✅
C) Array
D) Stack

---

### Question 4

Why is `map.containsKey(level)` checked?

A) To avoid duplicates
B) To store first node only ✅
C) For faster access
D) For memory optimization

---

### Question 5

Time complexity of this solution?

A) O(n²)
B) O(log n)
C) O(n log n) ✅
D) O(n³)

---

### Question 6

Horizontal distance of right child is

A) hd + 1 ✅
B) hd - 1
C) hd
D) 0

---

### Question 7

What does the `Pair` class store?

A) Node and parent
B) Node and depth
C) Node and horizontal distance ✅
D) Node and height

---

### Question 8

Which traversal builds the tree?

A) DFS
B) Level Order BFS ✅
C) Preorder
D) Inorder

---

### Question 9

If `HashMap` replaces `TreeMap`, what happens?

A) Compilation error
B) Output unsorted ✅
C) Faster output
D) No change

---

### Question 10

Worst case space complexity

A) O(1)
B) O(log n)
C) O(n) ✅
D) O(n²)

---

# 6. Common Mistakes Students Make

### 1 Forgetting Null Check

```
if(tree.length == 0 || tree[0].equals("null"))
```

Without this → **NullPointerException**

---

### 2 Forgetting Boundary Check

Wrong

```
tree[i]
```

Correct

```
i < tree.length
```

---

### 3 Using HashMap Instead of TreeMap

Result:

```
columns printed in random order
```

---

### 4 Forgetting Horizontal Distance Rule

Correct

```
Left → hd - 1
Right → hd + 1
```

Students often reverse it.

---

### 5 Not Using BFS

If DFS is used incorrectly → wrong top view.

---

# 7. Complex Syntax to Be Careful With

### 1 Queue Initialization

Correct

```
Queue<Pair> queue = new LinkedList<>();
```

Queue is **interface**, so cannot do:

```
new Queue<>()
```

---

### 2 Integer Parsing

```
Integer.parseInt(tree[i])
```

Must check

```
!tree[i].equals("null")
```

Otherwise **NumberFormatException** occurs.

---

### 3 Generic List Declaration

Correct

```
ArrayList<Integer> list = new ArrayList<>();
```

Avoid raw type

```
new ArrayList()
```

---

### 4 Adding Pair to Queue

Correct

```
queue.add(new Pair(node, level));
```

---

### 5 Map Access

```
if(!map.containsKey(level))
    map.put(level,value);
```

This ensures **first node per vertical line**.

---

# 8. Important Exam Tip

The **same template solves multiple problems**:

```
Top View
Bottom View
Vertical Order
Vertical Sum
Diagonal Traversal
```

Only **1–2 lines of map logic change**.

---

✅ If you want, I can also show you **the 5 most common binary tree problems that appear in computer-based exams (they all reuse this same code pattern)**.
