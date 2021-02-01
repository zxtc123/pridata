package leetcode.solution.tree;


/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * @author Administrator
 */
public class SortedListToBST {
    /**
     * 为得到平衡的二叉搜索树，需要树2边节点数尽量相同
     * 由于是有序链表，取链表的中位数做根节点，左右子树高度均衡，之后不断对左右子树进行相同操作即可
     * 对于链表采用左闭右开，链表的next容易取到，它的pre不容易取到，所以要确定头部，尾部没有的部分为null
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        return buildTree(head, null);
    }

    public TreeNode buildTree(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }
        ListNode mid = getMedian(left, right);
        TreeNode root = new TreeNode(mid.val);
        root.left = buildTree(left, mid);
        root.right = buildTree(mid.next, right);
        return root;
    }

    /**
     * 链表取中位数的方法可以采用：快慢指针
     * 快指针移动2次
     * 慢指针移动1次
     *
     * @param left
     * @param right
     * @return
     */
    public ListNode getMedian(ListNode left, ListNode right) {
        ListNode fast = left;
        ListNode slow = left;
        while (fast != right && fast.next != right) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    /**
     * 中序遍历
     * 先遍历左子树，在根节点，在右子树
     */
    ListNode ptr;

    public TreeNode sortedListToBST2(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);

        ptr = head;
        return buildTree(0, length(head) - 1);
    }

    TreeNode buildTree(int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left + 1) / 2;
        TreeNode root = new TreeNode();
        TreeNode leftTree = buildTree(left, mid - 1);
        root.val = ptr.val;
        ptr = ptr.next;
        root.left = leftTree;
        root.right = buildTree(mid + 1, right);
        return root;
    }

    int length(ListNode node) {
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
