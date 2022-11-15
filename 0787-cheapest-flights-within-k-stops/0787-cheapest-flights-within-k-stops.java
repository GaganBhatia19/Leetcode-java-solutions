class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Dijkstra over directed graph with node limitation -> k
        // construct adj
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for(int[]fi:flights) {
            int from = fi[0], to = fi[1], price = fi[2];
            List<int[]> list = new ArrayList<>();
            if(adj.containsKey(from)) 
                list = adj.get(from);
            list.add(new int[]{to, price});
            adj.put(from, list);
        }
        // apply dijkstra's algo
        int[]dist = new int[n];
        Arrays.fill(dist, (int)1e9);
        dist[src] = 0;
        
        Queue<int[]> Q = new ArrayDeque<>();
        Q.offer(new int[]{0, k, src});
        
        while(!Q.isEmpty()) {
            int[]cur = Q.poll();
            int d = cur[0], nk = cur[1], curNode = cur[2];
            if(nk == -1) continue; // don't go further this node as you might increase the stops
            if(adj.get(curNode) == null) continue;
            for(int[]ni:adj.get(curNode)) {
                int nd = d + ni[1]; //ni[1] -> price .... ni[0] -> to
                if(nd < dist[ni[0]]) {
                    dist[ni[0]] = nd;
                    Q.offer(new int[]{nd, nk-1, ni[0]});
                }
            }
        }
        
        return dist[dst] == (int)1e9 ? -1 : dist[dst];
    }
}