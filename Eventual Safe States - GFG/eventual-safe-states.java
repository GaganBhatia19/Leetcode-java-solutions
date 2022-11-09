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
            int V = sc.nextInt();
            int E = sc.nextInt();

            List<List<Integer>> adj = new ArrayList<>();

            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
            for (int i = 0; i < E; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();

                adj.get(u).add(v);
            }

            Solution obj = new Solution();
            List<Integer> safeNodes = obj.eventualSafeNodes(V, adj);
            for (int i : safeNodes) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {

    List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {
        Map<Integer, List<Integer>> adjr = new HashMap<>();
        // reverse graph
        for(int i=0; i<V; i++) {
            for(int vi:adj.get(i)) {
                List<Integer> list;
                if(adjr.containsKey(vi)) 
                    list = adjr.get(vi);
                else list = new ArrayList<>();
                list.add(i);
                adjr.put(vi, list);
            }
        }
        
        // for(int i=0; i<V; i++) {
            // for(int vi:adjr.get(i)) {
            //     System.out.print(vi + " ");
            // }
            // System.out.println(adjr.get(4));
        // }
        
        // Apply Kahn's algo
        int[]indegree = new int[V];
        for(int i=0; i<V; i++) 
            if(adjr.get(i) != null)
                for(int vi:adjr.get(i))
                    indegree[vi]++;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for(int i=0; i<V; i++)
            if(indegree[i] == 0)
                queue.offer(i);
        List<Integer> safe = new ArrayList<>();
        while(!queue.isEmpty()) {
            int curn = queue.poll();
            if(adjr.get(curn) != null) {
                for(int vi:adjr.get(curn)) {
                    if(--indegree[vi] == 0)
                        queue.offer(vi);
                }
            }
            
            safe.add(curn);
        }
        Collections.sort(safe);
        return safe;
    }
}
