package leetcode.solution.dynamic_planning;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * @author Administrator
 *只能向下或向右，即最终点由上面的点或左边的点移动而来
 *动态规划
 */
public class MinPathSum {
    public int minPathSum(int[][] grid) {
    	int m = grid.length;
    	int n = grid[0].length;
    	int[][]minPath=new int[m][n];
    	minPath[0][0]=grid[0][0];
    	int asum=minPath[0][0];
    	int bsum=minPath[0][0];
    	for(int a=1;a<n;a++){
    		asum+=grid[0][a];
    		minPath[0][a]=asum;
    	}
    	for(int b=1;b<m;b++){
    		bsum+=grid[b][0];
    		minPath[b][0]=bsum;
    	}
    	for(int i =0;i<m-1;i++){
    		for(int j=0;j<n-1;j++){
    			minPath[i+1][j+1]=grid[i+1][j+1]+Math.min(minPath[i+1][j], minPath[i][j+1]);
    		}
    	}
    	
    	return minPath[m-1][n-1];
    }
    
    public int minPathSum2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[rows - 1][columns - 1];
    }
    
    public static void main(String[] args) {
    	MinPathSum m = new MinPathSum();
		int[][]grid={{1,3,1},{1,5,1},{4,2,1}};
		System.out.println(m.minPathSum(grid));
	}
}
