// dfs approach
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[]coloured = new int[n];
        Arrays.fill(coloured, -1);
        for(int i = 0; i<n; i++) { // graph may not be connected
            if(coloured[i] == -1) {
                coloured[i] = 0;
                if(!dfs(i, -1, graph, coloured)) return false;
            }
        }
        return true;
    }
    private boolean dfs(int curi, int previ, int[][]graph, int[]coloured) {
        if(previ != -1) {
            if(coloured[previ] == 0) coloured[curi] = 1;
            else coloured[curi] = 0;
        }
        boolean res = true;
        for(int vi:graph[curi]) {
            if(coloured[vi] == -1) res &= dfs(vi, curi, graph, coloured);
            else {
                if(coloured[vi] == coloured[curi]) 
                    return false;
            }
        }
        return res;
    }
}