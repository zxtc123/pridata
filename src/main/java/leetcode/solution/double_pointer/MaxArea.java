package leetcode.solution.double_pointer;

import java.util.Arrays;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，
 * 垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 *动态规划，基本的表达式: area = min(height[i], height[j]) * (j - i)
 *使用双指针，指向数组2端，移动指针获取最大值
 *每次移动2个指针对应数据小的指针：
 *	若移动指针对应数大的指针，在距离变短的情况下，高度也可能变小，不符合
 */
public class MaxArea {
    public static int maxArea(int[] height) {
    	int maxArea=0;
    	int l = 0;//左指针
    	int r = height.length-1;//右指针
    	while(l<=r){
    		int area = Math.min(height[l], height[r])*(r-l);
    		maxArea = Math.max(maxArea, area);
    		if(height[l]>height[r]){
    			r--;
    		}else{
    			l++;
    		}
    	}
    	
    	return maxArea;
    }
    
    public static void main(String[] args) {
    	int[] height = {1,4,2,3,5};
    	System.out.print(maxArea(height));
	}

}
