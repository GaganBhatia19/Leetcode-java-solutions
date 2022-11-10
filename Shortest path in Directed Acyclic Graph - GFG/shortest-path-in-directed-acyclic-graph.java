//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		while (T-- > 0) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			int[][] edge = new int[m][3];
			for (int i = 0; i < m; i++) {
				edge[i][0] = sc.nextInt();
				edge[i][1] = sc.nextInt();
				edge[i][2] = sc.nextInt();
			}
			Solution obj = new Solution();
			int res[] = obj.shortestPath(n, m,edge);
			for (int i = 0; i < n; i++) {
				System.out.print(res[i] + " ");
			}
			System.out.println();
		}
	}
}
// } Driver Code Ends


//User function Template for Java
class Solution {

	public int[] shortestPath(int N,int M, int[][] edges) {
		//create adj list
		Map<Integer, List<int[]>> adj = new HashMap<>();
		for(int i=0; i<M; i++) {
		    int start = edges[i][0], end = edges[i][1], weight = edges[i][2];
		    int[]pair = new int[]{end, weight};
		    List<int[]> list;
		    if(adj.containsKey(start))
		        list = adj.get(start);
		    else list = new ArrayList<>();
		    list.add(pair);
		    adj.put(start, list);
		}
		
		// find topological sort
		int[]indegree = new int[N];
		for(int i=0; i<N; i++) {
		    if(adj.get(i) != null) {
		        for(int[]pair:adj.get(i)) {
		            indegree[pair[0]]++;
		        }
		    } 
		}
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		for(int i=0; i<N; i++)
		    if(indegree[i] == 0)
		        queue.offer(i);
		List<Integer> topoSort = new ArrayList<>();
		while(!queue.isEmpty()) {
		    int curn = queue.poll();
		    if(adj.get(curn) != null) {
		        for(int[]pair:adj.get(curn)) {
		            if(--indegree[pair[0]] == 0)
		                queue.offer(pair[0]);
		        }
		    }
		    topoSort.add(curn);
		}
		// take elements from list and start relaxing nodes
		int[]dist = new int[N];
		Arrays.fill(dist, (int)1e9);
		dist[0] = 0;
		for(int i=0; i<N; i++) {
		    if(adj.get(i) != null) {
		        for(int[]pair:adj.get(i)) {
		            int end = pair[0], weight = pair[1];
		          //  if(dist[end] == -1) continue;
		            dist[end] = Math.min(dist[end], dist[i] + weight);
		          //  if(dist[end] == (int)1e9) dist[end] = -1;
		        }
		    }
		}
		for(int i=0; i<N; i++)
		    if(dist[i] == (int)1e9) 
		        dist[i] = -1;
		return dist;
	}
}