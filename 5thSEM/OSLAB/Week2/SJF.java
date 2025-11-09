// Shortest Job First (Non-Preemptive) Scheduling in Java
// Calculates: Completion Time (CT), Turnaround Time (TAT), Waiting Time (WT), Response Time (RT)

import java.util.*;

class SJF {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Input number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        // Step 2: Declare arrays
        int pid[] = new int[n];   // Process IDs
        int at[] = new int[n];    // Arrival Times
        int bt[] = new int[n];    // Burst Times
        int ct[] = new int[n];    // Completion Times
        int tat[] = new int[n];   // Turnaround Times
        int wt[] = new int[n];    // Waiting Times
        int rt[] = new int[n];    // Response Times
        int done[] = new int[n];  // To check if a process is completed

        // Step 3: Take input for each process
        for (int i = 0; i < n; i++) {
            System.out.println("Enter Arrival Time and Burst Time for Process " + (i + 1) + ": ");
            pid[i] = i + 1;
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            done[i] = 0; // initially, all processes are incomplete
        }

        int currentTime = 0;   // Keeps track of CPU's current time
        int completed = 0;     // Counts how many processes are done

        // Step 4: Repeat until all processes are completed
        while (completed < n) {
            int idx = -1;              // index of process with shortest burst time
            int minBT = Integer.MAX_VALUE; // initialize with a large value

            // Step 5: Find the process with the smallest burst time among arrived processes
            for (int i = 0; i < n; i++) {
                if (at[i] <= currentTime && done[i] == 0) { // process has arrived and not yet executed
                    if (bt[i] < minBT) {  // select process with minimum burst time
                        minBT = bt[i];
                        idx = i;
                    }
                }
            }

            // Step 6: If no process has arrived yet, move time forward
            if (idx == -1) {
                currentTime++;
            } 
            // Step 7: Execute the selected process
            else {
                ct[idx] = currentTime + bt[idx];  // completion time = current time + burst time
                tat[idx] = ct[idx] - at[idx];     // turnaround time = completion - arrival
                wt[idx] = tat[idx] - bt[idx];     // waiting time = turnaround - burst
                rt[idx] = wt[idx];                // for non-preemptive, response = waiting
                done[idx] = 1;                    // mark process as completed
                completed++;                      // increase count of completed processes
                currentTime = ct[idx];            // update current time
            }
        }

        // Step 8: Display the results in tabular form
        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT\tRT");
        double totalTAT = 0, totalWT = 0, totalRT = 0;

        for (int i = 0; i < n; i++) {
            totalTAT += tat[i];
            totalWT += wt[i];
            totalRT += rt[i];
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" +
                               ct[i] + "\t" + tat[i] + "\t" + wt[i] + "\t" + rt[i]);
        }

        // Step 9: Calculate and print average times
        System.out.println("\nAverage Turnaround Time: " + (totalTAT / n));
        System.out.println("Average Waiting Time: " + (totalWT / n));
        System.out.println("Average Response Time: " + (totalRT / n));
    }
}
