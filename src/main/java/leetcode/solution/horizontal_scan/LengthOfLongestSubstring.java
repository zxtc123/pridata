package leetcode.solution.horizontal_scan;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * @author Administrator
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        String ch = "";
        int max = 1;
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            if (ch.contains(s.substring(i, i + 1))) {
                max = Math.max(max, length);
                length = 1;
                ch = s.substring(i, i + 1);
            } else {
                length++;
                ch = ch + s.substring(i, i + 1);
            }

        }
        return max;
    }

    /**
     * 滑动窗口，不断递增子串的起始位置，得出每个起始位置的最长长度
     * 用set来存放不重复的子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring l = new LengthOfLongestSubstring();
        String s = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        String s4 = " ";
        System.out.print(l.lengthOfLongestSubstring(s4));
    }
}
