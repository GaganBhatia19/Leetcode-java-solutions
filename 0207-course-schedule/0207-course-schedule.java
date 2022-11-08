class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1 - create adjacency list 
        ArrayList<Integer>[]adj = new ArrayList[numCourses];
        for(int[]pair:prerequisites) {
            int i = pair[0], val = pair[1];
            if(adj[i] == null)
                adj[i] = new ArrayList<>();
            adj[i].add(val);
        }
        // for(int i=0; i<numCourses; i++) {
        //     for(int j=0; adj[i] != null && j<adj[i].size(); j++)
        //         System.out.print(adj[i].get(j) + " ");
        //     System.out.println();
        // }
        // Step 2 - Use cycle detection algo on adj
            // if there is cycle return false otherwise true;
        
        // Kahn's algo for cycle detection
        int[]indegree = new int[numCourses];
        for(int i=0; i<numCourses; i++) {
            if(adj[i] != null) {
                for(int vi:adj[i]) {
                    indegree[vi]++;
                }
            }
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for(int i=0; i<numCourses; i++)
            if(indegree[i] == 0)
                queue.offer(i);
        int coursesComplete = 0; // count of topological sort
        while(!queue.isEmpty()) {
            int curn = queue.poll();
            if(adj[curn] != null) {
                for(int vi:adj[curn]) 
                    if(--indegree[vi] == 0)
                        queue.offer(vi);
            }
            coursesComplete++;
        }
        return coursesComplete == numCourses;
    }
}