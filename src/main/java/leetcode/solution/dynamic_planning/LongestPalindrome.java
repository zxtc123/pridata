package leetcode.solution.dynamic_planning;

/**
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * @author Administrator
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * <p>
 * 回文去掉2头后依旧是回文,可以使用动态规划
 */
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        int length = s.length();
        int r = length;
        String lp = "";
        String lgpd = "";
        for (int l = 0; l < length; l++) {
            lp = s.substring(l);
            while (r > l && !palindrome(lp)) {
                r--;
                lp = s.substring(l, r);
            }
            if (lp.length() > lgpd.length()) lgpd = lp;
        }
        return lgpd;
    }

    private boolean palindrome(String s) {
        char[] arr = s.toCharArray();
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            if (arr[i] != arr[length - i - 1]) return false;
        }
        return true;
    }

    //暴力解法，枚举所有的子串
    public String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组
        char[] charArray = s.toCharArray();

        // 枚举所有长度大于 1 的子串 charArray[i..j]
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && validPalindromic(charArray, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    private boolean validPalindromic(char[] charArray, int left, int right) {
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 动态规划
     * dp[i,j]表示子串s[i....j]是否为回文
     * 当子串头尾不等时，不是回文，当字串头尾相等时，
     * dp[i,j]=dp[i+1,j-1]由去掉头尾的子串决定
     *
     * @param s
     * @return
     */
    public String longestPalindrome3(String s) {
        // 特判
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // dp[i][j] 表示 s[i, j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();
        //单个字串为回文
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
