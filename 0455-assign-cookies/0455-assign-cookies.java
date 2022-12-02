class Solution {
    public int findContentChildren(int[] g, int[] s) {
        int count = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        for(int i=0, j=0; j<g.length && i<s.length; i++) {
            if(g[j] <= s[i]){
                count++;
                j++;
            }
        }
        return count;
    }
}