//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m=sc.nextInt();
            int[][] edge = new int[m][2];
            for(int i=0;i<m;i++){
                edge[i][0]=sc.nextInt();
                edge[i][1]=sc.nextInt();
            }
            int src=sc.nextInt();
            Solution obj = new Solution();
            int res[] = obj.shortestPath(edge,n,m,src);
            for(int i=0;i<n;i++){
                System.out.print(res[i]+" ");
            }
            System.out.println();
        }
    }
}

// } Driver Code Ends


class Solution {
    
    public int[] shortestPath(int[][] data,int n,int m ,int src) {
        // create adj list
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        for(int[]pair:data) {
            List<Integer> list1, list2;
            if(adj.containsKey(pair[0]))
                list1 = adj.get(pair[0]);
            else list1 = new ArrayList<>();
            if(adj.containsKey(pair[1]))
                list2 = adj.get(pair[1]);
            else list2 = new ArrayList<>();
            
            list1.add(pair[1]);
            adj.put(pair[0], list1);
            
            list2.add(pair[0]);
            adj.put(pair[1], list2);
        }
        // for(int i=0; i<n; i++) System.out.println(i + " : " + adj.get(i));
        
        // apply bfs with relaxation
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int[]distance = new int[n];
        boolean[]visited = new boolean[n];
        Arrays.fill(distance, (int)1e9);
        distance[src] = 0; // 0 is source
        visited[src] = true;
        queue.offer(src);
        while(!queue.isEmpty()) {
            int curn = queue.poll();
            if(adj.get(curn) == null) continue;
            for(int vi:adj.get(curn)) {
                if(!visited[vi]) {
                    visited[vi] = true;
                    queue.offer(vi);
                }
                distance[vi] = Math.min(distance[vi], distance[curn] + 1);
            }
        }
        // mark -1
        for(int i=0; i<n; i++) if(distance[i] == (int)1e9) distance[i] = -1;
        return distance;
    }
}