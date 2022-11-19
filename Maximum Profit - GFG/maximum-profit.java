//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;

class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader in =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int K = Integer.parseInt(in.readLine());
            int N = Integer.parseInt(in.readLine());
            String input_line[] = in.readLine().trim().split("\\s+");
            int A[] = new int[N];
            for (int i = 0; i < N; i++) A[i] = Integer.parseInt(input_line[i]);

            Solution ob = new Solution();
            System.out.println(ob.maxProfit(K, N, A));
        }
    }
}

// } Driver Code Ends


// User function Template for Java

class Solution {
    static int maxProfit(int K, int N, int A[]) {
        int[][][]cache = new int[N+1][2][K+1];
        // for(int[][]cii:cache)
        //     for(int[]ci:cii)
        //         Arrays.fill(ci, -1);
        // return dp(A, 0, 1, K, cache);
        
        // tabulation
        for(int ind = N-1; ind >= 0; ind--) {
            for(int buy = 0; buy <= 1; buy++) {
                for(int k = 1; k <= K; k++) {
                    int buyP = 0, sellP = 0, skipP = 0;
        
                    if(buy == 1 && k > 0) //  buy = 1 represents that i can buy stock
                        buyP = cache[ind+1][0][k] - A[ind];
                    
                    if(buy == 0) // buy = 0 represent that i have previously purchased some stock so i can't buy currently
                        sellP = cache[ind+1][1][k-1] + A[ind];
                        
                    skipP = cache[ind+1][buy][k];
                    
                    cache[ind][buy][k] = Math.max(buyP, Math.max(sellP, skipP));
                }
            }
        }
        return cache[0][1][K];
    }
    // memoization
    static int dp(int[]A, int ind, int buy, int k, int[][][]cache) {
        if(ind == A.length) return 0;
        
        if(cache[ind][buy][k] != -1)
            return cache[ind][buy][k];
        
        int buyP = 0, sellP = 0, skipP = 0;
        
        if(buy == 1 && k > 0) //  buy = 1 represents that i can buy stock
            buyP = dp(A, ind+1, 0, k, cache) - A[ind];
        
        if(buy == 0) // buy = 0 represent that i have previously purchased some stock so i can't buy currently
            sellP = dp(A, ind+1, 1, k-1, cache) + A[ind];
            
        skipP = dp(A, ind+1, buy,  k, cache);
        
        return cache[ind][buy][k] = Math.max(buyP, Math.max(sellP, skipP));
    }
}