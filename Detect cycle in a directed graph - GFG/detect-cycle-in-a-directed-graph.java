//{ Driver Code Starts
import java.util.*;
import java.io.*;
import java.lang.*;

class DriverClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            int V = sc.nextInt();
            int E = sc.nextInt();
            for (int i = 0; i < V; i++)
                list.add(i, new ArrayList<Integer>());
            for (int i = 0; i < E; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                list.get(u).add(v);
            }
            if (new Solution().isCyclic(V, list) == true)
                System.out.println("1");
            else
                System.out.println("0");
        }
    }
}
// } Driver Code Ends


/*Complete the function below*/

class Solution {
    // Function to detect cycle in a directed graph.
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        // dfs approch
        /*
        boolean[]vis = new boolean[V];
        boolean[]pathVis = new boolean[V];
        for(int i=0; i<V; i++) {
            if(!vis[i]) {
                if(dfs(i, adj, vis, pathVis))
                    return true;
            }
        }
        return false;
        */
        // Kahn's Algo. / Topological Sort
        int[]indegree = new int[V];
        for(int i=0; i<V; i++)
            for(int vi:adj.get(i))
                indegree[vi]++;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for(int i=0; i<V; i++) 
            if(indegree[i] == 0)
                queue.offer(i);
        List<Integer> topoSort = new ArrayList<>();
        while(!queue.isEmpty()) {
            int curn = queue.poll();
            for(int vi:adj.get(curn))
                if(--indegree[vi] == 0)
                    queue.offer(vi);
            topoSort.add(curn);
        }
        return topoSort.size() < V;
    }
    private boolean dfs(int v, ArrayList<ArrayList<Integer>> adj, boolean[]vis, boolean[]pathVis) {
        // if(adj.get(v).size() == 0) return false;
        // if(vis[v] && pathVis[v]) return true;
        vis[v] = pathVis[v] = true;
        for(int vi:adj.get(v)) {
            if(!vis[vi] && dfs(vi, adj, vis, pathVis)) return true;
            else if(pathVis[vi]) return true;
        }
        pathVis[v] = false;
        return false;
    }
}