package leetcode.solution.dynamic_planning;

/**
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。

输入："aaa"
输出：6
解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * @author Administrator
 *
 */
public class CountSubstrings {
	
	/**
	 * dp,令dp[j][i]表示子串s[j,i]是否为回文
	 * 当i=j 单个字符肯定为回文
	 * 当s[i]=s[j]且i-j=1 2个重复的字符也为回文
	 * 当s[i]=s[j]且dp[j+1][i-1]是回文，则dp[j][i]也是回文
	 * 其他情况都不是回文
	 * 
	 * 合并1，2，3种情况
	 * 当s[i]==s[j]时，dp[j+1][i-1]是回文串或i-j<2，则dp[j][i]也是回文串
	 * @param s
	 * @return
	 */
    public int countSubstrings2(String s) {
        int n = s.length();
        int res = 0;
        boolean[][] dp = new boolean[n][n];
        for(int i=0;i<n;++i){
            for(int j=0;j<=i;++j){
                if(s.charAt(i) == s.charAt(j)&&(i-j<2 || dp[j+1][i-1])){
                    dp[j][i] = true;
                    ++res;
                }
            } 
        }
        return res;
    }
	
    public int countSubstrings(String s) {
        if(s==null || s=="")return 0;

        int len = s.length();
        int head = 0;
        int tail = 0;
        int count = 0;
        for(;head<len;head++){
            for(tail = head; tail<len;tail++){
            	if(ispString(s,head,tail))count++;
            }
        }

        return count;
    }

    private boolean ispString(String s,int head,int tail){
        String subString = s.substring(head,tail+1);
        int len = subString.length();
        for(int i=len-1;i>=0;i--){
            if(subString.charAt(i)!=subString.charAt(len-i-1))return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
    	CountSubstrings c = new CountSubstrings();
    	System.out.print(c.countSubstrings2("abc"));
	}
}
