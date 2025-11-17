Perfect 👍 This is a clear and well-structured **Priority Scheduling (Non-Preemptive)** Java program.
Here’s your **complete formatted lab record** — ideal for FAT/practical notebook submission or viva prep.

---

## 🧮 **Experiment: Priority Scheduling (Non-Preemptive)**

---

### **Aim:**

To simulate the **Non-Preemptive Priority Scheduling Algorithm** and calculate **Completion Time (CT)**, **Turnaround Time (TAT)**, **Waiting Time (WT)**, and **Response Time (RT)** for all processes.

---

### **Objective:**

* To understand process scheduling based on **priority** instead of arrival time or burst time.
* To ensure that the process with **highest priority (lowest number)** is executed first among available processes.
* To compute all related scheduling metrics and their averages.

---

### **Algorithm:**

1. Start and input the number of processes, their **Arrival Time (AT)**, **Burst Time (BT)**, and **Priority (PR)**.
2. Initialize `currentTime = 0` and mark all processes as **not done**.
3. Repeat until all processes are completed:

   * Select the process with **highest priority (lowest PR value)** among those that have **arrived** (`AT <= currentTime`).
   * If no process is available, increment `currentTime` by 1 (CPU idle).
   * Compute:

     * `CT = currentTime + BT`
     * `TAT = CT - AT`
     * `WT = TAT - BT`
     * `RT = WT` (for non-preemptive scheduling)
   * Mark the process as done and update `currentTime = CT`.
4. After all processes are done, compute **average TAT**, **WT**, and **RT**.
5. Display the scheduling table.

---

### **Code:**

```java
// Simple Priority Scheduling (Non-Preemptive)
public class PrioritySimple {
    public static void main(String[] args) {
        int n = 4; // Number of processes

        // Given data (smaller number = higher priority)
        int pid[] = {1, 2, 3, 4};   // Process ID
        int at[]  = {0, 2, 4, 6};   // Arrival Time
        int bt[]  = {5, 3, 8, 6};   // Burst Time
        int pr[]  = {2, 1, 4, 3};   // Priority

        int ct[] = new int[n];  // Completion Time
        int tat[] = new int[n]; // Turnaround Time
        int wt[] = new int[n];  // Waiting Time
        int rt[] = new int[n];  // Response Time
        boolean done[] = new boolean[n]; // Process done flag

        int currentTime = 0, completed = 0;

        while (completed < n) {
            int idx = -1, bestPr = Integer.MAX_VALUE;

            // Pick process with highest priority (smallest pr) among arrived
            for (int i = 0; i < n; i++) {
                if (!done[i] && at[i] <= currentTime && pr[i] < bestPr) {
                    bestPr = pr[i];
                    idx = i;
                }
            }

            if (idx == -1) { // If no process arrived, just move time
                currentTime++;
                continue;
            }

            ct[idx] = currentTime + bt[idx];
            tat[idx] = ct[idx] - at[idx];
            wt[idx] = tat[idx] - bt[idx];
            rt[idx] = wt[idx];

            done[idx] = true;
            completed++;
            currentTime = ct[idx];
        }

        System.out.println("PID\tAT\tBT\tPR\tCT\tTAT\tWT\tRT");
        double totalTAT = 0, totalWT = 0, totalRT = 0;

        for (int i = 0; i < n; i++) {
            totalTAT += tat[i];
            totalWT += wt[i];
            totalRT += rt[i];
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" +
                               pr[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" +
                               wt[i] + "\t" + rt[i]);
        }

        System.out.println("\nAverage TAT = " + (totalTAT / n));
        System.out.println("Average WT  = " + (totalWT / n));
        System.out.println("Average RT  = " + (totalRT / n));
    }
}
```

---

### **Expected Output:**

```
PID	AT	BT	PR	CT	TAT	WT	RT
1	0	5	2	5	5	0	0
2	2	3	1	8	6	3	3
4	6	6	3	14	8	2	2
3	4	8	4	22	18	10	10

Average TAT = 9.25
Average WT  = 3.75
Average RT  = 3.75
```

*(Note: Output may slightly differ depending on arrival and priority conditions.)*

---

### **Observation:**

* Processes are selected based on **priority**, not arrival order.
* Lower priority number means **higher priority**.
* CPU may stay **idle** if no process has arrived yet.
* Once a process starts, it runs to completion (non-preemptive).

---

### **Conclusion:**

The **Non-Preemptive Priority Scheduling Algorithm** was successfully implemented.
It correctly selects processes based on priority and computes all timing parameters accurately.
This algorithm is efficient when priority control is needed, but it may cause **starvation** for low-priority processes.

---

Would you like me to prepare the same formatted content for **Round Robin Scheduling** next (with time quantum)?
