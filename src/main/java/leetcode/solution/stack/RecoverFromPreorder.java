package leetcode.solution.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。
 * （如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 * 给出遍历输出 S，还原树并返回其根节点 root。
 * 
 * 读取数字获得当前节点T和上一个节点S
 * 有两种情况：

T 是 S 的左子节点；

T 是根节点到 S 这一条路径上（不包括 S）某一个节点的右子节点。
为什么不包括 S？因为题目中规定了如果节点只有一个子节点，那么保证该子节点为左子节点。
在 T 出现之前，S 仍然还是一个叶节点，没有左子节点，因此 T 如果是 S 的子节点，一定是优先变成 S 的左子节点。

如果当前节点深度比上个节点大1，则当前节点为上个节点的左节点
否则，借助栈，将根节点到S的节点存入栈中，不断从栈中获取数字，栈的大小即深度，获取到T的父节点

 */
public class RecoverFromPreorder {
	    public TreeNode recoverFromPreorder(String S) {
	        Deque<TreeNode> path = new LinkedList<TreeNode>();
	        int pos = 0;
	        while (pos < S.length()) {
	            int level = 0;
	            while (S.charAt(pos) == '-') {
	                ++level;
	                ++pos;
	            }
	            int value = 0;
	            while (pos < S.length() && Character.isDigit(S.charAt(pos))) {
	                value = value * 10 + (S.charAt(pos) - '0');
	                ++pos;
	            }
	            TreeNode node = new TreeNode(value);
	            if (level == path.size()) {
	                if (!path.isEmpty()) {
	                    path.peek().left = node;
	                }
	            }
	            else {
	                while (level != path.size()) {
	                    path.pop();
	                }
	                path.peek().right = node;
	            }
	            path.push(node);
	        }
	        while (path.size() > 1) {
	            path.pop();
	        }
	        return path.peek();
	    }
}

class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x){val=x;}
}
