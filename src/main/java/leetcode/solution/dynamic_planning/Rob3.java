package leetcode.solution.dynamic_planning;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * @author Administrator
 */
public class Rob3 {
    public int rob(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int pre = 0;
        int cur = root.val;
        int temp;
        while (!queue.isEmpty()) {
            int sum = 0;
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
                sum += node.left.val;
            }
            if (node.right != null) {
                queue.offer(node.right);
                sum += node.right.val;
            }

            temp = cur;
            cur = Math.max(pre + sum, cur);
            pre = temp;
        }
        return cur;
    }

    /**
     * 递归
     * 比较 爷+孙 与 父 节点的大小
     *
     * @param root
     * @return
     */
    public int rob2(TreeNode root) {
        if (root == null) return 0;

        int money = root.val;
        if (root.left != null) {
            money += (rob2(root.left.left) + rob2(root.left.right));
        }

        if (root.right != null) {
            money += (rob2(root.right.left) + rob2(root.right.right));
        }

        return Math.max(money, rob2(root.left) + rob2(root.right));
    }

    /**
     * 解决重复计算，使用map记录数据
     *
     * @param root
     * @return
     */
    public int rob3(TreeNode root) {
        HashMap<TreeNode, Integer> memo = new HashMap<>();
        return robInternal(root, memo);
    }

    public int robInternal(TreeNode root, HashMap<TreeNode, Integer> memo) {
        if (root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);
        int money = root.val;

        if (root.left != null) {
            money += (robInternal(root.left.left, memo) + robInternal(root.left.right, memo));
        }
        if (root.right != null) {
            money += (robInternal(root.right.left, memo) + robInternal(root.right.right, memo));
        }
        int result = Math.max(money, robInternal(root.left, memo) + robInternal(root.right, memo));
        memo.put(root, result);
        return result;
    }

    /**
     * 我们使用一个大小为 2 的数组来表示 int[] res = new int[2] 0 代表不偷，1 代表偷
     * 任何一个节点能偷到的最大钱的状态可以定义为
     * 当前节点选择不偷：当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱
     * 当前节点选择偷：当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数
     *
     * @param root
     * @return
     */
    public int rob4(TreeNode root) {
        int[] result = robInternal(root);
        return Math.max(result[0], result[1]);
    }

    public int[] robInternal(TreeNode root) {
        if (root == null) return new int[2];
        int[] result = new int[2];

        int[] left = robInternal(root.left);
        int[] right = robInternal(root.right);

        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = left[0] + right[0] + root.val;

        return result;
    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

