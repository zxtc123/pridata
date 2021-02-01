package leetcode.solution.greed;

import java.util.Arrays;

/**
 * 公司计划面试 2N 人。第 i 人飞往 A 市的费用为 costs[i][0]，飞往 B 市的费用为 costs[i][1]
 * 返回将每个人都飞到某座城市的最低费用，要求每个城市都有 N 人抵达。
 * <p>
 * 数组第一个数代表去A的费用，第二个数代表去B的费用，要求总的费用最低，且2个城市都去N个人
 * <p>
 * 贪心算法：
 * 假设都去B，之后选择N个去A，费用减少 pib-pia
 * 排序减少费用，选择1半最大减少的，拿总费用去减
 * <p>
 * 排序从小到大
 * 总费用去加pia-pib
 * 选1半最小的
 */
public class TwoCitySchedCost {
    public int twoCitySchedCost(int[][] costs) {
        int sum = 0;
        int[] pis = new int[costs.length];
        for (int i = 0; i < costs.length; i++) {
            int piA = costs[i][0];
            int piB = costs[i][1];
            pis[i] = piA - piB;
            sum += costs[i][1];
        }
        Arrays.sort(pis);
        for (int j = 0; j < costs.length / 2; j++) {
            sum += pis[j];
        }
        return sum;
    }

    public static void main(String[] args) {
        TwoCitySchedCost t = new TwoCitySchedCost();
        int[][] costs = {{10, 20}, {30, 200}, {400, 50}, {30, 20}};
        System.out.print(t.twoCitySchedCost(costs));
    }
}
