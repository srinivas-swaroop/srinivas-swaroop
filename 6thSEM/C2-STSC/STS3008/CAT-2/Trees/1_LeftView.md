# Left View of Binary Tree

---

# 1. Problem Understanding

A **binary tree** has nodes where each node may have:

* a **left child**
* a **right child**

When we **look at the tree from the left side**, some nodes hide others.

The **left view** of a binary tree contains:

> The **first node visible at every level** when viewed from the **left side**.

### Example

```
        1
       / \
      2   3
     / \   \
    4   5   6
```

Levels:

```
Level 0 → 1
Level 1 → 2
Level 2 → 4
```

Left view:

```
1 2 4
```

Because these are the **first nodes encountered at each level**.

---

# 2. Input Variations

Exam setters can design many input types.

---

## Normal Case

```
        1
       / \
      2   3
     /     \
    4       5
```

Output

```
1 2 4
```

---

## Single Node

```
1
```

Output

```
1
```

---

## Left Skewed Tree

```
1
|
2
|
3
|
4
```

Output

```
1 2 3 4
```

---

## Right Skewed Tree

```
1
 \
  2
   \
    3
     \
      4
```

Output

```
1 2 3 4
```

Because every node becomes first node of level.

---

## Complete Binary Tree

```
        10
      /    \
     20     30
    / \    / \
   40 50  60 70
```

Output

```
10 20 40
```

---

## Duplicate Values

```
       5
      / \
     5   5
    /
   5
```

Output

```
5 5 5
```

---

## Negative Values

```
      -1
      / \
    -2  -3
```

Output

```
-1 -2
```

---

## Empty Tree

```
null
```

Output

```
(empty)
```

---

## Large Tree

```
Nodes = 10^5
```

Tree depth may be large.

---

# 3. Test Case Bank

---

# Basic Cases

### Case 1

Input

```
        1
       / \
      2   3
     / \
    4   5
```

Output

```
1 2 4
```

Explanation
First node at each level.

---

### Case 2

Input

```
1
```

Output

```
1
```

---

# Edge Cases

### Case 3

Input

```
1
 \
  2
   \
    3
```

Output

```
1 2 3
```

---

### Case 4

Input

```
     10
    /
   20
  /
 30
```

Output

```
10 20 30
```

---

# Corner Cases

### Case 5

```
        8
       / \
      3   10
       \
        6
```

Output

```
8 3 6
```

---

### Case 6

```
       5
      / \
     2   9
        /
       7
```

Output

```
5 2 7
```

---

# Stress Case

Tree with

```
100000 nodes
```

Mostly skewed.

---

# Hidden Test Cases

Examiners often use:

1. Single node
2. Right skewed tree
3. Only left nodes
4. Duplicate values
5. Deep tree

---

# 4. Different Coding Approaches

---

# Approach 1 — Brute Force (Level Order + Store Levels)

## Intuition

1. Traverse tree level by level
2. Store nodes of each level
3. Print **first node of every level**

---

## Pseudocode

```
perform level order traversal

for each level
    print first node
```

---

## Java Code

```java
import java.util.*;

class Solution {

    ArrayList<Integer> leftView(Node root) {

        ArrayList<Integer> res = new ArrayList<>();

        if(root == null) return res;

        Queue<Node> q = new LinkedList<>();

        q.add(root);

        while(!q.isEmpty()){

            int size = q.size();

            for(int i=0;i<size;i++){

                Node temp = q.poll();

                if(i == 0)
                    res.add(temp.data);

                if(temp.left != null)
                    q.add(temp.left);

                if(temp.right != null)
                    q.add(temp.right);
            }
        }

        return res;
    }
}
```

---

## Complexity

```
Time  : O(n)
Space : O(n)
```

---

# Approach 2 — DFS with Level Tracking (Better)

## Intuition

We traverse:

```
Root → Left → Right
```

When visiting first node of level → add it.

Track **current level**.

---

## Pseudocode

```
if level == result.size
    add node

call left subtree
call right subtree
```

---

## Java Code

```java
class Solution {

    ArrayList<Integer> res = new ArrayList<>();

    void dfs(Node root, int level){

        if(root == null)
            return;

        if(level == res.size())
            res.add(root.data);

        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }

    ArrayList<Integer> leftView(Node root) {

        dfs(root, 0);

        return res;
    }
}
```

---

## Complexity

```
Time  : O(n)
Space : O(h)
```

Where

```
h = tree height
```

---

# Approach 3 — Optimal BFS (Most Used)

This is the **most common exam solution**.

---

## Intuition

Use **Queue (Level Order Traversal)**.

At each level:

```
first node = left view
```

---

## Pseudocode

```
queue.add(root)

while queue not empty

    size = queue.size

    for i in size

        node = poll

        if i == 0
            add node

        push left
        push right
```

---

## Java Code

```java
import java.util.*;

class Node{
    int data;
    Node left,right;

    Node(int data){
        this.data = data;
        left = right = null;
    }
}

class BinaryTree{

    public static Node buildTree(String str){

        if(str.length() == 0 || str.charAt(0) == 'N')
            return null;

        String[] ip = str.split(" ");

        Node root = new Node(Integer.parseInt(ip[0]));

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        int i = 1;

        while(!q.isEmpty() && i < ip.length){

            Node curr = q.poll();

            String val = ip[i];

            if(!val.equals("N")){
                curr.left = new Node(Integer.parseInt(val));
                q.add(curr.left);
            }

            i++;

            if(i >= ip.length)
                break;

            val = ip[i];

            if(!val.equals("N")){
                curr.right = new Node(Integer.parseInt(val));
                q.add(curr.right);
            }

            i++;
        }

        return root;
    }
}

class Solution{

    ArrayList<Integer> leftView(Node root){

        ArrayList<Integer> res = new ArrayList<>();

        if(root == null)
            return res;

        Queue<Node> q = new LinkedList<>();

        q.add(root);

        while(!q.isEmpty()){

            int size = q.size();

            for(int i=0;i<size;i++){

                Node node = q.poll();

                if(i == 0)
                    res.add(node.data);

                if(node.left != null)
                    q.add(node.left);

                if(node.right != null)
                    q.add(node.right);
            }
        }

        return res;
    }
}

public class Main{

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        Node root = BinaryTree.buildTree(input);

        Solution obj = new Solution();

        ArrayList<Integer> ans = obj.leftView(root);

        for(int x : ans)
            System.out.print(x + " ");
    }
}
```

---

# 5. Input Handling Variants

---

# Case 1 — Single Line Input

```
1 2 3 4 5 N 6
```

Use **N = null node**.

---

# Case 2 — Multiple Line Input

```
7
1 2 3 4 5 N 6
```

First line = number of nodes.

---

# Case 3 — Test Cases

```
T
Tree1
Tree2
Tree3
```

Example code

```java
int T = sc.nextInt();

while(T-- > 0){

    Node root = buildTree();
}
```

---

# Case 4 — Array Input

Tree represented as array:

```
[1,2,3,4,5,null,6]
```

---

# Case 5 — Matrix Input

Rare.

Adjacency matrix.

---

# Case 6 — String Input

```
"1 2 3 4 5 N 6"
```

Split using

```
split(" ")
```

---

# 6. Common Mistakes Students Make

### Mistake 1

Printing **all nodes in level** instead of first.

---

### Mistake 2

Checking

```
if(i == size)
```

Instead of

```
if(i == 0)
```

---

### Mistake 3

Forgetting

```
root == null
```

---

### Mistake 4

Not pushing children correctly.

---

### Mistake 5

DFS order wrong

Must be

```
left before right
```

---

# 7. Possible MCQ Questions

---

### Question 1

Which traversal is used for left view?

A) Inorder
B) Preorder
C) Level Order ✅
D) Postorder

---

### Question 2

Time complexity?

A) O(log n)
B) O(n) ✅
C) O(n²)
D) O(1)

---

### Question 3

Left view selects:

A) Last node in level
B) First node in level ✅
C) Middle node
D) Random node

---

### Question 4

Which data structure is used in BFS?

A) Stack
B) Queue ✅
C) Deque
D) Heap

---

# 8. Dry Run

---

# Normal Case

```
        1
       / \
      2   3
     / \
    4   5
```

Queue

```
[1]
```

Level 0

```
1 → add
```

Queue

```
[2,3]
```

Level 1

```
2 → add
3
```

Queue

```
[4,5]
```

Level 2

```
4 → add
5
```

Output

```
1 2 4
```

---

# Edge Case

```
1
 \
  2
   \
    3
```

Level 0

```
1
```

Level 1

```
2
```

Level 2

```
3
```

Output

```
1 2 3
```

---

# 9. Pattern Identification

This problem belongs to:

```
Tree Traversal
+
Level Order Traversal
+
BFS
```

Alternative pattern:

```
DFS with level tracking
```

---

# 10. Exam Tips

### Tip 1

Keyword

```
"first node of each level"
```

Immediately think

```
Level Order Traversal
```

---

### Tip 2

Condition

```
if(i == 0)
```

---

### Tip 3

Queue size = level size.

---

### Tip 4

Always check

```
root == null
```

---

### Tip 5

If DFS used

```
Left first
Right later
```

---

✅ If you want, I can also show you the **5 most asked Binary Tree view problems in placements**:

1. Left View
2. Right View
3. Top View
4. Bottom View
5. Vertical Order Traversal

They all follow **one powerful pattern** that makes them **very easy to solve in exams**.
