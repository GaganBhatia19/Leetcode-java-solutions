//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            String[] S1 = br.readLine().trim().split(" ");
            int n = Integer.parseInt(S1[0]);
            int m = Integer.parseInt(S1[1]);
            int[][] edges = new int[m][3];
            for(int i = 0; i < m; i++){
                String S2[] = br.readLine().trim().split(" ");
                for(int j = 0; j < 3; j++)
                    edges[i][j] = Integer.parseInt(S2[j]);
            }
            Solution obj = new Solution();
            int ans = obj.isNegativeWeightCycle(n, edges);
            System.out.println(ans);
        }
    }
}

// } Driver Code Ends


//User function Template for Java

class Solution
{
    public int isNegativeWeightCycle(int n, int[][] edges)
    {
        // bellman-ford algorithm
        
        // relax all nodes for n-1 times
        
        int[]dist = new int[n];
        Arrays.fill(dist, (int)1e9);
        dist[0] = 0;
        int itr = 0;
        while(itr++ < n) {
            relax(edges, dist);
        } // relax n-1 times
        int[]temp = new int[n];
        for(int i=0; i<n; i++) temp[i] = dist[i]; // copy the value of dist into temp
        relax(edges, dist); // relax nth time
        // now compare
        for(int i=0; i<n; i++)
            if(dist[i] != temp[i])
                return 1;
                
        return 0;
    }
    private void relax(int[][]edges, int[]dist) {
        for(int[]ei:edges) {
            int u = ei[0], v = ei[1], wt = ei[2];
            if(dist[u] + wt < dist[v]) {
                dist[v] = dist[u] + wt;
            }
        }
    }
}