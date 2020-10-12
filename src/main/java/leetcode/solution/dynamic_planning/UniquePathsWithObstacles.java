package leetcode.solution.dynamic_planning;

/**
 * 一个机器人位于一个 m x n 网格的左上角，向右下方移动，只能向右和向下移动
 * 考虑障碍物情况，obstacleGrid==1表示有障碍物
 * 有多少种移动方案
 * 
 * DP到(i,j)的移动方案取决于(i-1,j)和(i,j-1)
 * 当(i,j)为1，dp(i,j)为0
 * 否则dp(i,j)=dp(i-1,j)+dp(i,j-1)
 * @author Administrator
 *
 */
public class UniquePathsWithObstacles {
	
	//用二维数组储存所有点的路径情况
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    	if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
    	int m = obstacleGrid.length;
    	int n = obstacleGrid[0].length;
    	int[][]dp=new int[m][n];
    	//当不存在障碍时，初始化dp(i,0)和dp(0,j)为1，只有1种路径
    	for(int i=0;i<m && obstacleGrid[i][0]==0;i++)
    		dp[i][0]=1;
    	for(int j=0;j<n && obstacleGrid[0][j]==0;j++)
    		dp[0][j]=1;
    	for(int k=1;k<m;k++){
    		for(int l=1;l<n;l++){
    			if(obstacleGrid[k][l]==0){
    				dp[k][l]=dp[k-1][l]+dp[k][l-1];
    			}
    		}
    	}
    	return dp[m-1][n-1];
    }
    
    //可以压缩，使用一维数组储存结果，只储存最下方i的结果
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
    	if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
    	int m = obstacleGrid.length;
    	int n = obstacleGrid[0].length;
    	int[] dp = new int[n];
    	dp[0] = obstacleGrid[0][0]==0? 1:0;
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && obstacleGrid[i-1][j] == 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n-1];
    }
}
