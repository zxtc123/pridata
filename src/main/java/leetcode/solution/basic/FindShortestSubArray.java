package leetcode.solution.basic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class FindShortestSubArray {
    public int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.get(num) == null) {
                map.put(num, new int[]{1, i, i});
            } else {
                int[] arrs = map.get(num);
                arrs[0]++;
                arrs[2] = i;
                map.put(num, arrs);
            }
        }
        int subArr = Integer.MAX_VALUE;
        int maxd = 0;
        for (Entry<Integer, int[]> entry : map.entrySet()) {
            int[] ens = entry.getValue();
            if (ens[0] > maxd) {
                maxd = ens[0];
                subArr = ens[2] - ens[1] + 1;
            } else if (ens[0] == maxd) {
                maxd = ens[0];
                subArr = Math.min(subArr, ens[2] - ens[1] + 1);
            }
        }

        return subArr;
    }

    public static void main(String[] args) {
        FindShortestSubArray f = new FindShortestSubArray();
        int[] nums = new int[]{1, 2, 2, 3, 1, 4, 2};
        System.out.println(f.findShortestSubArray(nums));
    }
}
