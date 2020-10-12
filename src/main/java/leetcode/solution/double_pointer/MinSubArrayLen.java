package leetcode.solution.double_pointer;

import java.util.Arrays;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。
 * 如果不存在符合条件的连续子数组，返回 0。
 *
 *输入: s = 7, nums = [2,3,1,2,4,3]
输出: 2
解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 */
public class MinSubArrayLen {
	public int minSubArrayLen(int s, int[] nums){
		int min = 1;
		Arrays.sort(nums);
		int sum = 0;
		int length = nums.length;
		for(;min<length+1;min++){
			sum += nums[length-min];
			if(sum >= s)return min;
		}
		
		return 0;
	}
	
    public int minSubArrayLen2(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
	
	public static void main(String[] args) {
		MinSubArrayLen m = new MinSubArrayLen();
		int[] nums = {2,3,1,2,4,3};
		int[] nums2 = {12,28,83,4,25,26,25,2,25,25,25,12};
//		System.out.println(m.minSubArrayLen(7, nums));
		System.out.println(m.minSubArrayLen2(213, nums2));
	}
}
