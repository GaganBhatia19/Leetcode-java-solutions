//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;
@SuppressWarnings("unchecked") class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int edges[][] = new int[m][3];
            for (int i = 0; i < m; i++) {
                edges[i][0] = sc.nextInt();
                edges[i][1] = sc.nextInt();
                edges[i][2] = sc.nextInt();
            }
            Solution obj = new Solution();
            List<Integer> ans = obj.shortestPath(n, m, edges);
            for (int e : ans) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {
    public static List<Integer> shortestPath(int n, int m, int edges[][]) {
        // construct adj
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for(int[]ei:edges) {
            int start = ei[0], end = ei[1], weight = ei[2];
            // undirected 
            // start -> end
            List<int[]> list1 = new ArrayList<>();
            if(adj.containsKey(start)) list1 = adj.get(start);
            list1.add(new int[]{end, weight});
            adj.put(start, list1);
            // end -> start
            List<int[]> list2 = new ArrayList<>();
            if(adj.containsKey(end)) list2 = adj.get(end);
            list2.add(new int[]{start, weight});
            adj.put(end, list2);
        }
        
        // dijkstra's algo
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        int[]dist = new int[n+1];
        Arrays.fill(dist, (int)1e9);
        dist[1] = 0;
        
        int[]trace = new int[n+1];
        Arrays.fill(trace, -1);
        
        pq.offer(new int[]{0,1});
        
        while(!pq.isEmpty()) {
            int[]pair = pq.poll();
            int d = pair[0], curNode = pair[1];
            if(adj.get(curNode) == null) continue;
            for(int[]ni:adj.get(curNode)) {
                int newDist = d + ni[1]; // ni[1] -> weight
                if(newDist < dist[ni[0]]) {
                    dist[ni[0]] = newDist;
                    pq.offer(new int[]{newDist, ni[0]});
                    trace[ni[0]] = curNode;
                }
            }
        }
        // backtrack from last node and trace path using tracing array
        // for(int i:trace) System.out.print(i + " ");
        if(trace[n] == -1)
            return new ArrayList<>(List.of(-1));
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int node = n;
        while(node != 1) {
            stack.push(node);
            node = trace[node];
        }
        List<Integer> result = new ArrayList<>();
        result.add(1);
        while(!stack.isEmpty())
            result.add(stack.pop());
        return result;
    }
}