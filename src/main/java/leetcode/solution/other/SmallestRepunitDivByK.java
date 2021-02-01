package leetcode.solution.other;

/**
 * 给定正整数 K，你需要找出可以被 K 整除的、仅包含数字 1 的最小正整数 N。
 * 返回 N 的长度。如果不存在这样的 N，就返回 -1。
 * <p>
 * 输入：3
 * 输出：3
 * 解释：最小的答案是 N = 111，其长度为 3。
 * <p>
 * 1 <= K <= 10^5
 * <p>
 * while (x % K != 0) x = x * 10 + 1;
 * 用x不断去尝试整除K，不满足就扩大继续整除，存在超出范围的问题
 * <p>
 * 对于任意整数x > 0x>0，存在非负整数pp和qq，使得x = pK + qx=pK+q
 */
public class SmallestRepunitDivByK {
    public int smallestRepunitDivByK(int k) {
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }
        int temp = 1;
        int len = 1;
        while (temp % k != 0) {
            temp = temp % k;
            temp = temp * 10 + 1;
            len += 1;
        }
        return len;

    }
}
