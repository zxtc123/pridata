package leetcode.solution.dynamic_planning;

/**
 * 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i
 * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
 * 返回一对观光景点能取得的最高分。
 * 
 * 输入：[8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 * 
 * 2 <= A.length <= 50000
 * 1 <= A[i] <= 1000
 *
 */
public class MaxScoreSightseeingPair {
	//A的长度较大3/4千时，运算很耗时
	public int maxScoreSightseeingPair(int[] A) {
		int maxScore=0;
		int length = A.length;
		int score=0;
		for(int i=0;i<length-1;i++){
			for(int j=i+1;j<length;j++){
				score=A[i]+A[j]+i-j;
				if(score>maxScore)maxScore=score;
			}
		}
		
		
		return maxScore;
	}
	
	//动态规划：A[i] + A[j] + i - j分为 A[i]+i  A[j]-j
	//当遍历j的同时，在j一定的情况下 A[i]+i越大总值越大
	   public int maxScoreSightseeingPair2(int[] A) {
	        int ans = 0, mx = A[0] + 0;
	        for (int j = 1; j < A.length; ++j) {
	            ans = Math.max(ans, mx + A[j] - j);
	            // 边遍历边维护
	            mx = Math.max(mx, A[j] + j);
	        }
	        return ans;
	    }
	
	public static void main(String[] args) {
		MaxScoreSightseeingPair m = new MaxScoreSightseeingPair();
		int[] A = {8,1,5,2,6};
		System.out.print(m.maxScoreSightseeingPair(A));
	}
}
