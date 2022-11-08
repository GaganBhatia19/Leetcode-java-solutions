class Solution {
    public int[] findOrder(int numCourses, int[][] prereq) {
    // Same as CourseSchedule 1 but here we have to return topologically sorted array
        ArrayList<Integer>[]adj = new ArrayList[numCourses];
        for(int[]pair:prereq) {
            if(adj[pair[1]] == null)
                adj[pair[1]] = new ArrayList<>();
            adj[pair[1]].add(pair[0]);
        }
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
        int[]topo = new int[numCourses];
        int tc = 0;
        while(!queue.isEmpty()) {
            int cur = queue.poll(); 
            if(adj[cur] != null) {
                for(int vi:adj[cur]) 
                    if(--indegree[vi] == 0)
                        queue.offer(vi);
                
            }
            topo[tc++] = cur;
        }
        return tc == numCourses ? topo : new int[]{};
    }
}