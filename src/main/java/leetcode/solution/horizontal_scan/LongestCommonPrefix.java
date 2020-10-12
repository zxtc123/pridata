package leetcode.solution.horizontal_scan;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
	如果不存在公共前缀，返回空字符串 ""。
 * @author Administrator
 * 
 * 查询一组字符串最长前缀的方法描述为LCP(s1,s2,s3....sn);
 * 
 * LCP(s1,s2,s3....sn)可以表述为  LCP(LCP(LCP(s1,s2),s3)...sn)
 * 水平扫描：由后面的数扫描是否完全包含前一个，不包含，将前一个数尾端去掉一个，继续扫描
 *
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
    	StringBuilder prefix = new StringBuilder("");
    	for(int i=0;;i++){
    		int j=0;
    		if(j>strs.length-1 || i > strs[j].length()-1)break;
    		char ch=strs[j].charAt(i);
    		while(j<=strs.length-1 && i <= strs[j].length()-1 && strs[j].charAt(i)==ch){
    			j++;
    		}
    		if(j==strs.length){
    			prefix.append(ch);
    		}else{
    			break;
    		}
    	}
    	return prefix.toString();
    }
    
    public String longestCommonPrefix2(String[] strs) {
    	   if (strs.length == 0) return "";
    	   String prefix = strs[0];
    	   for (int i = 1; i < strs.length; i++)
    	       while (strs[i].indexOf(prefix) != 0) {
    	           prefix = prefix.substring(0, prefix.length() - 1);
    	           if (prefix.isEmpty()) return "";
    	       }        
    	   return prefix;
    	}

    
    public static void main(String[] args) {
		String[] strs = {"flower","flow","flight"};
		LongestCommonPrefix l = new LongestCommonPrefix();
		System.out.print(l.longestCommonPrefix2(strs));
	}
}
