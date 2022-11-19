//{ Driver Code Starts
//Initial template for JAVA

import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            int n = Integer.parseInt(br.readLine().trim());
            int[][] matrix = new int[n][n];
            for(int i = 0; i < n; i++){
                String[] s = br.readLine().trim().split(" ");
                for(int j = 0; j < n; j++)
                    matrix[i][j]  =Integer.parseInt(s[j]);
            }
            Solution obj = new Solution();
            obj.shortest_distance(matrix);
            for(int i = 0; i < n; i++){
                for(int j  = 0; j < n; j++){
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}

// } Driver Code Ends


//User function template for JAVA

class Solution
{
    public void shortest_distance(int[][] matrix) {
        int n = matrix.length;
        change(matrix, -1, (int)1e9);
        for(int k=0; k<n; k++) {
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if(i == j || i == k || j == k)
                        continue;
                    else 
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
        change(matrix, (int)1e9, -1);
    }
    private void change(int[][]m, int from, int to) {
        for(int i=0; i<m.length; i++) {
            for(int j=0; j<m.length; j++) {
                if(m[i][j] == from) m[i][j] = to;
            }
        }
    }
}