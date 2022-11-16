class Solution {
    int mod = (int)1e9 + 7;
    public int countPaths(int n, int[][] roads) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for(int[]ri:roads) {
            List<int[]> list1 = new ArrayList<>(), list2 = new ArrayList<>();
            int from = ri[0], to = ri[1], ti = ri[2];
            if(adj.containsKey(from)) list1 = adj.get(from);
            list1.add(new int[]{to, ti});
            adj.put(from, list1);
            
            if(adj.containsKey(to)) list2 = adj.get(to);
            list2.add(new int[]{from, ti});
            adj.put(to, list2);
        }
        // for(int key:adj.keySet()) {
        //     System.out.print(key + " : ");
        //     for(int[]i:adj.get(key)) System.out.print(i[0] + " " +i[1]+ ", ");
        //     System.out.println();
        // }
        // System.out.println(adj);
        
        int[]time = new int[n];
        Arrays.fill(time, Integer.MAX_VALUE);
        
        int[]ways = new int[n];
        ways[0] = 1;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        
        time[0] = 0;
        
        pq.offer(new int[]{0, 0});
        
        while(!pq.isEmpty()) {
            int[]cur = pq.poll();
            int t = cur[0], curNode = cur[1];
            // System.out.println(t + " " + curNode);
            if(adj.get(curNode) == null) continue;
            for(int[]ni:adj.get(curNode)) {
                int nt = t + ni[1];
                if(nt < time[ni[0]]) {
                    time[ni[0]] = nt;
                    ways[ni[0]] = ways[curNode] % mod;
                    pq.offer(new int[]{nt, ni[0]});
                }
                else if(nt  == time[ni[0]]) 
                    ways[ni[0]] = (ways[ni[0]] + ways[curNode]) % mod;
            }
        }
        
        return ways[n-1] % mod;
    }
}