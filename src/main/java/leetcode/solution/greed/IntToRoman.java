package leetcode.solution.greed;

/**
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 * <p>
 * <p>
 * 翻译罗马数字是从左到右依次翻译的
 * 目标要求左边使用的符号代表的数字越大越好，采用贪心策略
 * 贪心选择是采用从顶向下、以迭代的方法做出相继选择，每做一次贪心选择就将所求问题简化为一个规模更小的子问题
 */
public class IntToRoman {
    public static String intToRoman(int num) {
        String roman = "";
        if (num < 1 || num > 3999) return roman;
        if (num >= 1000) {
            int q = num / 1000;
            for (int i = 0; i < q; i++) {
                roman += "M";
            }
            num = num - 1000 * q;
        }
        if (num >= 100) {
            int b = num / 100;
            if (b >= 5) {
                if (b == 9) {
                    roman += "CM";
                } else if (b == 10) {
                    roman += "M";
                } else {
                    roman += "D";
                    for (int j = 5; j < b; j++) {
                        roman += "C";
                    }
                }
            } else {
                if (b == 4) {
                    roman += "CD";
                } else {
                    for (int k = 0; k < b; k++) {
                        roman += "C";
                    }
                }
            }
            num = num - 100 * b;
        }
        if (num >= 10) {
            int s = num / 10;
            if (s >= 5) {
                if (s == 9) {
                    roman += "XC";
                } else if (s == 10) {
                    roman += "C";
                } else {
                    roman += "L";
                    for (int j = 5; j < s; j++) {
                        roman += "X";
                    }
                }
            } else {
                if (s == 4) {
                    roman += "XL";
                } else {
                    for (int k = 0; k < s; k++) {
                        roman += "X";
                    }
                }
            }
            num = num - 10 * s;
        }
        if (num > 0) {
            if (num >= 5) {
                if (num == 9) {
                    roman += "IX";
                } else if (num == 10) {
                    roman += "X";
                } else {
                    roman += "V";
                    for (int j = 5; j < num; j++) {
                        roman += "I";
                    }
                }
            } else {
                if (num == 4) {
                    roman += "IV";
                } else {
                    for (int k = 0; k < num; k++) {
                        roman += "I";
                    }
                }
            }
        }


        return roman;
    }

    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String intToRoman2(int num) {
        StringBuilder sb = new StringBuilder();
        // Loop through each symbol, stopping if num becomes 0.
        for (int i = 0; i < values.length && num >= 0; i++) {
            // Repeat while the current symbol still fits into num.
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        IntToRoman i = new IntToRoman();
        System.out.print(i.intToRoman2(1998));
    }
}
