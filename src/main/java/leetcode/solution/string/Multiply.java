package leetcode.solution.string;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * @author Administrator
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class Multiply {
    private static String ZREO = "0";

    //数值可能超过 造成误差
    public String multiply(String num1, String num2) {
        if (ZREO.equals(num1) || ZREO.equals(num2)) return ZREO;
        char[] arr1 = num1.toCharArray();
        char[] arr2 = num2.toCharArray();
        int inum1 = 0;
        int bs = 1;
        int result = 0;
        for (int i = arr1.length - 1; i > -1; i--) {
            inum1 = inum1 + (arr1[i] - '0') * bs;
            bs *= 10;
        }
        int bs2 = 1;
        for (int j = arr2.length - 1; j > -1; j--) {
            result += bs2 * (arr2[j] - '0') * inum1;
            bs2 *= 10;
        }
        return Integer.valueOf(result).toString();
    }

    //num2中取数逐行与num1相乘，多的位数，stringbuilder补0
    public String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        // 保存计算结果
        String res = "0";

        // num2 逐位与 num1 相乘
        for (int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0;
            // 保存 num2 第i位数字与 num1 相乘的结果
            StringBuilder temp = new StringBuilder();
            // 补 0 
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                temp.append(0);
            }
            int n2 = num2.charAt(i) - '0';

            // num2 的第 i 位数字 n2 与 num1 相乘
            for (int j = num1.length() - 1; j >= 0 || carry != 0; j--) {
                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int product = (n1 * n2 + carry) % 10;
                temp.append(product);
                carry = (n1 * n2 + carry) / 10;
            }
            // 将当前结果与新计算的结果求和作为新的结果
            res = addStrings(res, temp.reverse().toString());
        }
        return res;
    }

    /**
     * 对两个字符串数字进行相加，返回字符串形式的和
     */
    public String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1;
             i >= 0 || j >= 0 || carry != 0;
             i--, j--) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            int sum = (x + y + carry) % 10;
            builder.append(sum);
            carry = (x + y + carry) / 10;
        }
        return builder.reverse().toString();
    }

    public static void main(String[] args) {
        Multiply m = new Multiply();
        System.out.println(m.multiply("123", "456"));
    }
}
