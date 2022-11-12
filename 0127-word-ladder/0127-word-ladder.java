class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>();
        // instead of finding combination with all possible letters...
        // use only those characters that are present in wordList
        HashSet<Character> charactersInList = new HashSet<>();
        for(String s:wordList){ 
            set.add(s);
            for(char ch:s.toCharArray())
                charactersInList.add(ch);
        }
        
        // System.out.println(set);
        // System.out.println(charactersInList);
        ArrayDeque<Pair<String, Integer>> queue = new ArrayDeque<>();
        queue.offer(new Pair<>(beginWord, 1));
        while(!queue.isEmpty()) {
            Pair<String, Integer> pair = queue.poll();
            char[]str = pair.data1.toCharArray();
            int step = pair.data2;
            // System.out.println(new String(str) + step);
            if(pair.data1.equals(endWord)) return step;
            for(int i=0; i<str.length; i++) {
                for(char ch:charactersInList) {
                    
                    str[i] = ch;
                    String ns = new String(str);
                    if(set.contains(ns)) {
                        queue.offer(new Pair<>(ns, step + 1));
                        set.remove(ns);
                    }
                }
                str = pair.data1.toCharArray();
            }
        }
        return 0;
    }
}
class Pair <T,E> {
    T data1;
    E data2;
    Pair(T data1, E data2) {
        this.data1 = data1;
        this.data2 = data2;
    }
}