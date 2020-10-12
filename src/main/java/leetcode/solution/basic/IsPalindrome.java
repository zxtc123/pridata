package leetcode.solution.basic;

/**
 * 判断是否是回文
 * @author Administrator
 *
 */
public class IsPalindrome {
    public static boolean isPalindrome(int x) {
    	boolean flag = true;
    	if(x<0){
    		flag = false;
    	}else{
    		String str = String.valueOf(x);
    		char[] chars = str.toCharArray();
    		for(int i=0;i<chars.length;i++){
    			if(chars[i]!=chars[chars.length-i-1]){
    				flag = false;
    			}
    		}
    	}
    	return flag;
    }
    
    public static void main(String[] args) {
		System.out.print(isPalindrome(020));
	}

}
