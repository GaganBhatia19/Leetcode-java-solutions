//{ Driver Code Starts
import java.util.*;
import java.io.*;
import java.lang.*;

class DriverClass
{
    public static void main(String args[]) throws IOException {

        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String str[] = read.readLine().trim().split(" ");
            int V = Integer.parseInt(str[0]);
            int E = Integer.parseInt(str[1]);
    
            ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();
            for(int i=0;i<V;i++)
            {
                adj.add(new ArrayList<ArrayList<Integer>>());
            }
            
            int i=0;
            while (i++<E) {
                String S[] = read.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                int w = Integer.parseInt(S[2]);
                ArrayList<Integer> t1 = new ArrayList<Integer>();
                ArrayList<Integer> t2 = new ArrayList<Integer>();
                t1.add(v);
                t1.add(w);
                t2.add(u);
                t2.add(w);
                adj.get(u).add(t1);
                adj.get(v).add(t2);
            }
            
            Solution ob = new Solution();
            
            System.out.println(ob.spanningTree(V, adj));
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution
{
    //Function to find sum of weights of edges of the Minimum Spanning Tree.
    static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) 
    {
        // Prim's Algorithm
        // System.out.println(adj); // [ [[1, 5], [2, 1]], [[0, 5], [2, 3]], [[1, 3], [0, 1]]]
        // for(ArrayList<Integer> vi : adj.get(0))
        //     System.out.println(vi);
        int sum = 0;
        boolean[]visited = new boolean[V];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0, -1});
        visited[0] = true;
        while(!pq.isEmpty()) {
            int[]cur = pq.poll();
            int wt = cur[0], cnode = cur[1], parentNode = cur[2];
            if(!visited[cnode]) {
                visited[cnode] = true;
                sum += wt;
            }
            // check if all are visited then break execution
            if(allVisited(visited)) break;
            
            for(ArrayList<Integer> vi : adj.get(cnode)) {
                if(!visited[vi.get(0)])
                    pq.offer(new int[]{vi.get(1), vi.get(0), cnode});
            }
        }
        return sum;
    }
    private static boolean allVisited(boolean[]visited) {
        for(boolean b : visited) 
            if(!b) return false;
        return true;
    }
}
