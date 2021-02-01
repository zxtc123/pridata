package leetcode.solution.double_pointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> fourSumList = new ArrayList<>();
        Arrays.sort(nums);
        int length = nums.length;
        for (int a = 0; a < length - 3; a++) {
            while (a > 0 && a < length - 3 && nums[a] == nums[a - 1]) a++;
            for (int d = length - 1; d > a + 1; d--) {
                while (d < length - 1 && d > 2 && nums[d] == nums[d + 1]) d--;
                int b = a + 1;
                int c = d - 1;
                while (a < b && b < c && c < d) {
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];
                    if (sum < target) {
                        b++;
                        while (b < c && nums[b] == nums[b - 1]) b++;
                    } else if (sum > target) {
                        c--;
                        while (b < c && nums[c] == nums[c + 1]) c--;
                    } else {
                        fourSumList.add(new ArrayList(Arrays.asList(nums[a], nums[b], nums[c], nums[d])));
                        c--;
                        while (b < c && nums[c] == nums[c + 1]) c--;
                    }
                }
            }

        }
        return fourSumList;
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        /*定义一个返回值*/
        List<List<Integer>> result = new ArrayList<>();
        /*当数组为null或元素小于4个时，直接返回*/
        if (nums == null || nums.length < 4) {
            return result;
        }
        /*对数组进行从小到大排序*/
        Arrays.sort(nums);
        /*数组长度*/
        int length = nums.length;
        /*定义4个指针k，i，j，h  k从0开始遍历，i从k+1开始遍历，留下j和h，j指向i+1，h指向数组最大值*/
        for (int k = 0; k < length - 3; k++) {
            /*当k的值与前面的值相等时忽略*/
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏*/
            int min1 = nums[k] + nums[k + 1] + nums[k + 2] + nums[k + 3];
            if (min1 > target) {
                break;
            }
            /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
            int max1 = nums[k] + nums[length - 1] + nums[length - 2] + nums[length - 3];
            if (max1 < target) {
                continue;
            }
            /*第二层循环i，初始值指向k+1*/
            for (int i = k + 1; i < length - 2; i++) {
                /*当i的值与前面的值相等时忽略*/
                if (i > k + 1 && nums[i] == nums[i - 1]) {
                    continue;
                }
                /*定义指针j指向i+1*/
                int j = i + 1;
                /*定义指针h指向数组末尾*/
                int h = length - 1;
                /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏，忽略*/
                int min = nums[k] + nums[i] + nums[j] + nums[j + 1];
                if (min > target) {
                    continue;
                }
                /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
                int max = nums[k] + nums[i] + nums[h] + nums[h - 1];
                if (max < target) {
                    continue;
                }
                /*开始j指针和h指针的表演，计算当前和，如果等于目标值，j++并去重，h--并去重，当当前和大于目标值时h--，当当前和小于目标值时j++*/
                while (j < h) {
                    int curr = nums[k] + nums[i] + nums[j] + nums[h];
                    if (curr == target) {
                        result.add(Arrays.asList(nums[k], nums[i], nums[j], nums[h]));
                        j++;
                        while (j < h && nums[j] == nums[j - 1]) {
                            j++;
                        }
                        h--;
                        while (j < h && i < h && nums[h] == nums[h + 1]) {
                            h--;
                        }
                    } else if (curr > target) {
                        h--;
                    } else {
                        j++;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int[] nums2 = {-3, -1, 0, 2, 4, 5};
        int[] nums3 = {-3, -2, -1, 0, 0, 1, 2, 3};
        int[] nums4 = {5, 5, 3, 5, 1, -5, 1, -2};
        int[] nums5 = {0, 0, 0, 0};

        FourSum f = new FourSum();
        System.out.println(f.fourSum2(nums5, 0));
        System.out.println(f.fourSum2(nums5, 0).size());

    }
}
