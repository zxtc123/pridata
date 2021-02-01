package leetcode.solution.dynamic_planning;

/**
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。像句子
 * "I reset the computer. It still didn’t boot!"已经变成了
 * "iresetthecomputeritstilldidntboot"。
 * 在处理标点符号和大小写之前，你得先把它断成词语。
 * 当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。
 * 假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 * <p>
 * 输入：
 * dictionary = ["looked","just","like","her","brother"]
 * sentence = "jesslookedjustliketimherbrother"
 * 输出： 7
 * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
 */
public class Respace {
    /**
     * 看到题目，求最小值。想到用动态规划求解。
     * 定义状态量，dp[i]表示sentence中的前i个字符组成的字符串中最大识别数。
     * 状态转移：对于每个dp[i+1],他是从dp[i]转化过来的，那么第i+1个字符，他可以选，也可以不选，选就进行匹配，
     * 不选的话dp[i+1]=dp[i].
     * 选的话，就对字典中的每个字符串进行匹配，取最大的识别数。具体根据字典中每个字符串的长度来进行状态转移，
     * 公式：dp[i]=dp[i-dictionary[j]]+dictionary[j].length(),
     * 对于字典中的每个字符串都进行状态转移，每一轮内循环，都更新下最大值。因此状态方程为
     * dp[i] = Math.max(dp[i - dictionary[j].length()] + dictionary[j].length(), dp[i]);
     * 最终结果就是字符串长度-最大匹配数。
     *
     * @param dictionary
     * @param sentence
     * @return
     */
    public int respace(String[] dictionary, String sentence) {
        int n = sentence.length();
        int m = dictionary.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            for (int j = 0; j < m; j++) {
                if (i < dictionary[j].length()) continue;
                if (sentence.substring(i - dictionary[j].length(), i).equals(dictionary[j])) {
                    dp[i] = Math.max(dp[i - dictionary[j].length()] + dictionary[j].length(), dp[i]);
                }
            }
        }
        return n - dp[n];
    }
}
