import java.util.*;

public class obst { 
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        
        int freq[] = new int[n];
        int keys[] = new int[n];
        int q[] = new int[n+1];
        
        int weight[][] = new int[n+1][n+1];
        int cost[][] = new int[n+1][n+1];
        int root[][] = new int[n+1][n+1]; // stores index of root
        
        for(int i=0; i<n; i++) keys[i] = sc.nextInt();
        for(int i=0; i<n; i++) freq[i] = sc.nextInt();
        for(int i=0; i<=n; i++) q[i] = sc.nextInt();
        
        for(int i=0; i<=n; i++){
            cost[i][i] = q[i];
            weight[i][i] = q[i];
        }
        
        for(int len = 1; len <= n; len++){
            for(int i=0; i<=n-len; i++){
                int j=i+len;
                weight[i][j] = weight[i][j-1]+freq[j-1]+q[j];
                cost[i][j] = Integer.MAX_VALUE;
                for(int k= i+1; k<=j; k++){
                    int c = cost[i][k-1]+cost[k][j]+weight[i][j];
                    if(c < cost[i][j]){
                        cost[i][j] = c;
                        root[i][j] = k; // store root index
                    }
                }
            }
        }
        
        // Printing matrices
        System.out.println("\nCost Matrix:");
        for(int i=0; i<=n; i++){
            for(int j=i; j<=n; j++){
                System.out.print(cost[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("\nWeight Matrix:");
        for(int i=0; i<=n; i++){
            for(int j=i; j<=n; j++){
                System.out.print(weight[i][j] + "\t");
            }
            System.out.println();
        }
        
        System.out.println("\nRoot Matrix (which key gives min cost):");
        for(int i=0; i<=n; i++){
            for(int j=i; j<=n; j++){
                System.out.print(root[i][j] + "\t");
            }
            System.out.println();
        }
        
        System.out.println("\nMinimum Cost of OBST: " + cost[0][n]);
        System.out.println("Root of the Optimal BST: " + root[0][n]);
    }
}
