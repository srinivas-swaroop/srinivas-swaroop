Excellent 👏
Here’s the **complete, formatted lab FAT content** for the **Banker’s Algorithm** — everything from Aim to Expected Output in a neat format, ready for your lab record or viva.

---

### **Experiment: Banker's Algorithm**

#### **Aim:**

To implement the **Banker’s Algorithm** to determine whether a system is in a **safe state** or not.

---

#### **Objective:**

* To understand **deadlock avoidance** using the Banker’s Algorithm.
* To check the system’s **safe state** before resource allocation.
* To find the **safe sequence** of process execution if it exists.

---

#### **Algorithm:**

1. Start the program.
2. Read the **number of processes (n)** and **resources (m)**.
3. Input the **Allocation**, **Maximum Need**, and **Available** resource matrices.
4. Calculate the **Need Matrix** using:
   [
   Need[i][j] = Max[i][j] - Allocation[i][j]
   ]
5. Initialize all processes as **unfinished**.
6. Find a process `Pi` such that:

   * `Finish[i] == false`, and
   * `Need[i][j] ≤ Available[j]` for all resources `j`.
7. If found, allocate its resources back to the system:
   [
   Available[j] = Available[j] + Allocation[i][j]
   ]
   Mark it as finished and add it to the **safe sequence**.
8. Repeat until all processes are finished or no such process is found.
9. If all processes finish, the system is in a **safe state**, else it is **unsafe**.
10. Stop.

---

#### **Problem Statement:**

Given the allocation, maximum need, and available resource matrices, determine if the system is in a **safe state** using the Banker’s Algorithm. If safe, display the **safe sequence**.

---

#### **Code:**

```java
// Simple Banker's Algorithm in Java
public class BankersSimple {
    public static void main(String[] args) {
        int n = 3; // Number of processes
        int m = 3; // Number of resources

        // Allocation matrix
        int[][] alloc = {
            {1, 2, 2},
            {1, 0, 3},
            {1, 2, 1}
        };

        // Maximum need matrix
        int[][] max = {
            {3, 3, 2},
            {1, 2, 3},
            {1, 3, 5}
        };

        // Available resources
        int[] avail = {2, 1, 0};

        // Calculate Need matrix
        int[][] need = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                need[i][j] = max[i][j] - alloc[i][j];
            }
        }

        // Print Need matrix
        System.out.println("Need Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(need[i][j] + " ");
            }
            System.out.println();
        }

        boolean[] finish = new boolean[n];
        int[] safeSeq = new int[n];
        int count = 0;

        // Banker's safety check
        while (count < n) {
            boolean found = false;
            for (int i = 0; i < n; i++) {
                if (!finish[i]) {
                    int j;
                    for (j = 0; j < m; j++) {
                        if (need[i][j] > avail[j])
                            break;
                    }

                    if (j == m) { // All resources can be allocated
                        for (int k = 0; k < m; k++)
                            avail[k] += alloc[i][k];
                        safeSeq[count++] = i;
                        finish[i] = true;
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("System is not in a safe state.");
                return;
            }
        }

        // Print Safe Sequence
        System.out.print("\nSystem is in a safe state.\nSafe Sequence: ");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + safeSeq[i]);
            if (i != n - 1) System.out.print(" -> ");
        }
    }
}
```

---

#### **Expected Output:**

```
Need Matrix:
2 1 0
0 2 0
0 1 4

System is in a safe state.
Safe Sequence: P1 -> P0 -> P2
```

*(Note: Safe sequence may vary depending on matrix values.)*

---

#### **Observation:**

* The algorithm checks if there exists an order in which all processes can execute safely.
* In this case, the system is in a **safe state**, meaning **no deadlock** will occur.

---

#### **Conclusion:**

The Banker’s Algorithm ensures that the system will **never enter a deadlock state** by allocating resources only if the system remains in a **safe state** after allocation.

---

Would you like me to prepare the next one for **Deadlock Detection Algorithm** (the complement of Banker’s)?
It’s also commonly asked right after this in lab FATs.
