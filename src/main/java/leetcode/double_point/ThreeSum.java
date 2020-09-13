package leetcode.double_point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 排序+双指针
 *
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> threeList = new ArrayList<>();
        Arrays.sort(nums);
        if(nums.length<3 || nums[0]>0 || nums[nums.length-1]<0)return threeList;
        for(int i=0;i<nums.length-2;i++){
            if(i>0 && nums[i]==nums[i-1])continue;
            int l = i+1;
            int r = nums.length-1;

            while(l<r){
                int sum = nums[i]+nums[l]+nums[r];
                if(sum==0){
                    threeList.add(new ArrayList<>(Arrays.asList(nums[i],nums[l],nums[r])));
                    while (l<r && nums[l] == nums[l+1]) l++; // 去重
                    while (l<r && nums[r] == nums[r-1]) r--; // 去重
                    l++;//满足条件的情况下，不可能，移动一边还满足条件
                    r--;
                    continue;
                }
                if(sum<0){
                    l++;
                }
                if(sum>0){
                    r--;
                }
            }
        }
        return threeList;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ThreeSum t = new ThreeSum();
        int[]nums = {-1,0,1,2,-1,-4};
        int[]nums2={0,0,0,0,0,0};
        System.out.println(t.threeSum(nums));
    }
}
