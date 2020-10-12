package leetcode.solution.double_pointer;

/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * @author Administrator
 *
 */
public class IsSubsequence {
	//记录字符相同时，长字符的位置，在往后偏移
    public boolean isSubsequence(String s, String t) {
    	if(s.length()==0 && t.length()!=0)return true;
    	if(s.length()!=0 && t.length()==0)return false;
    	char[]ss = s.toCharArray();
    	char[]ts = t.toCharArray();
    	int k = 0;
    	for(int i=0;i<ss.length;i++){
    		for(int j=k;j<ts.length;j++){
    			if(ss[i]==ts[j]){
    				k = j+1;
    				break;
    			}
    			if(j==ts.length-1 && ss[i]!=ts[j])
    				return false;
    		}
    	}
    	return k==ts.length;
    }
    
    //2个指针指向s,t的位置，相同时i后移，只要比较都移动j
    //最后看i是否移动完，i!=n说明有不同的未移动
    public boolean isSubsequence2(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }
    
    public static void main(String[] args) {
    	IsSubsequence i = new IsSubsequence();
    	String s = "acb";
    	String t = "ahbgdc";
    	System.out.println(i.isSubsequence(s, t));
	}
}
