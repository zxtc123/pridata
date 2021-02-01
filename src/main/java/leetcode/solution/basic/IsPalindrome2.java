package leetcode.solution.basic;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 */
public class IsPalindrome2 {
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int length = s.length();
        if (length < 2) return true;
        int l = 0;
        int r = length - 1;
        while (l < r) {
            while (!Character.isLetterOrDigit(chars[l]) && l < r) l++;
            while (!Character.isLetterOrDigit(chars[r]) && l < r) r--;

            if (Character.toLowerCase(chars[l]) != Character.toLowerCase(chars[r]))
                return false;
            l++;
            r--;
        }
        return true;
    }

    public static void main(String[] args) {
        IsPalindrome2 i = new IsPalindrome2();
        String s = "A man, a plan, a canal: Panama";
        String s1 = "race a car";
        System.out.print(i.isPalindrome(s1));
    }
}
