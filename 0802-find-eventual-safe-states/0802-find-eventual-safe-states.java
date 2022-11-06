class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        /*
            Check for every node does it makes cycle,
                if it makes cycle then it's not a safe state
                else it's a safe state and add to final list
        */
        List<Integer> safe = new ArrayList<>();
        int V = graph.length;
        boolean[]vis = new boolean[V];
        boolean[]pathVis = new boolean[V];
        for(int vi=0; vi<V; vi++) 
            if(!isCycle(vi, graph, vis, pathVis))
                safe.add(vi);
        
        return safe;
    }
    // dfs approach
    private boolean isCycle(int v, int[][]graph, boolean[]vis, boolean[]pathVis) {
        vis[v] = pathVis[v] = true;
        for(int vi:graph[v]) {
            if(!vis[vi] && isCycle(vi, graph, vis, pathVis)) 
                return true;
            else if(pathVis[vi])
                return true;
        }
        pathVis[v] = false;
        return false;
    }
}