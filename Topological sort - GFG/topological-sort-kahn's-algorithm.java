//{ Driver Code Starts
import java.util.*;
import java.io.*;
import java.lang.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            String st[] = read.readLine().trim().split("\\s+");
            int edg = Integer.parseInt(st[0]);
            int nov = Integer.parseInt(st[1]);

            for (int i = 0; i < nov + 1; i++)
                list.add(i, new ArrayList<Integer>());

            int p = 0;
            for (int i = 1; i <= edg; i++) {
                String s[] = read.readLine().trim().split("\\s+");
                int u = Integer.parseInt(s[0]);
                int v = Integer.parseInt(s[1]);
                list.get(u).add(v);
            }

            int[] res = new Solution().topoSort(nov, list);

            if (check(list, nov, res) == true)
                System.out.println("1");
            else
                System.out.println("0");
        }
    }
    static boolean check(ArrayList<ArrayList<Integer>> list, int V, int[] res) {
        
        if(V!=res.length)
        return false;
        
        int[] map = new int[V];
        for (int i = 0; i < V; i++) {
            map[res[i]] = i;
        }
        for (int i = 0; i < V; i++) {
            for (int v : list.get(i)) {
                if (map[i] > map[v]) return false;
            }
        }
        return true;
    }
}

// } Driver Code Ends


/*Complete the function below*/


class Solution
{
    //Function to return list containing vertices in Topological order. 
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) 
    {
        //dfs method
        /*
        boolean[]visited = new boolean[V];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for(int vi=0; vi<V; vi++) {
            if(!visited[vi])
                dfs(vi, adj, visited, stack);
        }
        // empty the stack to trace the order
        int[]result = new int[V];
        int i=0;
        while(!stack.isEmpty())
            result[i++] = stack.pop();
        return result;
        */
        
        // Kahn's algorithm
        int[]indegree = new int[V];
        for(int vi=0; vi<V; vi++) {
            for(int vj:adj.get(vi)) {
                indegree[vj]++;
            }
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for(int vi=0; vi<V; vi++)
            if(indegree[vi] == 0)
                queue.offer(vi);
        int[]result = new int[V];
        int itr = 0;
        while(!queue.isEmpty()) {
            int curn = queue.poll();
            for(int vi:adj.get(curn)) {
                indegree[vi]--;
                if(indegree[vi] == 0)
                    queue.offer(vi);
            }
            result[itr++] = curn;
        }
        // for(int val:result) System.out.print(val + " ");
        return result;
    }
    private static void dfs(int V, ArrayList<ArrayList<Integer>> adj, boolean[]visited, ArrayDeque<Integer> stack) {
        visited[V] = true;
        for(int vi:adj.get(V))
            if(!visited[vi])
                dfs(vi, adj, visited, stack);
        stack.push(V);
    }
}
