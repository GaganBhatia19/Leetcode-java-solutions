ways[0] = 0;
PriorityQueue<long[]> pq = new PriorityQueue<long[]>((a,b) -> (int)(a[0] - b[0]));
time[0] = 0;
pq.offer(new long[]{0, 0});
while(!pq.isEmpty()) {
long[]cur = pq.poll();
long t = cur[0], curNode = cur[1];
// System.out.println(t + " " + curNode);
if(adj.get(curNode) == null) continue;
for(long[]ni:adj.get(curNode)) {
long nt = (t + ni[1]) % mod;
if(nt < time[(int)ni[0]]) {
time[(int)ni[0]] = nt;
ways[(int)ni[0]] = 1;
pq.offer(new long[]{nt, ni[0]});
}
else if(nt  == time[(int)ni[0]])
ways[(int)ni[0]] += ways[(int)curNode];
}
}
return (int)ways[n-1];
}
}