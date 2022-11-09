//{ Driver Code Starts
/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
import java.math.*;

class GFG {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = Integer.parseInt(sc.next());
		while(t-- > 0)
		{
		    int n = Integer.parseInt(sc.next());
		    int k = Integer.parseInt(sc.next());
		    
		    String[] words = new String[n];
		    
		    for(int i=0;i<n;i++)
		    {
		        words[i] = sc.next();
		    }
		    
		    Solution ob = new Solution();
		  //  System.out.println(T.findOrder(words,k));
		    String order = ob.findOrder(words,n,k);
		    if(order.length() == 0){
		        System.out.println(0);
		        continue;
		    }
		    String temp[] = new String[n];
		    for(int i=0;i<n;i++)
		        temp[i] = words[i];
		    
		    Arrays.sort(temp, new Comparator<String>(){
		    
		      @Override
                public int compare(String a, String b) {
                    int index1 = 0;
                    int index2 = 0;
                    for(int i = 0; i < Math.min(a.length(), b.length()) 
                                        && index1 == index2; i++) {
                        index1 = order.indexOf(a.charAt(i));
                        index2 = order.indexOf(b.charAt(i));
                    }
                
                    if(index1 == index2 && a.length() != b.length()) 
                    {
                        if(a.length() < b.length())
                            return -1;
                        else
                            return 1;
                    }
                
                    if(index1 < index2)
                        return -1;
                    else
                        return 1;
                        
                }
		    });
		    
		    int flag = 1;
		    for(int i=0;i<n;i++)
		    {
		        if(!words[i].equals(temp[i]))
	            {
	                flag = 0;
	                break;
	            }
		    }
		    
		    System.out.println(flag);
		}
	}
	
}

// } Driver Code Ends


//User function Template for Java

class Solution
{
    public String findOrder(String [] dict, int N, int K)
    {
        // create adj list for character 
        Map<Character, List<Character>> adj = new HashMap<>();
        for(int i=1; i<N; i++) {
            int indx = compare(dict[i-1], dict[i]);
            if(indx == -1) continue;
            List<Character> charList;
            if(adj.containsKey(dict[i-1].charAt(indx)))
                charList = adj.get(dict[i-1].charAt(indx));
            else charList = new ArrayList<>();
            charList.add(dict[i].charAt(indx));
            adj.put(dict[i-1].charAt(indx), charList);
        }
        // applying topological/ Kahn's algo
        int[]indegree = new int[K];
        for(char ch = 'a'; ch < 'a' + K; ch++) {
            if(adj.get(ch) != null) 
                for(char cvi : adj.get(ch)) 
                    indegree[(int)(cvi - 'a')]++;
            
        }
        ArrayDeque<Integer>queue = new ArrayDeque<>();
        for(int i=0; i<K; i++)
            if(indegree[i] == 0)
                queue.offer(i);
        String finalTopoSort = "";
        while(!queue.isEmpty()) {
            int curn = queue.poll();
            if(adj.get((char)('a' + curn)) != null) {
                for(char cvi : adj.get((char)('a' + curn))) {
                    if(--indegree[(int)(cvi - 'a')] == 0)
                        queue.offer((int)(cvi - 'a'));
                }
            }
            finalTopoSort += (char)('a' + curn);
        }
        // System.out.println(finalTopoSort);
        return finalTopoSort;
    }
    private int compare(String s1, String s2) {
        for(int i=0; i<s1.length(); i++)
            if(s1.charAt(i) != s2.charAt(i))
                return i;
        return -1;
    }
}