class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int[]coloured = new int[n];
        Arrays.fill(coloured, -1);
        for(int gi=0; gi<n; gi++) {
            queue.offer(gi);
            coloured[gi] = -2; // marking -2  to say that the particular node has been touched
            while(!queue.isEmpty()) {
                int cur = queue.poll();
                int val = -1;
                for(int i=0; i<graph[cur].length; i++) {
                    int ni = graph[cur][i];
                    if(coloured[ni] != -2){
                        if(coloured[ni] == -1){ 
                            queue.offer(ni);
                            coloured[ni] = -2;
                        }
                        else {
                            if(val == -1) {
                                if(coloured[ni] == 0) val = 1;
                                else if(coloured[ni] == 1) val = 0;
                            } else {
                                if(val == coloured[ni])
                                    return false;
                            }
                        }
                    }
                }
                coloured[cur] = val==-1?0:val;
            }
        }
        return true;
    }
}