// FCFS Scheduling - Simple Java Code
import java.util.*;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int pid[] = new int[n];
        int at[] = new int[n];   // Arrival Time
        int bt[] = new int[n];   // Burst Time
        int ct[] = new int[n];   // Completion Time
        int tat[] = new int[n];  // Turnaround Time
        int wt[] = new int[n];   // Waiting Time
        int rt[] = new int[n];   // Response Time

        for (int i = 0; i < n; i++) {
            System.out.println("Enter Arrival Time and Burst Time for Process " + (i + 1) + ": ");
            pid[i] = i + 1;
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
        }

        // Sort by Arrival Time
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (at[i] > at[j]) {
                    int temp;
                    temp = at[i]; at[i] = at[j]; at[j] = temp;
                    temp = bt[i]; bt[i] = bt[j]; bt[j] = temp;
                    temp = pid[i]; pid[i] = pid[j]; pid[j] = temp;
                }
            }
        }

        // Calculate times
        int currentTime = 0;
        for (int i = 0; i < n; i++) {
            if (currentTime < at[i])
                currentTime = at[i]; // CPU waits for process to arrive

            ct[i] = currentTime + bt[i];
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
            rt[i] = wt[i]; // For FCFS, response time = waiting time
            currentTime = ct[i];
        }

        // Display results
        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT\tRT");
        double totalTAT = 0, totalWT = 0, totalRT = 0;
        for (int i = 0; i < n; i++) {
            totalTAT += tat[i];
            totalWT += wt[i];
            totalRT += rt[i];
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" +
                               ct[i] + "\t" + tat[i] + "\t" + wt[i] + "\t" + rt[i]);
        }

        System.out.println("\nAverage Turnaround Time: " + (totalTAT / n));
        System.out.println("Average Waiting Time: " + (totalWT / n));
        System.out.println("Average Response Time: " + (totalRT / n));
    }
}
