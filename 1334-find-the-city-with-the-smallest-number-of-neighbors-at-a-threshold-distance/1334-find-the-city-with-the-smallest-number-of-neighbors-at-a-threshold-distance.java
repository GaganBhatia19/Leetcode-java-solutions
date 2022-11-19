class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][]matrix = new int[n][n];
        
        for(int[]row:matrix)
            Arrays.fill(row, (int)1e9);
        
        for(int[]ei:edges) {
            int u = ei[0], v = ei[1], wt = ei[2];
            matrix[u][v] = wt;
            matrix[v][u] = wt;
        }
        // floyd-warshall
        for(int via = 0; via < n; via++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if (i == j) matrix[i][j] = 0;
                    else if (i == via || j == via) continue;
                    else {
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][via] + matrix[via][j]);
                    }
                }
            }
        }
        int[]cityCount = new int[n];
        for(int i=0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i != j && matrix[i][j] <= distanceThreshold)
                    cityCount[i]++;
            }
        }
        int minCount = Integer.MAX_VALUE, greaterCityNumber = 0;
        for(int i=n-1; i >= 0; i--) { // taking from last as "lasts" will represent city with greater number
            if(minCount > cityCount[i])  {
                minCount = cityCount[i];
                greaterCityNumber = i;
            }
        }
        return greaterCityNumber;
    }
}