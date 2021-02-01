package leetcode.solution.tree;

public class HasPathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;//当左右节点都为空且节点加和不为sum，在经过此处返回fasle
        if (root.left == null && root.right == null) return root.val == sum;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) return false;
        return dfs(root, sum) == 0;
    }

    private int dfs(TreeNode node, int sum) {
        if (node == null) return sum;
        sum -= node.val;
        if (dfs(node.left, sum) == 0) {
            return 0;
        } else if (dfs(node.right, sum) == 0) {
            return 0;
        } else {
            return sum += node.val;
        }

    }

    public static void main(String[] args) {
        HasPathSum h = new HasPathSum();
        TreeNode root = new TreeNode(1);
        TreeNode node = new TreeNode(2);
        root.left = node;
        System.out.print(h.hasPathSum(root, 1));
    }
}
