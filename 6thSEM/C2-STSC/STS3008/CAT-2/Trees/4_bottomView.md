Below is the **exam-focused breakdown** for your program (Bottom View of Binary Tree). I’ll keep it **structured and concise so you can directly revise before coding exams.**

---

# 1. Problem Statement

You are given the **level-order representation of a binary tree**, where `"null"` indicates that a node is missing.

Your task is to **print the Bottom View of the binary tree**.

### What is Bottom View?

The **bottom view** of a binary tree consists of the nodes that are visible when the tree is viewed **from the bottom**.

Each node has a **horizontal distance (HD)** from the root.

Rules:

```
Root → HD = 0
Left child → HD - 1
Right child → HD + 1
```

For each horizontal distance, the **last node encountered during BFS traversal** becomes part of the **bottom view**.

---

# 2. Example

### Input

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
HD  0 : 5
HD +1 : 3
HD +2 : 6
```

### Output

```
4 2 5 3 6
```

Explanation
Node **5 replaces 1** at HD = 0 because it is **lower in the tree**.

---

# 3. Code (Given Solution)

```java
import java.util.*;

public class Main{

    static class Node{
        Node left;
        Node right;
        int val;

        Node(int val){
            this.val = val;
        }
    }

    static class Pair{
        Node nd;
        int level;

        Pair(Node nd, int level){
            this.nd = nd;
            this.level = level;
        }
    }

    static Node buildTree(String tree[]){

        if(tree.length == 0 || tree[0].equals("null"))
            return null;

        Queue<Node> queue = new LinkedList<>();

        Node head = new Node(Integer.parseInt(tree[0]));

        queue.add(head);

        int i = 1;

        while(!queue.isEmpty() && i < tree.length){

            Node temp = queue.poll();

            if(!tree[i].equals("null")){

                Node leftNode = new Node(Integer.parseInt(tree[i]));

                temp.left = leftNode;

                queue.add(leftNode);
            }

            i++;

            if(i < tree.length && !tree[i].equals("null")){

                Node rightNode = new Node(Integer.parseInt(tree[i]));

                temp.right = rightNode;

                queue.add(rightNode);
            }

            i++;
        }

        return head;
    }

    static List<Integer> bottomView(Node head){

        ArrayList<Integer> list = new ArrayList<>();

        Map<Integer,Integer> map = new TreeMap<>();

        Queue<Pair> queue = new LinkedList<>();

        queue.add(new Pair(head,0));

        while(!queue.isEmpty()){

            Pair temp = queue.poll();

            int level = temp.level;

            int value = temp.nd.val;

            map.put(level,value);

            if(temp.nd.left != null)
                queue.add(new Pair(temp.nd.left, level-1));

            if(temp.nd.right != null)
                queue.add(new Pair(temp.nd.right, level+1));
        }

        list.addAll(map.values());

        return list;
    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        String tree[] = s.split(" ");

        Node head = buildTree(tree);

        List<Integer> list = bottomView(head);

        for(int i : list)
            System.out.print(i+" ");
    }
}
```

---

# 4. Time Complexity

Traversal uses **BFS** and visits each node once.

Operations:

```
BFS traversal → O(n)
TreeMap insertion → O(log n)
```

### Final Complexity

```
O(n log n)
```

---

# 5. Space Complexity

Memory used by:

```
Queue → O(n)
TreeMap → O(n)
Tree nodes → O(n)
```

### Final Space Complexity

```
O(n)
```

---

# 6. MCQs (Exam Practice)

### Q1

What traversal is used in bottom view?

A) DFS
B) BFS ✅
C) Inorder
D) Postorder

---

### Q2

Horizontal distance of root is

A) -1
B) 1
C) 0 ✅
D) Depends

---

### Q3

Why is `map.put(level,value)` used without checking?

A) To store duplicates
B) To replace previous node with deeper node ✅
C) To reduce memory
D) To increase speed

---

### Q4

Which data structure keeps HD values sorted?

A) HashMap
B) TreeMap ✅
C) Array
D) Queue

---

### Q5

Time complexity of algorithm?

A) O(n²)
B) O(n log n) ✅
C) O(n)
D) O(log n)

---

### Q6

Left child horizontal distance

A) hd + 1
B) hd - 1 ✅
C) hd
D) 0

---

### Q7

Which class stores node + horizontal distance?

A) Node
B) Pair ✅
C) Map
D) Queue

---

### Q8

What does BFS ensure here?

A) Correct order of nodes
B) Nodes processed level by level ✅
C) Tree balancing
D) Faster search

---

### Q9

Worst case space complexity

A) O(1)
B) O(log n)
C) O(n) ✅
D) O(n²)

---

### Q10

If `HashMap` replaces `TreeMap`, what happens?

A) Compilation error
B) Unsorted output ✅
C) Faster execution
D) Memory leak

---

# 7. Common Mistakes Students Make

### 1 Not checking `"null"` nodes

This causes

```
NumberFormatException
```

Always check:

```
!tree[i].equals("null")
```

---

### 2 Forgetting index bound check

Wrong

```
tree[i]
```

Correct

```
i < tree.length
```

---

### 3 Reversing horizontal distance rule

Correct rule

```
Left → hd - 1
Right → hd + 1
```

---

### 4 Using DFS incorrectly

Bottom view requires **level order traversal**.

---

### 5 Using `HashMap` instead of `TreeMap`

Output becomes **unsorted**.

---

# 8. Syntax to Be Careful With

### Queue Declaration

Correct

```java
Queue<Pair> queue = new LinkedList<>();
```

Wrong

```java
Queue<Pair> queue = new Queue<>();
```

---

### Parsing integers

Always check `"null"` first

```java
Integer.parseInt(tree[i])
```

---

### Generic List Declaration

Correct

```java
ArrayList<Integer> list = new ArrayList<>();
```

Avoid raw type

```java
new ArrayList()
```

---

### Pair insertion

Correct

```java
queue.add(new Pair(node, level));
```

---

# 9. Important Pattern

This problem belongs to

```
Binary Tree
+
BFS Traversal
+
Horizontal Distance Mapping
```

---

# 10. Very Important Exam Insight

This **same template** solves 4 common exam problems:

```
Top View
Bottom View
Vertical Order Traversal
Vertical Sum
```

Only **1 line of map logic changes**.

Example:

Top View

```
if(!map.containsKey(level))
    map.put(level,value);
```

Bottom View

```
map.put(level,value);
```

---

If you want, I can also show you **a trick professors use to combine Top View + Bottom View + Vertical Order in one single template (very useful in coding exams).**
