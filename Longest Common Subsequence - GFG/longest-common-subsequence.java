//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
	public static void main (String[] args) {

		Scanner sc=new Scanner(System.in);
		int test=sc.nextInt();
		while(test-- > 0){
		    int p=sc.nextInt();             // Take size of both the strings as input
		    int q=sc.nextInt();
		    
		    String s1=sc.next();            // Take both the string as input
	        String s2=sc.next();
		    
		    Solution obj = new Solution();
		    
		    System.out.println(obj.lcs(p, q, s1, s2));
		}
	}
}
// } Driver Code Ends


class Solution {
    //Function to find the length of longest common subsequence in two strings.
    static int lcs(int x, int y, String s1, String s2) {
        // int[][]cache = new int[x][y];
        // memoization
        // for(int[]ci:cache)
        //     Arrays.fill(ci, -1);
        // return dp(s1.toCharArray(), s2.toCharArray(), 0, 0, cache);
        
        // tabulation code
        int[][]cache = new int[x+1][y+1];
        for(int idx1 = x-1; idx1>=0; idx1--) {
            for(int idx2 = y-1; idx2>=0; idx2--) {
                if(s1.charAt(idx1) == s2.charAt(idx2))
                    cache[idx1][idx2] = 1 + cache[idx1+1][idx2+1];
                else 
                    cache[idx1][idx2] =  Math.max(cache[idx1+1][idx2], cache[idx1][idx2+1]);
            }
        }
        int max = 0;
        for(int[]cii:cache) {
            for(int ci:cii) {
                // System.out.print(ci);
                max = Math.max(ci, max);
            }
            // System.out.println();
        }
        return max;
    }
    
    static int dp(char[]ch1, char[]ch2, int idx1, int idx2, int[][]cache) {
        if(idx1 >= ch1.length || idx2 >= ch2.length)
            return 0;
        
        if(cache[idx1][idx2] != -1) return cache[idx1][idx2];
        
        if(ch1[idx1] == ch2[idx2])
            return cache[idx1][idx2] = 1 + dp(ch1, ch2, idx1+1, idx2+1, cache);
        
        else 
            return cache[idx1][idx2] =  Math.max(dp(ch1, ch2, idx1+1, idx2, cache), 
                                                 dp(ch1, ch2, idx1, idx2+1, cache));
    }
    
}