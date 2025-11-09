/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.*;
public class mcm
{ static String buildOrder(int i, int j, int split[][]){
    if(i == j) return "M"+i;
    
    return "("+buildOrder(i, split[i][j], split)+ buildOrder(split[i][j]+1, j, split)+")";
}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int arr[] = new int[n];
		
		for(int i=0; i<n; i++){
		    arr[i] = sc.nextInt();
		}
		
		int dp[][] = new int[n][n];
		int split[][] = new int[n][n];
		
		for(int i=0; i<n; i++){
		    dp[i][i] = 0;
		}
		
		for(int len = 2; len < n; len++){
		    for(int i=1; i <= n-len; i++){
		        int j = i+len-1;
		        dp[i][j] = Integer.MAX_VALUE;
		        
		        for(int k=i; k<j; k++){
		            int cost = dp[i][k]+dp[k+1][j]+arr[i-1]*arr[k]*arr[j];
		            
		            if(cost < dp[i][j]){
		                dp[i][j] = cost;
		                split[i][j] = k;
		            }
		        }
		        
		    }
		}
		
		System.out.println(dp[1][n-1]);
		System.out.println(buildOrder(1,n-1, split));
		
		
	}
}