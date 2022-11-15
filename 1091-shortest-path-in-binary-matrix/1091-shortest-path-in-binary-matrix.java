class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if(grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;
        
        boolean[][]visited = new boolean[n][n];
        ArrayDeque<int[]> Q = new ArrayDeque<>();
        
        visited[0][0] = true;
        Q.offer(new int[]{0, 0, 1});
        
        while(!Q.isEmpty()) {
            int[]cur = Q.poll();
            int ci = cur[0], cj = cur[1], len = cur[2];
            if(ci == n-1 && cj == n-1) return len;
            
            int[]I = {-1, -1, 0, 1, 1, 1, 0, -1};
            int[]J = {0, 1, 1, 1, 0, -1, -1, -1};
            
            for(int pt = 0; pt < 8; pt++) {
                int ni = ci + I[pt], nj = cj + J[pt];
                if(ni >= 0 && ni < n && nj >= 0 && nj < n && !visited[ni][nj] && grid[ni][nj] == 0) {
                    visited[ni][nj] = true;
                    Q.offer(new int[]{ni, nj, len+1});
                }
            }
        }
        return -1;
    }
}