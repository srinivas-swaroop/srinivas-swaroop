## 🌳 Problem: Boundary Traversal of a Binary Tree

### 📌 Problem Statement (Simple Explanation)

You are given a **binary tree**.
Your task is to print the **boundary of the tree in anti-clockwise order**.

The **boundary** consists of **three parts**:

1. **Left Boundary** – nodes from root to the leftmost node (excluding leaf nodes)
2. **Leaf Nodes** – all leaf nodes from left to right
3. **Right Boundary** – nodes from bottom to top on the right side (excluding leaf nodes)

### 📌 Order of Traversal

```
Root → Left Boundary → Leaf Nodes → Right Boundary (reverse)
```

### Example Tree

```
        1
      /   \
     2     3
    / \   / \
   4   5 6   7
```

Boundary traversal:

```
1 → 2 → 4 → 5 → 6 → 7 → 3
```

Explanation:

* Root → `1`
* Left boundary → `2`
* Leaves → `4 5 6 7`
* Right boundary (bottom to top) → `3`

Final Output:

```
1 2 4 5 6 7 3
```

---

# 🧠 Code Explained (Comments Inside Code)

```java
import java.util.*;

class Main {

    // Node class represents one node in the binary tree
    static class Node{
        Node left;     // pointer to left child
        Node right;    // pointer to right child
        int data;      // value stored in node
        
        // constructor to create a node
        Node(int data){
            this.data = data;
        }
    }

    // ArrayList to store the boundary traversal result
    static ArrayList<Integer> list;

    // Function to collect LEFT BOUNDARY nodes
    static void leftNodes(Node root){

        // If node is null → nothing to process
        if(root == null) return;

        // If it is a leaf node → do not include in left boundary
        // because leaf nodes will be added separately
        if(root.left == null && root.right == null) return;

        // Add current node data
        list.add(root.data);

        // Prefer left child for boundary
        if(root.left != null)
            leftNodes(root.left);

        // If left child doesn't exist go to right child
        else
            leftNodes(root.right);
    }

    // Function to collect all LEAF nodes
    static void leafNodes(Node root){

        // If node is null return
        if(root == null) return;

        // If node is a leaf → add it
        if(root.left == null && root.right == null){
            list.add(root.data);
            return;
        }

        // Traverse left subtree
        leafNodes(root.left);

        // Traverse right subtree
        leafNodes(root.right);
    }

    // Function to collect RIGHT BOUNDARY nodes
    static void rightNodes(Node root){

        // If node is null return
        if(root == null) return;

        // Ignore leaf nodes
        if(root.left == null && root.right == null) return;

        // Prefer right child for boundary
        if(root.right != null)
            rightNodes(root.right);

        // If right child doesn't exist go to left
        else
            rightNodes(root.left);

        // Add node AFTER recursion
        // This reverses the order (bottom to top)
        list.add(root.data);
    }

    // Main function performing boundary traversal
    static ArrayList<Integer> boundaryTraversal(Node root) {

        // initialize result list
        list = new ArrayList<>();

        // If tree empty return empty list
        if(root == null) return list;

        // Step 1: Add root node
        list.add(root.data);

        // Step 2: Add left boundary
        leftNodes(root.left);

        // Step 3: Add leaf nodes from left subtree
        leafNodes(root.left);

        // Step 4: Add leaf nodes from right subtree
        leafNodes(root.right);

        // Step 5: Add right boundary nodes
        rightNodes(root.right);

        return list;
    }

    // Function to build binary tree from level order input
    static Node buildTree(String tree[]){

        // If tree is empty or first element null
        if(tree.length == 0 || tree[0].equals("null"))
            return null;

        // Create root node
        Node head = new Node(Integer.valueOf(tree[0]));

        // Queue used for level order construction
        Queue<Node> queue = new LinkedList<>();

        queue.add(head);

        int i = 1;

        // Loop until queue empty or input finished
        while(!queue.isEmpty() && i < tree.length){

            Node temp = queue.poll();

            // Left child
            if(!tree[i].equals("null")){
                temp.left = new Node(Integer.valueOf(tree[i]));
                queue.add(temp.left);
            }

            i++;

            // Right child
            if(i < tree.length && !tree[i].equals("null")){
                temp.right = new Node(Integer.valueOf(tree[i]));
                queue.add(temp.right);
            }

            i++;
        }

        return head;
    }

    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);

        // Input example:
        // 1 2 3 4 5 6 7
        String s = sc.nextLine();

        // split input into array
        String tree[] = s.split(" ");

        // build tree
        Node head = buildTree(tree);

        // perform boundary traversal
        ArrayList<Integer> list = boundaryTraversal(head);

        // print result
        for(int i : list)
            System.out.print(i+"->");
    }
}
```

---

# ⚠️ Where Mistakes Commonly Happen

### 1️⃣ Adding leaf nodes twice

If leaf nodes are included in left/right boundary functions → duplicates occur.

### 2️⃣ Forgetting reverse order for right boundary

Right boundary must be **bottom to top**.

That’s why insertion happens **after recursion**.

### 3️⃣ Forgetting null checks

```
if(root == null)
```

Without this → **NullPointerException**

### 4️⃣ Not handling single node tree

If tree has only one node, result should be:

```
1
```

### 5️⃣ Incorrect input format

Input must be **level order traversal**

Example:

```
1 2 3 4 5 null 7
```

---

# 🎯 15 MCQs (Important for Exams)

### 1

Boundary traversal order is:

A. Root → Leaves → Left → Right
B. Root → Left → Leaves → Right
C. Root → Right → Leaves → Left
D. Leaves → Root

✅ **Answer:** B

---

### 2

Which nodes are excluded in left boundary?

A. Root
B. Leaf nodes
C. Internal nodes
D. All nodes

✅ **Answer:** B

---

### 3

Why are leaf nodes collected separately?

A. To avoid duplicates
B. To reduce time complexity
C. To balance tree
D. To sort nodes

✅ **Answer:** A

---

### 4

Right boundary nodes are added:

A. Before recursion
B. After recursion
C. Randomly
D. In queue

✅ **Answer:** B

---

### 5

Which data structure is used to build the tree?

A. Stack
B. Queue
C. Array
D. Heap

✅ **Answer:** B

---

### 6

`queue.poll()` does what?

A. Adds element
B. Removes front element
C. Sorts queue
D. Prints queue

✅ **Answer:** B

---

### 7

Time complexity of boundary traversal is:

A. O(log n)
B. O(n²)
C. O(n)
D. O(1)

✅ **Answer:** C

---

### 8

Leaf node condition:

A. left != null && right != null
B. left == null && right == null
C. left == null
D. right == null

✅ **Answer:** B

---

### 9

Tree construction method used here:

A. DFS
B. Level Order
C. Postorder
D. Inorder

✅ **Answer:** B

---

### 10

If tree has only root node:

Output is:

A. Empty
B. Root twice
C. Root once
D. Error

✅ **Answer:** C

---

### 11

Which traversal collects leaves?

A. Inorder style recursion
B. BFS
C. Stack traversal
D. Heap traversal

✅ **Answer:** A

---

### 12

If left child doesn't exist in left boundary:

A. Stop
B. Go to parent
C. Go to right child
D. Ignore node

✅ **Answer:** C

---

### 13

Which structure stores final answer?

A. Stack
B. ArrayList
C. Queue
D. TreeMap

✅ **Answer:** B

---

### 14

Right boundary excludes:

A. root
B. leaves
C. internal nodes
D. children

✅ **Answer:** B

---

### 15

Worst case recursion depth is:

A. O(log n)
B. O(n)
C. O(1)
D. O(n²)

✅ **Answer:** B

---

✅ If you want, I can also show **one visualization trick professors love in exams** to **write boundary traversal in 10 seconds during coding rounds**.
