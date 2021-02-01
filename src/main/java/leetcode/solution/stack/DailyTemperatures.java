package leetcode.solution.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。
 * 如果之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
 * 你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * <p>
 * 单调栈
 * 可以维护一个存储下标的单调栈，从栈底到栈顶的下标对应的温度列表中的温度依次递减。
 * 当下个温度小于栈顶温度，入栈，当大于栈顶温度，移出栈顶温度，计算2个坐标的差值（储存的是下标）
 * 如果一个下标在单调栈里，则表示尚未找到下一次温度更高的下标。
 */
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] T) {
        int[] day = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            if (i == T.length - 1) {
                day[i] = 0;
                break;
            }

            for (int j = i; j < T.length + 1; j++) {
                if (j == T.length) {
                    day[i] = 0;
                    break;
                }
                if (T[j] > T[i]) {
                    day[i] = j - i;
                    break;
                }
            }
        }
        return day;
    }

    public int[] dailyTemperatures2(int[] T) {
        int length = T.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < length; i++) {
            int temperature = T[i];
            while (!stack.isEmpty() && temperature > T[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        DailyTemperatures d = new DailyTemperatures();
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        for (int i : d.dailyTemperatures(temperatures)) {
            System.out.print(i);
        }

    }
}
