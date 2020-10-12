package leetcode.solution.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]

给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。

可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 */
public class FindOrder {
	  public int[] findOrder(int numCourses, int[][] prerequisites){
	        int[] inDegree = new int[numCourses];
	        HashMap<Integer, List<Integer>> map = new HashMap<>();
	        Queue<Integer> queue = new LinkedList<>();
	        //创建入度表和哈希表
	        for (int i = 0; i < prerequisites.length; i++) {
	            inDegree[prerequisites[i][0]]++;
	            if(map.containsKey(prerequisites[i][1])){
	                map.get(prerequisites[i][1]).add(prerequisites[i][0]);
	            } else {
	                List<Integer> list = new ArrayList<>();
	                list.add(prerequisites[i][0]);
	                map.put(prerequisites[i][1], list);
	            }
	        }
	        //遍历，将index入队
	        List<Integer> res = new ArrayList<>();
	        for (int i = 0; i < numCourses; i++) {
	            if(inDegree[i] == 0){
	                queue.offer(i);
	            }
	        }
	        // 出队，查哈希表，将入度为零的入队
	        while (!queue.isEmpty()){
	            Integer cur = queue.poll();
	            res.add(cur);
	            if(map.containsKey(cur) && map.get(cur).size() != 0){
	                for (Integer num : map.get(cur)) {
	                    inDegree[num]--;
	                    if(inDegree[num] == 0) queue.offer(num);
	                }
	            }
	        }
	        //使用list的流来转为int[]数组，也可以通过遍历一遍完成转换。
	        return res.size() == numCourses ? res.stream().mapToInt(Integer::valueOf).toArray() : new int[0];
	    }
	
	public static void main(String[] args) {
		FindOrder f = new FindOrder();
		int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
		for(int i : f.findOrder(4, prerequisites)){
			System.out.println(i);
		}
	}
}
