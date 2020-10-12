package leetcode.solution.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
本题中，一棵高度平衡二叉树定义为：
一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * @author Administrator
 *
 */
public class IsBalanced {
    public boolean isBalanced(TreeNode root) {
    	if(root == null)return true;
    	Queue<TreeNode> qu = new LinkedList<TreeNode>();
    	int minHigh = 0;
    	int maxHigh = 0;
    	int high = 1;
    	
    	qu.offer(root);
    	while(!qu.isEmpty()){
    		TreeNode node = qu.poll();
    		if(node.left==null && node.right==null){
    			minHigh = high;
    		}else{
    			if(node.left==null && node.right!=null){
        			minHigh = high;
        			qu.offer(node.right);
        		}
        		if(node.left!=null && node.right==null){
        			minHigh = high;
        			qu.offer(node.left);
        		}
        		if(node.right!=null && node.left!=null){
        			qu.offer(node.right);
        			qu.offer(node.left);
        		}
        		high++;
    		}
    	}
    	maxHigh = high;
    	
    	return (maxHigh-minHigh)<2;
    }
    
    
    /**
     * 自顶向下递归
     * 判断每个节点高度，并判断节点是否平衡
     * 节点高度：
     * height(p) =    0   									p为空节点
     * 				Max(height(p.left),height(p.right))+1   p不为空
     * @param root
     * @return
     */
    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }
    
    /**
     * 自底向上递归
     * 先判断左右子树是否均衡，在判断当前根节点是否平衡
     * @param root
     * @return
     */
    public boolean isBalanced3(TreeNode root) {
        return height2(root) >= 0;
    }

    public int height2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {//只要高度差大于1，不平衡则返回-1
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }


}
