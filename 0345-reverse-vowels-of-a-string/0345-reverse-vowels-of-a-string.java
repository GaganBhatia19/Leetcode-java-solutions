class Solution {
    public String reverseVowels(String s) {
        Function<Character, Boolean> isVowel = c -> 
            c == 'a' ||c == 'e' ||c == 'i' ||c == 'o' ||c == 'u'||c == 'A' ||c == 'E' ||c == 'I' ||c == 'O' ||c == 'U';
        char[]carr = s.toCharArray();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for(int i=0; i<s.length(); i++) 
            if(isVowel.apply(carr[i])) stack.push(carr[i]);
        
        for(int i=0; i<s.length(); i++) 
            if(isVowel.apply(carr[i])) carr[i] = stack.pop();
        
        return new String(carr);
    }
}