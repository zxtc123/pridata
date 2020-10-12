package leetcode.solution.double_pointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * @author Administrator
 * 
 * 排序加双指针
 * 将nums排序，选择最左和最右指针，当加合大于0，右指针向左移，当加合小于0，左指针向右移
 *
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>>threeList = new ArrayList<>();
    	if(nums.length<3)return threeList;
    	Arrays.sort(nums);
    	if(nums[0]>0 || nums[nums.length-1]<0)return threeList;
    	for(int i=0;i<nums.length-2;i++){//最后留2个数组合
    		if(nums[i]>0)break;//去除特殊情况
    		if(i>0 && nums[i]==nums[i-1])continue;//去除重复情况
    		int l = i+1;
    		int r = nums.length-1;
    		while(l<r){
    			if(nums[i]+nums[l]+nums[r]==0){
        			threeList.add(new ArrayList<Integer>(Arrays.asList(nums[i],nums[l],nums[r])));
        			while(l<r && nums[l]==nums[l+1])l++;
        			while(l<r && nums[r]==nums[r-1])r--;
        			l++;
        			r--;
        			continue;
    			}
    			if(nums[i]+nums[l]+nums[r]<0){
    				l++;
    				continue;
    			}
    			if(nums[i]+nums[l]+nums[r]>0){
    				r--;
    				continue;
    			}
    		}
    	}
    	return threeList;
    }
    
    
    public static void main(String[] args) {
    	ThreeSum t = new ThreeSum();
		int[] nums ={-1, 0, 1, 2, -1, -4};
		int[] nums2 ={0, 0, 0, 0, 0, 0};
		System.out.print(t.threeSum(nums2));
	}
}
