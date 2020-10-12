package leetcode.solution.dynamic_planning;
/**
 * 给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，
 * 然后玩家 1 拿，…… 。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。
 * 最终获得分数总和最多的玩家获胜。
 * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
 * 示例 1：
输入：[1, 5, 2]
输出：False
解释：一开始，玩家1可以从1和2中进行选择。
如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。
所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
因此，玩家 1 永远不会成为赢家，返回 False 。
 */
public class PredictTheWinner {
	/**
	 * 定义二维数组 dp，其行数和列数都等于数组的长度，dp[i][j] 表示当数组剩下的部分为下标 i 到下标 j 时，
	 * 当前玩家与另一个玩家的分数之差的最大值，注意当前玩家不一定是先手。
	 * 只有当 i≤j 时，数组剩下的部分才有意义，因此当i>j 时，dp[i][j]=0。
	 * 当 i=j 时，只剩一个数字，当前玩家只能拿取这个数字，因此对于所有 0≤i<nums.length，都有 dp[i][i]=nums[i]。
	 * 当 i<j 时，当前玩家可以选择 nums[i] 或nums[j]，然后轮到另一个玩家在数组剩下的部分选取数字。
	 * 在两种方案中，当前玩家会选择最优的方案，使得自己的分数最大化。因此可以得到如下状态转移方程：
	 * dp[i][j]=max(nums[i]−dp[i+1][j],nums[j]−dp[i][j−1])
	 * 最后判断 dp[0][nums.length−1] 的值，如果大于或等于 0，则先手得分大于或等于后手得分，
	 * 因此先手成为赢家，否则后手成为赢家。
	 * @param nums
	 * @return
	 */
    public boolean PredictTheWinner2(int[] nums) {
        int length = nums.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][length - 1] >= 0;
    }

	
	
    public boolean PredictTheWinner(int[] nums) {
    	int number = 0;
    	int sum1 = 0;
    	int sum2 = 0;
    	int low = 0;
    	int high = nums.length-1;
    	int temp;
    	while(number < nums.length){
    		if(nums[high] > nums[low]){
    			temp = nums[high];
    			high--;
    		}else{
    			temp = nums[low];
    			low++;
    		}
    		
    		if(number%2 == 0){
    			sum1 += temp;
    		}else{
    			sum2 += temp;
    		}
    		number++;
    	}
    	
    	return sum1 >= sum2;
    }
}
