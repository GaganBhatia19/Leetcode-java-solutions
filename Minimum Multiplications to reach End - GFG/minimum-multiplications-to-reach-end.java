//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;

// Position this line where user code will be pasted.

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }

            int start = sc.nextInt();
            int end = sc.nextInt();

            Solution ob = new Solution();
            int ans = ob.minimumMultiplications(a, start, end);
            System.out.println(ans);
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {
    int minimumMultiplications(int[] arr, int start, int end) {
        
        int[]mem = new int[100000];
        Arrays.fill(mem, (int)1e9);
        
        Queue<int[]> Q = new ArrayDeque<>();
        Q.offer(new int[]{start, 0});
        
        mem[start] = 0;
        
        while(!Q.isEmpty()) {
            int[]pair = Q.poll();
            int curStart = pair[0], level = pair[1];
            for(int ni:arr) {
                int newStart = (curStart * ni) % 100000;
                if(level+1 < mem[newStart]){ 
                    mem[newStart] = level+1;
                    Q.offer(new int[]{newStart, level+1});
                }
            }
        }
        return mem[end] == (int)1e9 ? -1 : mem[end];
    }
}
