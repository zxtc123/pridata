package leetcode.solution.stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：
左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。
 * @author Administrator
 *
 */
public class IsValid {
    public boolean isValid(String s) {
    	if(s==null && s=="")return true;
    	int len = s.length();
    	if(len%2==1)return false;//奇数不可能闭合，直接返回false
    	Stack stack = new Stack<>();
    	for(int i=0;i<len;i++){
    		char c = s.charAt(i);
    		if(stack.isEmpty()){
    			stack.push(c);
    		}else{
    			char prec = (char)stack.peek();
    			if((prec=='{' && c=='}') 
    					|| (prec=='[' && c==']')
    					|| (prec=='(' && c==')')){
    				stack.pop();
    			}else{
    				stack.push(c);
    			}
    		}
    	}
    	return stack.isEmpty();
    }
    
    //用map存放右边的终止括号
    //当出现终止括号且前面的括号与它不匹配
    //表示不是有效字符串
    public boolean isValid2(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {
    	IsValid i = new IsValid();
    	System.out.println(i.isValid("()"));
	}
}
