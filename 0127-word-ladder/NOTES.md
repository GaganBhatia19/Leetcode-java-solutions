```
// wrong approch 9 / 50 test cases passed.
class Solution {
public int ladderLength(String beginWord, String endWord, List<String> wordList) {
// if endWord doesn't exist in list the simply return 0
boolean terminate = true;
for(String str:wordList)
if(str.equals(endWord))
terminate = false;
if(terminate) return 0;
// Pair will store String , index and level or step
Pair<String, Pair<Integer, Integer>> p = new Pair<>(beginWord, new Pair<>(0, 1));
ArrayDeque<Pair<String, Pair<Integer, Integer>>> queue = new ArrayDeque<>();
queue.offer(p);
HashMap<String, Boolean> visited = new HashMap<>(); // visited to keep track
for(String s:wordList) visited.put(s, false);
visited.put(beginWord, true);
int lastStep = 0;
while(!queue.isEmpty()) {
Pair<String, Pair<Integer, Integer>> pair = queue.poll();
String str = pair.data1;
int idx = pair.data2.data1;
int step = pair.data2.data2;
// System.out.println(str + idx + step);
// calculate distance and whose distance is 1 add string in the queue with step increase
for(int i=idx; i<wordList.size(); i++) {
// check if distance is 1 and not visited yet
if(distance(str, wordList.get(i)) <= 1 && !visited.get(wordList.get(i))) {
visited.put(wordList.get(i), true); // mark visited
queue.offer(new Pair<>(wordList.get(i), new Pair<>(i+1, step+1)));
}
}
lastStep = step;
}
return lastStep;
}
private int distance(String s1, String s2) {
int dist = 0;
for(int i=0; i<s1.length(); i++) {
if(s1.charAt(i) != s2.charAt(i)) dist++;