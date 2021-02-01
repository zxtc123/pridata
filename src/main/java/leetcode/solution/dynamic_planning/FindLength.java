package leetcode.solution.dynamic_planning;

/**
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * <p>
 * 输入:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出: 3
 * 解释:
 * 长度最长的公共子数组是 [3, 2, 1]。
 */
public class FindLength {
    //暴力解法，会超时
    public int findLength(int[] A, int[] B) {
        int i = 0;
        int j = 0;
        int maxlength = 0;
        while (i < A.length && j < B.length) {
            j = 0;
            while (i < A.length && j < B.length - 1 && A[i] != B[j]) {
                j++;
            }
            int length = 0;
            int a = i;
            int b = j;
            while (a < A.length && b < B.length && A[a] == B[b]) {
                a++;
                b++;
                length++;
            }
            maxlength = Math.max(maxlength, length);
            i++;
        }
        return maxlength;
    }

    //动态规划，最后一个字符的最长前缀由前一个字符决定
    //如果前面字符不等，最长前缀为0，否则最长前缀为前一个字符的最长前缀+1
    //dp[i][j]=dp[i-1][j-1]+ 1
    public int findLength2(int[] A, int[] B) {
        int max = 0;
        int[][] dp = new int[A.length + 1][B.length + 1];
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = 0;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    /**
     * 滑动窗口，只滑动A或B，计算滑动后AB重复的部分
     *
     * @param A
     * @param B
     * @return
     */
    public int findLength3(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int len = Math.min(m, n - i);
            int maxlen = maxLength(A, B, i, 0, len);
            ret = Math.max(ret, maxlen);
        }
        for (int i = 0; i < m; i++) {
            int len = Math.min(n, m - i);
            int maxlen = maxLength(A, B, 0, i, len);
            ret = Math.max(ret, maxlen);
        }
        return ret;
    }

    public int maxLength(int[] A, int[] B, int addA, int addB, int len) {
        int ret = 0, k = 0;
        for (int i = 0; i < len; i++) {
            if (A[addA + i] == B[addB + i]) {
                k++;
            } else {
                k = 0;
            }
            ret = Math.max(ret, k);
        }
        return ret;
    }


    public static void main(String[] args) {
        FindLength f = new FindLength();
        int[] A = {1, 2, 3, 2, 1};
        int[] B = {3, 2, 1, 4, 7};

        int[] A1 = {0, 0, 0, 0, 0, 0, 1, 0, 0, 0};
        int[] B1 = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0};
        System.out.print(f.findLength2(A, B));
    }
}
