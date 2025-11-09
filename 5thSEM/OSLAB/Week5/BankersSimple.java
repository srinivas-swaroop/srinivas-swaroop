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

                    if (j == m) { // If all resources can be allocated
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
