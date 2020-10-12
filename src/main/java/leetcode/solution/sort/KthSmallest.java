package leetcode.solution.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KthSmallest {
	
	//直接排序
	   public int kthSmallest(int[][] matrix, int k) {
	        int rows = matrix.length, columns = matrix[0].length;
	        int[] sorted = new int[rows * columns];
	        int index = 0;
	        for (int[] row : matrix) {
	            for (int num : row) {
	                sorted[index++] = num;
	            }
	        }
	        Arrays.sort(sorted);
	        return sorted[k - 1];
	    }
	
	   //归并排序，使用小堆来维护
    public int kthSmallest2(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] now = pq.poll();
            if (now[2] != n - 1) {
                pq.offer(new int[]{matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
            }
        }
        return pq.poll()[0];
    }
}
