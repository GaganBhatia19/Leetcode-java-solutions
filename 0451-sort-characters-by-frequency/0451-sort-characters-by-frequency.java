class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new TreeMap<>();
        for(char c : s.toCharArray()) {
            if(map.containsKey(c)) {
                int freq = map.get(c);
                map.put(c, freq + 1);
            }
            else map.put(c, 1);
        }
        Queue<int[]> pq = new PriorityQueue<>((a,b) -> b[1] - a[1]);
        for(char c : map.keySet()) 
            pq.offer(new int[]{(int)c, map.get(c)});
        
        StringBuilder res = new StringBuilder();
        while(!pq.isEmpty()) {
            int[]el = pq.poll();
            for(int i=0; i<el[1]; i++) {
                res.append((char)el[0]);
            }
        }
        return res.toString();
    }
}