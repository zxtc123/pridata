package leetcode.solution.dynamic_planning;

import java.util.Arrays;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，
 * 这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * @author Administrator
 *
 */
public class Rob2 {
	/**
	 * 围成圈，计算不包含第一间房间和不包含最后一间房间的最大值
	 * @param nums
	 * @return
	 */
    public int rob(int[] nums) {
        int len = nums.length;
        if(nums==null || len==0)return 0;
        if(len==1)return nums[0];
        int[] dp = new int[len];
        return Math.max(myRob(Arrays.copyOfRange(nums, 0, len-1)), 
        		myRob(Arrays.copyOfRange(nums, 1, len)));
    }
    
    private int myRob(int[] nums){
        int len = nums.length;
        if(nums==null || len==0)return 0;
        if(len==1)return nums[0];
        int[] dp = new int[len];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0], nums[1]);
        if(len > 2){
        	for(int i=2;i<len;i++){
        		dp[i]=Math.max(dp[i-1], dp[i-2]+nums[i]);
        	}	
        }
        return dp[len-1];
    }
}
