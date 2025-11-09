// Priority Scheduling (Non-Preemptive) - Simple Java Code
// Calculates CT, TAT, WT, RT and their averages

import java.util.*;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Input number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        // Step 2: Declare arrays
        int pid[] = new int[n];   // Process ID
        int at[] = new int[n];    // Arrival Time
        int bt[] = new int[n];    // Burst Time
        int pr[] = new int[n];    // Priority (smaller number = higher priority)
        int ct[] = new int[n];    // Completion Time
        int tat[] = new int[n];   // Turnaround Time
        int wt[] = new int[n];    // Waiting Time
        int rt[] = new int[n];    // Response Time
        int done[] = new int[n];  // To mark completed processes

        // Step 3: Take input
        for (int i = 0; i < n; i++) {
            System.out.println("Enter Arrival Time, Burst Time and Priority for Process " + (i + 1) + ": ");
            pid[i] = i + 1;
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            pr[i] = sc.nextInt();
            done[i] = 0;
        }

        int currentTime = 0;   // Keeps track of system time
        int completed = 0;     // Number of processes completed

        // Step 4: Repeat until all processes are done
        while (completed < n) {
            int idx = -1;           // Process to execute next
            int highestPriority = Integer.MAX_VALUE; // smaller = higher priority

            // Step 5: Choose process with highest priority among arrived ones
            for (int i = 0; i < n; i++) {
                if (at[i] <= currentTime && done[i] == 0) {
                    if (pr[i] < highestPriority) {
                        highestPriority = pr[i];
                        idx = i;
                    }
                }
            }

            // Step 6: If no process has arrived, move time forward
            if (idx == -1) {
                currentTime++;
            } 
            // Step 7: Run selected process
            else {
                ct[idx] = currentTime + bt[idx];  // Completion time
                tat[idx] = ct[idx] - at[idx];     // Turnaround = CT - AT
                wt[idx] = tat[idx] - bt[idx];     // Waiting = TAT - BT
                rt[idx] = wt[idx];                // Response = Waiting (non-preemptive)
                done[idx] = 1;                    // Mark process as completed
                completed++;                      // Increase completed count
                currentTime = ct[idx];            // Update time to process end
            }
        }

        // Step 8: Print the results
        System.out.println("\nPID\tAT\tBT\tPR\tCT\tTAT\tWT\tRT");
        double totalTAT = 0, totalWT = 0, totalRT = 0;

        for (int i = 0; i < n; i++) {
            totalTAT += tat[i];
            totalWT += wt[i];
            totalRT += rt[i];
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + pr[i] + "\t" +
                               ct[i] + "\t" + tat[i] + "\t" + wt[i] + "\t" + rt[i]);
        }

        // Step 9: Print averages
        System.out.println("\nAverage Turnaround Time: " + (totalTAT / n));
        System.out.println("Average Waiting Time: " + (totalWT / n));
        System.out.println("Average Response Time: " + (totalRT / n));
    }
}
