
import java.util.*;

class Main {
    static int n;
    static int arr[];
    static String[][] order; // to store order of multiplication

    // Recursive function to find minimum cost and build order
    static int matrixChainRecursive(int i, int j, int[] arr) {
        if (i == j) {
            order[i][j] = "M" + i;
            return 0;
        }

        int min = Integer.MAX_VALUE;
        String bestOrder = "";

        for (int k = i; k < j; k++) {
            int costLeft = matrixChainRecursive(i, k, arr);
            int costRight = matrixChainRecursive(k + 1, j, arr);
            int cost = costLeft + costRight + arr[i - 1] * arr[k] * arr[j];

            if (cost < min) {
                min = cost;
                bestOrder = "(" + order[i][k] + order[k + 1][j] + ")";
            }
        }

        order[i][j] = bestOrder;
        return min;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        order = new String[n][n];
        int minCost = matrixChainRecursive(1, n - 1, arr);

        System.out.println(minCost);
        System.out.println(order[1][n - 1]);
    }
}
