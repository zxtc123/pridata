package leetcode.solution.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * @author Administrator
 */
public class IsSymmetric {
    //递归比较对称的节点
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (right == null && left == null)
            return true;
        else if (left == null || right == null)
            return false;
        else
            return left.val == right.val
                    && isSymmetric(left.left, right.right)
                    && isSymmetric(left.right, right.left);
    }

    /**
     * 将左右节点放入队列中比较
     * 比较完后，2节点的左右节点相反放入队列，继续比较
     *
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode u, TreeNode v) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(u);
        q.offer(v);
        while (!q.isEmpty()) {
            u = q.poll();
            v = q.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }

            q.offer(u.left);
            q.offer(v.right);

            q.offer(u.right);
            q.offer(v.left);
        }
        return true;
    }
}
