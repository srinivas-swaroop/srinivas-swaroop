Perfect 👏
You’ve provided a **simple and non-threaded** version of the **Dining Philosopher Problem** — great for lab exams since it’s short, clear, and avoids complexity.

Here’s the **complete formatted lab FAT content** (Aim → Objective → Algorithm → Problem Statement → Code → Expected Output → Conclusion).

---

### **Experiment: Dining Philosopher Problem**

#### **Aim:**

To simulate the **Dining Philosopher Problem** demonstrating synchronization and resource sharing between multiple processes using forks.

---

#### **Objective:**

* To understand the **concept of synchronization** and **deadlock prevention** in concurrent processes.
* To simulate philosophers (processes) competing for shared resources (forks).
* To ensure **no two neighboring philosophers eat simultaneously**, avoiding deadlock.

---

#### **Algorithm:**

1. Start the program.
2. Initialize `n` philosophers and `n` forks (one between each pair).
3. Mark all forks as **available (true)** initially.
4. Randomize the order of philosophers to simulate unpredictable behavior.
5. For each philosopher in random order:

   * Identify **left** and **right** forks.
   * If both forks are available, the philosopher **starts eating**.
   * After eating (using sleep delay), **release both forks**.
   * If not available, the philosopher **waits (cannot eat now)**.
6. Continue for all philosophers.
7. End program.

---

#### **Problem Statement:**

Simulate the **Dining Philosopher Problem** to illustrate how multiple processes can access shared resources without causing **deadlock** or **starvation**.

---

#### **Code:**

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n = 5; // Number of philosophers
        boolean[] fork = new boolean[n];
        for (int i = 0; i < n; i++) fork[i] = true; // All forks are free

        List<Integer> order = new ArrayList<>();
        for (int i = 0; i < n; i++) order.add(i);
        Collections.shuffle(order); // Randomize philosopher order

        for (int i : order) {
            int left = i, right = (i + 1) % n;
            System.out.println("\nPhilosopher " + (i + 1) + " is thinking...");

            if (fork[left] && fork[right] && Math.random() > 0.3) {
                fork[left] = fork[right] = false;
                System.out.println("Philosopher " + (i + 1) + " starts eating.");
                try { Thread.sleep((int)(Math.random() * 1000)); } catch (Exception e) {}
                fork[left] = fork[right] = true;
                System.out.println("Philosopher " + (i + 1) + " finished eating.");
            } else {
                System.out.println("Philosopher " + (i + 1) + " cannot eat now (fork not available).");
            }
        }
    }
}
```

---

#### **Expected Output:**

*(Output may vary each run due to randomization)*

```
Philosopher 3 is thinking...
Philosopher 3 starts eating.
Philosopher 3 finished eating.

Philosopher 1 is thinking...
Philosopher 1 cannot eat now (fork not available).

Philosopher 5 is thinking...
Philosopher 5 starts eating.
Philosopher 5 finished eating.

Philosopher 2 is thinking...
Philosopher 2 cannot eat now (fork not available).

Philosopher 4 is thinking...
Philosopher 4 starts eating.
Philosopher 4 finished eating.
```

---

#### **Observation:**

* Only philosophers with both forks available are allowed to eat.
* The random order and conditional eating simulate concurrent access to limited resources.
* No deadlock occurs as forks are released after use.

---

#### **Conclusion:**

The **Dining Philosopher Problem** demonstrates the need for **proper synchronization** when multiple processes share resources.
This solution avoids deadlock by ensuring that each philosopher can only eat when both forks are available and releases them afterward.

---

Would you like me to make the **thread-based version (true concurrent)** next — still simple but realistic for an OS lab viva?
