package leetcode.solution.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * <p>
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();

        // 根据官方对 Stack 的使用建议，这里将 Deque 对象当做 stack 使用
        // 注意只使用关于栈的接口
        Deque<Integer> path = new ArrayDeque<>();
        dfs(k, n, 1, path, res);
        return res;
    }

    /**
     * @param k       剩下要找 k 个数
     * @param residue 剩余多少
     * @param start   下一轮搜索的起始元素是多少
     * @param path    深度优先遍历的路径参数（状态变量）
     * @param res     保存结果集的列表
     */
    private void dfs(int k, int residue, int start, Deque<Integer> path, List<List<Integer>> res) {
        // 这一步判断可以放到上一层，减少递归深度
        if (residue < 0) {
            return;
        }

        if (k == 0) {
            if (residue == 0) {
                res.add(new ArrayList<>(path));
                return;
            }
            return;
        }

        for (int i = start; i <= 9; i++) {
            path.addLast(i);
            dfs(k - 1, residue - i, i + 1, path, res);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        CombinationSum3 c = new CombinationSum3();
        System.out.print(c.combinationSum3(3, 9));
    }
}
