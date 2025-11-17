Perfect — this is a clean **FCFS (First Come First Serve) Scheduling Algorithm** in Java.
Here’s the **complete lab record** format including **Aim, Objective, Algorithm, Code, Output, Observation, and Conclusion**, exactly how it’s written in lab exams 👇

---

## 🧮 **Experiment: First Come First Serve (FCFS) CPU Scheduling Algorithm**

---

### **Aim:**

To simulate the **First Come First Serve (FCFS)** CPU scheduling algorithm and calculate **Completion Time (CT)**, **Turnaround Time (TAT)**, **Waiting Time (WT)**, and **Response Time (RT)** for each process.

---

### **Objective:**

* To understand how processes are executed in the order of their arrival time.
* To calculate all scheduling parameters for each process.
* To compute **average turnaround time, waiting time, and response time**.

---

### **Algorithm:**

1. Input number of processes, their **Arrival Time (AT)** and **Burst Time (BT)**.
2. Sort all processes according to **Arrival Time (AT)**.
3. Initialize `currentTime = 0`.
4. For each process:

   * If `currentTime < AT`, CPU waits until process arrives.
   * Compute:

     * `CT = currentTime + BT`
     * `TAT = CT - AT`
     * `WT = TAT - BT`
     * `RT = WT` (for FCFS, since no preemption)
   * Update `currentTime = CT`.
5. Display the scheduling table.
6. Compute and display average **TAT**, **WT**, and **RT**.

---

### **Code:**

```java
// Simple FCFS (First Come First Serve) Scheduling
public class FCFS_Simple {
    public static void main(String[] args) {
        int n = 4;

        // Given (unsorted) process data
        int pid[] = {1, 2, 3, 4};        // Process IDs
        int at[]  = {3, 1, 0, 2};        // Arrival Time (unsorted)
        int bt[]  = {4, 3, 5, 2};        // Burst Time

        int ct[] = new int[n];  // Completion Time
        int tat[] = new int[n]; // Turnaround Time
        int wt[] = new int[n];  // Waiting Time
        int rt[] = new int[n];  // Response Time

        // Sort by Arrival Time
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (at[i] > at[j]) {
                    int temp = at[i]; at[i] = at[j]; at[j] = temp;
                    temp = bt[i]; bt[i] = bt[j]; bt[j] = temp;
                    temp = pid[i]; pid[i] = pid[j]; pid[j] = temp;
                }
            }
        }

        int currentTime = 0;
        for (int i = 0; i < n; i++) {
            if (currentTime < at[i]) currentTime = at[i]; // CPU waits if idle

            ct[i] = currentTime + bt[i];
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
            rt[i] = wt[i];

            currentTime = ct[i];
        }

        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT\tRT");
        double totalTAT = 0, totalWT = 0, totalRT = 0;
        for (int i = 0; i < n; i++) {
            totalTAT += tat[i];
            totalWT += wt[i];
            totalRT += rt[i];
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" +
                               ct[i] + "\t" + tat[i] + "\t" + wt[i] + "\t" + rt[i]);
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
PID	AT	BT	CT	TAT	WT	RT
3	0	5	5	5	0	0
2	1	3	8	7	4	4
4	2	2	10	8	6	6
1	3	4	14	11	7	7

Average TAT = 7.75
Average WT  = 4.25
Average RT  = 4.25
```

*(Note: Values may differ slightly depending on sorting and timing.)*

---

### **Observation:**

* Processes are executed in the order of **arrival time**.
* CPU remains idle if no process has arrived.
* FCFS is **non-preemptive** — once a process starts, it completes fully before the next begins.

---

### **Conclusion:**

The FCFS scheduling algorithm was successfully implemented.
It schedules processes in the order they arrive, and the corresponding **CT**, **TAT**, **WT**, and **RT** values were correctly calculated along with their averages.

---

Would you like me to now prepare the same **lab record format** for **SJF (Shortest Job First)** next?
