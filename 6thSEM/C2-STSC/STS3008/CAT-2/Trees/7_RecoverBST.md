## 🧩 Problem Title

**Recover a Binary Search Tree (BST)**

---

## 📄 Problem Statement

A **Binary Search Tree (BST)** is given, but **two nodes of the tree are swapped by mistake**.

Your task is to **recover the tree without changing its structure**, such that the tree again satisfies the **BST property**.

BST property:

* Left subtree values **< root**
* Right subtree values **> root**
* Inorder traversal of a BST always gives **sorted order**

### Idea Used in This Code

1. Perform **inorder traversal** and store all values.
2. **Sort the values**.
3. Perform another **inorder traversal** and replace node values with sorted values.

This restores the BST.

---

## 📥 Input Format

```
Single line containing level-order traversal of the tree
Values separated by space
Use "null" for missing nodes
```

### Example Input

```
3 1 4 null null 2
```

Tree formed:

```
     3
    / \
   1   4
      /
     2
```

---

## 📤 Output Format

```
Print the inorder traversal of the corrected BST
```

### Example Output

```
1 2 3 4
```

---

# 💻 Code Explanation (Line by Line in Code)

```java
import java.util.*;

class Main {

    // Tree Node Structure
    static class TreeNode {

        int val;          // value of node
        TreeNode left;    // left child
        TreeNode right;   // right child

        TreeNode() {}

        // constructor with value
        TreeNode(int val) {
            this.val = val;
        }

        // constructor with children
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // index pointer used while rebuilding tree
    int idx = 0;

    // main function to recover BST
    public void recoverTree(TreeNode root) {

        // list to store inorder traversal values
        ArrayList<Integer> list = new ArrayList<>();

        // extract inorder values
        helperExtract(root, list);

        // sort values (correct BST order)
        Collections.sort(list);

        // rebuild tree using sorted values
        helperBuild(root, list);
    }

    // inorder traversal to extract values
    public void helperExtract(TreeNode root, ArrayList<Integer> list) {

        if (root == null) return;

        // visit left subtree
        helperExtract(root.left, list);

        // add current node value
        list.add(root.val);

        // visit right subtree
        helperExtract(root.right, list);
    }

    // rebuild tree using sorted inorder values
    public void helperBuild(TreeNode root, ArrayList<Integer> list) {

        if (root == null) return;

        // go left first
        helperBuild(root.left, list);

        // assign sorted value
        root.val = list.get(idx);

        // move to next value
        idx++;

        // go right
        helperBuild(root.right, list);
    }

    // print inorder traversal
    static void printInorder(TreeNode root) {

        if (root == null) return;

        printInorder(root.left);

        System.out.print(root.val + " ");

        printInorder(root.right);
    }

    // build tree from level order input
    static TreeNode buildTree(String[] values) {

        // if empty tree
        if (values.length == 0 || values[0].equals("null"))
            return null;

        // root node
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));

        // queue for level order construction
        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);

        int i = 1;

        while (!q.isEmpty() && i < values.length) {

            TreeNode curr = q.poll();

            // left child
            if (i < values.length && !values[i].equals("null")) {

                curr.left = new TreeNode(Integer.parseInt(values[i]));

                q.add(curr.left);
            }

            i++;

            // right child
            if (i < values.length && !values[i].equals("null")) {

                curr.right = new TreeNode(Integer.parseInt(values[i]));

                q.add(curr.right);
            }

            i++;
        }

        return root;
    }

    public static void main(String[] args) {

        // scanner input
        Scanner sc = new Scanner(System.in);

        // read level order traversal
        String[] input = sc.nextLine().split(" ");

        // build tree
        TreeNode root = buildTree(input);

        // object of main class
        Main obj = new Main();

        // recover BST
        obj.recoverTree(root);

        // print inorder traversal
        printInorder(root);
    }
}
```

---

# ⚠️ Common Mistakes / Cautions

### 1️⃣ Forgetting inorder property

BST inorder traversal must be **sorted**.

### 2️⃣ Not resetting index

`idx` must start from **0**.

### 3️⃣ Null handling

Always check

```
if(root == null)
```

in recursion.

### 4️⃣ Input parsing mistake

```
values[i].equals("null")
```

not

```
values[i] == "null"
```

### 5️⃣ Using preorder instead of inorder

If you extract using **preorder**, reconstruction fails.

### 6️⃣ Tree building errors

Wrong level-order build may produce wrong tree.

### 7️⃣ Forgetting Collections.sort

Then tree remains incorrect.

### 8️⃣ Not using global index

Local index resets in recursion.

### 9️⃣ Accessing list without bounds

```
list.get(idx)
```

must not exceed size.

### 🔟 Printing wrong traversal

Output requires **inorder traversal**.

---

# 🧠 15 MCQs

### 1️⃣ Inorder traversal of a BST gives:

A. Random order
B. Reverse order
C. **✅ Sorted order**
D. Preorder order

---

### 2️⃣ Time complexity of extracting inorder is:

A. O(log n)
B. **✅ O(n)**
C. O(n log n)
D. O(1)

---

### 3️⃣ Time complexity of sorting list:

A. O(n)
B. O(log n)
C. **✅ O(n log n)**
D. O(n²)

---

### 4️⃣ Total time complexity:

A. O(n)
B. **✅ O(n log n)**
C. O(log n)
D. O(n²)

---

### 5️⃣ Space complexity:

A. O(1)
B. O(log n)
C. **✅ O(n)**
D. O(n²)

---

### 6️⃣ Which traversal is used?

A. Preorder
B. Postorder
C. Level order
D. **✅ Inorder**

---

### 7️⃣ What data structure stores values?

A. Stack
B. Queue
C. **✅ ArrayList**
D. TreeMap

---

### 8️⃣ Tree is built using:

A. DFS
B. **✅ Level Order Traversal**
C. Postorder
D. Inorder

---

### 9️⃣ Queue is used in:

A. DFS
B. **✅ Level order tree construction**
C. Sorting
D. Recursion

---

### 🔟 What ensures correct BST order?

A. Queue
B. Stack
C. **✅ Sorting inorder values**
D. BFS

---

### 11️⃣ What does `idx` represent?

A. Node depth
B. Node height
C. **✅ Current index in sorted list**
D. Tree size

---

### 12️⃣ Which method extracts values?

A. recoverTree
B. helperBuild
C. **✅ helperExtract**
D. printInorder

---

### 13️⃣ Which method reconstructs BST?

A. helperExtract
B. printInorder
C. **✅ helperBuild**
D. buildTree

---

### 14️⃣ Recursion stops when:

A. node.val == 0
B. node.left == null
C. node.right == null
D. **✅ root == null**

---

### 15️⃣ Which traversal prints final BST?

A. Preorder
B. Postorder
C. **✅ Inorder**
D. Level order

---

## 📊 Complexity

| Operation       | Complexity     |
| --------------- | -------------- |
| Extract inorder | O(n)           |
| Sorting         | O(n log n)     |
| Rebuild tree    | O(n)           |
| Total           | **O(n log n)** |

Space = **O(n)**

---

✅ If you want, I can also show the **Optimal O(n) solution (without sorting and without extra space)** which is the **actual interview solution using inorder violation detection**. That one is **much more important for placements** (especially since you mentioned interviews coming in ~4-5 months).
