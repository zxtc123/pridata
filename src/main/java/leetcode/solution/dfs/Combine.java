package leetcode.solution.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaoxin
 * @Date: 2020/9/8 16:00
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class Combine {
    List<Integer> temp = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> combine(int n, int k) {
        dfs(1, n, k);
        return ans;
    }

    /**
     * 上面的代码中，dfs(cur,n) 参数表示当前位置是 cur，原序列总长度为 n。
     * 原序列的每个位置在答案序列种的状态有被选中和不被选中两种，我们用 temp 数组存放已经被选出的数字。
     * 在进入 dfs(cur,n) 之前 [1,cur−1] 位置的状态是确定的，而 [cur,n] 内位置的状态是不确定的，
     * dfs(cur,n) 需要确定 cur 位置的状态，然后求解子问题 dfs(cur+1,n)。
     * 对于 cur 位置，我们需要考虑 a[cur] 取或者不取，
     * 如果取，我们需要把 a[cur] 放入一个临时的答案数组中（即上面代码中的 temp），再执行 dfs(cur+1,n)，执行结束后需要对 temp 进行回溯；
     * 如果不取，则直接执行 dfs(cur+1,n)
     */
    public void dfs(int cur, int n, int k) {
        // 剪枝：temp 长度加上区间 [cur, n] 的长度小于 k，不可能构造出长度为 k 的 temp
        if (temp.size() + (n - cur + 1) < k) {
            return;
        }
        // 记录合法的答案
        if (temp.size() == k) {
            ans.add(new ArrayList<Integer>(temp));
            return;
        }
        // 考虑选择当前位置
        temp.add(cur);
        dfs(cur + 1, n, k);
        temp.remove(temp.size() - 1);//不考虑当前位置要移除这个数字，即进行回溯
        // 考虑不选择当前位置
        dfs(cur + 1, n, k);
    }

    public static void main(String[] args) {
        Combine c = new Combine();
        c.combine(4, 2);
    }

}
