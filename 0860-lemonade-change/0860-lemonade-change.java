class Solution {
    public boolean lemonadeChange(int[] bills) {
        int n = bills.length;
        int c5 = 0, c10 = 0, c20 = 0;
        for(int i=0; i<n; i++) {
            if(bills[i] == 5) c5++;
            else if(bills[i] == 10) {
                c10++;
                if(c5 > 0) c5--;
                else return false;
            }
            else if(bills[i] == 20) {
                c20++;
                if(c5 > 0  && c10 > 0) {
                    c5--;
                    c10--;
                }
                else if(c5 >= 3) {
                    c5 -= 3;
                }
                else return false;
            }
        }
        return true;
    }
}