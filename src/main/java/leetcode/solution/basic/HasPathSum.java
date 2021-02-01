package leetcode.solution.basic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 */
public class HasPathSum {
    //DFS 深度优先搜索，用于搜索或遍历树和图的算法，搜索特定解为止
    //一般用栈存储
    //判断边界，到达树的叶子节点
    //左右节点为扩展出的节点，继续DFS处理
    public boolean dfsPath(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        return dfsPath(root.left, sum - root.val) || dfsPath(root.right, sum - root.val);
    }

    //BFS 广度优先搜索，尽可能搜索所有节点并标记，没标记的节点继续扩展搜索
    //一般用队列存放
    //取队头元素判断，若队头元素可扩展，将扩展元素放入队尾
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queNode = new LinkedList<TreeNode>();
        Queue<Integer> queVal = new LinkedList<Integer>();
        queNode.offer(root);
        queVal.offer(root.val);
        while (!queNode.isEmpty()) {
            TreeNode now = queNode.poll();
            int temp = queVal.poll();
            if (now.left == null && now.right == null) {
                if (temp == sum) {
                    return true;
                }
                continue;
            }
            if (now.left != null) {
                queNode.offer(now.left);
                queVal.offer(now.left.val + temp);
            }
            if (now.right != null) {
                queNode.offer(now.right);
                queVal.offer(now.right.val + temp);
            }
        }
        return false;
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
