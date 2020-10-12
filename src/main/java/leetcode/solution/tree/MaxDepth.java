package leetcode.solution.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * @author Administrator
 *
 */
public class MaxDepth {
	public int maxDepth(TreeNode root) {
        if(root==null)return 0;
        int depth=1;
        Stack<Integer> stack = new Stack();
        depth(root,stack,depth);
        return stack.pop();
    }

    private void depth (TreeNode node, Stack stack,int depth){
        if(node.left!=null){
            stack.push(depth+1);
            depth(node.left, stack, ++depth);
        } 
        if(node.right!=null){
            stack.push(depth+1);
            depth(node.right, stack, ++depth);
        } 
    }
    
    /**
     * 递归，比较左子树和右子树的深度，选择大的加上自身的深度1为最大深度
     * 中止条件，没有子节点
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        return root==null? 0 : Math.max(maxDepth(root.left), maxDepth(root.right))+1;
    }
    
    /**
     * BFS，层层累加树的高度
     * 在队首取出该层的节点
     * 在队尾放入取出节点的相关节点
     * 终止条件：队列取出所有节点
     * @param root
     * @return
     */
    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();  //每一层的个数
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }

}
