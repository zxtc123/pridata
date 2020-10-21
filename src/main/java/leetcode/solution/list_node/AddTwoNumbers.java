package leetcode.solution.list_node;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，
 * 并且它们的每个节点只能存储 一位 数字。如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 */
public class AddTwoNumbers {
	//循环1边链表，万一循环的链表为空怎么办？
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	ListNode twoNums = new ListNode(0);
    	ListNode next = twoNums;
    	int carry = 0;
    	int sum=0;
    	while(l1!=null || l2!=null){
    		int i1 = (l1!=null)?l1.val:0;
    		int i2 = (l2!=null)?l2.val:0;
    		
    		sum=i1+i2+carry;
    		carry = sum/10;
    		sum = sum%10;
		
		    next.next = new ListNode(sum);
		    next = next.next;
		    if(l1!=null)l1 = l1.next;
		    if(l2!=null)l2 = l2.next;
    	}
    	if(carry!=0){//结尾超出情况
    		next.next = new ListNode(1);
    	}
    	return twoNums.next;
    }
    
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}

class ListNode{
	int val;
	ListNode next;
	ListNode(int x){val=x;}
}