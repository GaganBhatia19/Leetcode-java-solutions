class Solution {
    public int removeElement(int[] nums, int val) {
        if(nums.length == 0) return 0;
        int head = 0, last = nums.length - 1;
        while(head < last) {
            if(nums[last] == val) {
                last--;
            } else {
                if(nums[head] == val) {
                    //swap
                    int t = nums[head];
                    nums[head] = nums[last];
                    nums[last] = t;
                    last--;
                }
                head++;
            }
        }
        if(val == nums[last]) return last;
        return last + 1;
    }
}