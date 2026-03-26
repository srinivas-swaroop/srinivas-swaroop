Right View is **almost identical to Left View**.
Only **one small change** is needed.

Instead of taking the **first node of each level**, we take the **last node of each level**.

---

# 1. What is Right View?

The **right view of a binary tree** contains nodes visible when the tree is viewed from the **right side**.

### Example Tree

```
        1
       / \
      2   3
     / \   \
    4   5   6
```

Visible from the **right side**

```
1 3 6
```

Because at each level the **rightmost node** is visible.

---

# 2. Key Idea

During **Level Order Traversal (BFS)**:

* **Left View → first node of level**
* **Right View → last node of level**

---

# 3. Right View Code (BFS)

```java
import java.util.*;

class Solution {

    ArrayList<Integer> rightView(Node root){

        ArrayList<Integer> res = new ArrayList<>();

        if(root == null)
            return res;

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){

            int size = q.size();

            for(int i = 0; i < size; i++){

                Node node = q.poll();

                // last node of level
                if(i == size - 1)
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
```

---

# 4. Full Program (Right View + Tree Build)

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

    static Node buildTree(String str){

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

    ArrayList<Integer> rightView(Node root){

        ArrayList<Integer> res = new ArrayList<>();

        if(root == null)
            return res;

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){

            int size = q.size();

            for(int i=0;i<size;i++){

                Node node = q.poll();

                if(i == size - 1)
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

        ArrayList<Integer> ans = obj.rightView(root);

        for(int x : ans)
            System.out.print(x + " ");
    }
}
```

---

# 5. Example

Input

```
1 2 3 4 5 N 6
```

Tree

```
        1
       / \
      2   3
     / \   \
    4   5   6
```

Output

```
1 3 6
```

---

# 6. DFS Method (Interview Trick)

Instead of BFS we can do **DFS (Root → Right → Left)**.

```java
class Solution {

    ArrayList<Integer> res = new ArrayList<>();

    void dfs(Node root, int level){

        if(root == null)
            return;

        if(level == res.size())
            res.add(root.data);

        dfs(root.right, level+1);
        dfs(root.left, level+1);
    }

    ArrayList<Integer> rightView(Node root){

        dfs(root,0);

        return res;
    }
}
```

---

# 7. Quick Comparison (Very Important)

| Problem        | Condition           | Traversal |
| -------------- | ------------------- | --------- |
| Left View      | `i == 0`            | BFS       |
| Right View     | `i == size-1`       | BFS       |
| Left View DFS  | Root → Left → Right |           |
| Right View DFS | Root → Right → Left |           |

---

# 8. Exam Trick

Whenever you see

```
Left View / Right View
```

Think:

```
Level Order Traversal
+
Take one node per level
```

---

If you want, I can also show you the **single Binary Tree template that solves 8 problems in placements**, including:

* Left View
* Right View
* Top View
* Bottom View
* Vertical Order
* Level Order
* Zigzag Traversal

Once you learn that **one template**, all these become **very easy in exams**.
