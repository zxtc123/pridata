package leetcode.solution.basic;

import java.util.ArrayList;

public class DivingBoard {
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) return new int[0];
        int[] arr = new int[k + 1];
        if (shorter == longer) return new int[]{shorter * k};
        for (int i = 0; i <= k; i++) {
            arr[i] = shorter * (k - i) + longer * i;
        }
        return arr;
    }
}
