package leetcode.solution.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
重复出现的子串要计算它们出现的次数。

示例 1 :
输入: "00110011"
输出: 6
解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。

请注意，一些重复出现的子串要计算它们出现的次数。
另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 *这些组合字串1 0数量要相同
 */
public class CountBinarySubstrings {
    public int countBinarySubstrings(String s) {
    	if(s==null || s=="")return 0;
    	char[] chars = s.toCharArray();
    	int len = chars.length;
    	int count = 0;
    	for(int i=0;i<len;i++){
    		char c = chars[i];
    		int j=i+1;
    		while(j<len && chars[j]==c)j++;
    		for(int k=j;k<len && k<(2*j-i);k++){
    			if((2*j-1)>len && chars[k]!=chars[j])continue;
    		}
    		count++;
    	}
    	return count;
    }
    
    /**
     * 我们可以将字符串 ss 按照 0 和 1 的连续段分组，存在 countscounts 数组中，
     * 例如 s = 00111011，可以得到这样的 countscounts 数组：counts={2,3,1,2}。
     * 这里  countscounts 数组中两个相邻的数一定代表的是两种不同的字符。
     * 假设 countscounts 数组中两个相邻的数字为 u 或者 v，它们对应着 u 个 0 和 v 个 1，或者 u 个 1 和 v 个 0。
     * 它们能组成的满足条件的子串数目为 min{u,v}，即一对相邻的数字对答案的贡献。
     * 我们只要遍历所有相邻的数对，求它们的贡献总和，即可得到答案。
     * @param s
     * @return
     */
    public int countBinarySubstrings2(String s) {
        List<Integer> counts = new ArrayList<>();
        int ptr = 0, n = s.length();
        while (ptr < n) {
            char c = s.charAt(ptr);
            int count = 0;
            while (ptr < n && s.charAt(ptr) == c) {
                ++ptr;
                ++count;
            }
            counts.add(count);
        }
        int ans = 0;
        for (int i = 1; i < counts.size(); ++i) {
            ans += Math.min(counts.get(i), counts.get(i - 1));
        }
        return ans;
    }
    
    public static void main(String[] args) {
    	CountBinarySubstrings c = new CountBinarySubstrings();
    	System.out.println(c.countBinarySubstrings("00110"));
	}
}
