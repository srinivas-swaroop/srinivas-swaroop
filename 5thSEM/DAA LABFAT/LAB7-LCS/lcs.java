/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.*;
public class lcs
{   static int lps(String s, int l, int r){ //longestpalindromicsequence
        if(l == r) return 1;
        if(l > r) return 0;
        
        if(s.charAt(l) == s.charAt(r)) return 2+lps(s, l+1, r-1);
        
        return Math.max(lps(s, l+1, r), lps(s, l, r-1));
    }
    
    static int lis(int arr[]){ //longestincreasingsequence
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i : arr){
            int idx = Collections.binarySearch(list, i);
            
            if(idx < 0) idx = -idx-1; //this is used to locate crct insertion point if element is not there in list
            
            if(idx == list.size()) list.add(i);
            else list.set(idx,i);
        }
        
        for(int i : list) System.out.print(i+" "); //prints sequence
        return list.size();
    }
    
    
	public static void main(String args[]) {

		int arr[] = {5,3,8,10,2,6,12};
		String s = "saspaskaas";
		System.out.println(lis(arr));
		System.out.println(lps(s, 0, s.length()-1));
	}
}

-------------

class LCS {
    public static void main(String[] args) {
        String s1 = "ABCDEF";
        String s2 = "FBDAMN";
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m+1][n+1];
        
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
                else if (s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        
        System.out.println(dp[m][n]);
    }
}