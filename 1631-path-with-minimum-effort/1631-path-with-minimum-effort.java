class Solution {
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][]mem = new int[m][n];
        for(int[]mi:mem) Arrays.fill(mi, (int)1e9);
        mem[0][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        // priority for abs diff.
        // 0 -> abs diff, 1 -> ci, 2 -> cj
        pq.offer(new int[]{0, 0, 0});
        while(!pq.isEmpty()) {
            int[]cur = pq.poll();
            int ci = cur[1], cj = cur[2], absDiff = cur[0];
            int[]d = {-1, 0, 1, 0};
            for(int i = 0, j = 3; i < 4 && j >= 0; i++, j--) {
                int ni = ci + d[i], nj = cj + d[j];
                if(ni >= 0 && ni < m && nj >=0 && nj < n) {
                    int newAbsDiff = Math.abs(heights[ni][nj] - heights[ci][cj]);
                    int effort = Math.max(newAbsDiff, absDiff);
                    if(effort < mem[ni][nj]) {
                        mem[ni][nj] = effort;
                        pq.offer(new int[]{effort, ni, nj});
                    }
                }
            }
        }
        return mem[m-1][n-1];
    }
}