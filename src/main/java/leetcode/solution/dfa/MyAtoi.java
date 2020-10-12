package leetcode.solution.dfa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。

注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，
则你的函数不需要进行转换，即无法进行有效转换。
在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 */
public class MyAtoi {
	
	/**
	 * 有限状态机
	 * 我们的程序在每个时刻有一个状态 s，每次从序列中输入一个字符 c，并根据字符 c 转移到下一个状态 s'。
	 * 这样，我们只需要建立一个覆盖所有情况的从 s 与 c 映射到 s' 的表格即可解决题目中的问题。
	 * 遇到各种字符，状态的转变过程
	 * 		' '		+/-		number		other
start		start	signed	in_number	end
signed		end		end		in_number	end
in_number	end		end		in_number	end
end			end		end		end			end

	 */
    final String START = "start";
    final String SIGNED = "signed";
    final String IN_NUM = "in_number";
    final String END = "end";
    String state = START;
    Map<String, String[]> map;
    public int sign = 1;
    public long ans = 0;

    public MyAtoi() {
        map = new HashMap<>();
        map.put(START, new String[]{START, SIGNED, IN_NUM, END});
        map.put(SIGNED, new String[]{END, END, IN_NUM, END});
        map.put(IN_NUM, new String[]{END, END, IN_NUM, END});
        map.put(END, new String[]{END, END, END, END});
    }

    public int get_col(char c) {
        if (c == ' ') return 0;
        if (c == '+' || c == '-') return 1;
        if (c >= '0' && c <= '9') return 2;
        return 3;
    }

    public void get(char c) {
        state = map.get(state)[get_col(c)];
        if (state.equals(IN_NUM)) {
            ans = ans * 10 + c - '0';
            if (sign == 1) {
                ans = Math.min(ans, Integer.MAX_VALUE);
            } else {
                // -(long)Integer.MIN_VALUE，这个操作有点东西，不然越界
                ans = Math.min(ans, -(long)Integer.MIN_VALUE);
            }
        } else if (state.equals(SIGNED))
            sign = c == '+' ? 1 : -1;
    }
    
    public int myAtoi2(String str) {
    	MyAtoi automaton = new MyAtoi();
        char[] c = str.toCharArray();
        for (char ch : c) {
            automaton.get(ch);
        }
        return automaton.sign * ((int) automaton.ans);
    }
	
	
	/**
	 * 如何判断溢出情况：
	 * 加上最后一个数前判断：当前数是否大于 max/10 且 最后一个数是否大于 max%10
	 * 注意计算min的溢出 要判断最后一个数是否大于 -（min%10） 将负数转换为正数
	 * @param str
	 * @return
	 */
    public int myAtoi(String str) {
        char[] array = str.trim().toCharArray();
        if(array.length == 0)return 0;
        char firstChar = array[0];
        boolean flag = true;//默认正数
        if(firstChar == '-'){
        	flag = false;
        	array = Arrays.copyOfRange(array, 1, array.length);
        }else if(firstChar == '+'){
        	array = Arrays.copyOfRange(array, 1, array.length);
        }else if(Character.isDigit(firstChar)){
        	
        }else {
        	return 0;
        }
        char[] newNum = new char[array.length];
        for(int i = 0; i < array.length; i++){
        	char c = array[i];
        	if(Character.isDigit(c)){
        		newNum[i] = c;
        	}else{
        		newNum = Arrays.copyOfRange(array, 0, i);
        		break;
        	}
        }
        
        int res = 0;
        for(int j = 0; j < newNum.length; j++){
        	int num = newNum[j] - '0';
        	if(res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE/10 && num > Integer.MAX_VALUE%10)){
        		return Integer.MAX_VALUE;
        	}
        	if(res < Integer.MIN_VALUE/10 || (res == Integer.MIN_VALUE/10 && num > -(Integer.MIN_VALUE%10))){//负数转换为正数
        		return Integer.MIN_VALUE;
        	}
        	res = 10*res + (flag?1:-1)*num;
        }

        return res;
    }
    
    
    public static void main(String[] args) {
    	MyAtoi m = new MyAtoi();
    	String str = "42";
    	String str1 = "4193 with words";
    	System.out.print(m.myAtoi(str1));
	}
}
