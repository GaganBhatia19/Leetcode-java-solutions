class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // k -> source
        // using bellman_ford
        
        return bellman_ford(times, n, k);
    }
    private int bellman_ford(int[][] times, int n, int k) {
        int[]dist = new int[n+1];
        Arrays.fill(dist, (int)1e9);
        dist[k] = 0;
        for(int i=0; i<n; i++) {
            relax(times, dist);
        }
        int max = 0;
        for(int i=1; i<=n; i++) if(dist[i] > max) max = dist[i];
        return max == (int)1e9 ? -1 : max;
    }
    private void relax(int[][]times, int[]dist) {
        for(int[]ti:times) {
            int u = ti[0], v = ti[1], w = ti[2];
            if(dist[u] + w < dist[v])
                dist[v] = dist[u] + w;
        }
    }
}