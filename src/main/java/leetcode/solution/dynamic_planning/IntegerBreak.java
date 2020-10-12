package leetcode.solution.dynamic_planning;

/**
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * @author Administrator
 *对于的正整数 n，当 n≥2 时，可以拆分成至少两个正整数的和。令 k是拆分出的第一个正整数，则剩下的部分是 n-k，n-k可以不继续拆分，
 *或者继续拆分成至少两个正整数的和。由于每个正整数对应的最大乘积取决于比它小的正整数对应的最大乘积，因此可以使用动态规划求解。

创建数组 dp，其中dp[i] 表示将正整数 i 拆分成至少两个正整数的和之后，这些正整数的最大乘积。特别地，0 不是正整数，1 是最小的正整数，
0和 1都不能拆分，因此 {dp}[0]={dp}[1]=0。
当 i≥2 时，假设对正整数 i 拆分出的第一个正整数是 j（1≤j<i），则有以下两种方案：

将 i 拆分成 j 和 i-j的和，且 i−j 不再拆分成多个正整数，此时的乘积是 j×(i−j)；
将 i 拆分成 j 和i−j 的和，且i−j 继续拆分成多个正整数，此时的乘积是 j×dp[i−j]。

因此，当 j 固定时，有dp[i]=max(j*(i-j),j*dp(i-j)),需要遍历j的值获得最大的值

最终得到 dp[n] 的值即为将正整数 n 拆分成至少两个正整数的和之后，这些正整数的最大乘积。
 */
public class IntegerBreak {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int curMax = 0;
            for (int j = 1; j < i; j++) {
                curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = curMax;
        }
        return dp[n];
    }
}
