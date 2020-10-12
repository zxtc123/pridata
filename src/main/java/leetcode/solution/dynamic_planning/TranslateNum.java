package leetcode.solution.dynamic_planning;

/**
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，
 * 25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。

示例 1:

输入: 12258
输出: 5
解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"

滚动数组动态规划，每次都使用固定的几个存储空间，来达到压缩，节省存储空间的作用。
我们常常需要用到的是连续的解，前面的解往往可以舍去

 * @author Administrator
 *
 */
public class TranslateNum {
    public static int translateNum(int num) {
        String src = String.valueOf(num);
        int p = 0, q = 0, r = 1;
        for (int i = 0; i < src.length(); ++i) {
            p = q; 
            q = r; 
            r = 0;
            r += q;
            if (i == 0) {
                continue;
            }
            String pre = src.substring(i - 1, i + 1);
            if (pre.compareTo("25") <= 0 && pre.compareTo("10") >= 0) {
                r += p;
            }
        }
        return r;
    }
    
    public static int translateNum2(int num) {
        if (num == 0) return 1;
        return f(num);
    }

    public static int f(int num) {
        if (num < 10) {
            return 1;
        }
        if (num % 100 < 26 && num % 100 > 9) {
            return f(num / 10) + f(num / 100);
        } else {
            return f(num / 10);
        }
    }
    
    public static int translateNum3(int num) {
	if (num < 10) {
		//个位数,只可能有一种翻译法
		return 1;
	}
	char[] nums = String.valueOf(num).toCharArray();
	//dp[i]代表前i-1个数总共有多少种翻译方法
	int[] dp = new int[nums.length];
	dp[0] = 1;
	int n = (nums[0] - '0') * 10 + (nums[1] - '0');
	//计算初始值,第二位数和第一位数组成的数字介于(9,26)之间,有两种翻译
	//若组成的数是0~9或大于25则只能有一种翻译
	dp[1] = n > 9 && n < 26 ? 2 : 1;

	for (int i = 2; i < nums.length; i++) {
		//计算当前数和前一个数组成的数值大小,如1225的下标3的数和它前一个数组成的值为25
		n = (nums[i - 1] - '0') * 10 + (nums[i] - '0');
		if (n > 9 && n < 26) {
			//组成数值处于(9,26)范围内,则可翻译的方法数为前两个数的翻译总和
			dp[i] = dp[i - 1] + dp[i - 2];
		} else {
			//组成数值不在(9,26)范围内，则只能算一种翻译,和前一个数能翻译的方法数一样
			dp[i] = dp[i - 1];
		}
	}
	return dp[nums.length - 1];
    }
    
    public static int translateNum4(int num) {
    	if (num < 10) {
    		//个位数,只可能有一种翻译法
    		return 1;
    	}
    	char[] nums = String.valueOf(num).toCharArray();
    	//存储上上个数的翻译数
    	int prepre = 1;
    	int n = (nums[0] - '0') * 10 + (nums[1] - '0');
    	//存储上个数的翻译数,第二位数和第一位数组成的数字介于(9,26)之间,有两种翻译
    	//若组成的数是0~9或大于25则只能有一种翻译
    	int pre = n > 9 && n < 26 ? 2 : 1;

    	for (int i = 2; i < nums.length; i++) {
    		//计算当前数和前一个数组成的数值大小,如1225的下标3的数和它前一个数组成的值为25
    		n = (nums[i - 1] - '0') * 10 + (nums[i] - '0');
    		if (n > 9 && n < 26) {
    			//组成数值处于(9,26)范围内,则可翻译的方法数为前两个数的翻译总和
    			int tmppre = pre;
    			pre = pre + prepre;
    			prepre = tmppre;
    		}
    		//组成数值不在(9,26)范围内，则只能算一种翻译,和前一个数能翻译的方法数一样
    		//不需要更新上一个数和上上个数

    	}
    	return pre;
        }

    
    public static void main(String[] args) {
    	int i = translateNum4(12208);
		System.out.print(i);
	}
}
