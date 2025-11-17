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

            // Formula: CT = current time + BT
            ct[i] = currentTime + bt[i];

            // Formula: TAT = CT - AT
            tat[i] = ct[i] - at[i];

            // Formula: WT = TAT - BT
            wt[i] = tat[i] - bt[i];

            // Formula: RT = WT (for FCFS)
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
