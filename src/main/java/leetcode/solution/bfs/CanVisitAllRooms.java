package leetcode.solution.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，
 * 其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 * <p>
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 * 你可以自由地在房间之间来回走动。
 * 如果能进入每个房间返回 true，否则返回 false。
 * <p>
 * 示例 1：
 * 输入: [[1],[2],[3],[]]
 * 输出: true
 * 解释:
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 * <p>
 * 示例 2：
 * 输入：[[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 */
public class CanVisitAllRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] vtRooms = new boolean[rooms.size()];
        vtRooms[0] = true;
        Queue<Integer> qu = new LinkedList<>();
        if (rooms.size() == 1 && rooms.get(0).size() == 0) {
            return true;
        } else if (rooms.get(0).size() == 0) {
            return false;
        } else {
            for (Integer i : rooms.get(0)) {
                qu.offer(i);
            }
        }

        while (!qu.isEmpty()) {
            int room = qu.poll();
            vtRooms[room] = true;
            for (Integer i : rooms.get(room)) {
                if (!vtRooms[i]) {
                    qu.offer(i);
                }
                vtRooms[i] = true;
            }
        }


        for (boolean f : vtRooms) {
            if (!f) return false;
        }
        return true;
    }

    /**
     * 使用可访问房间数来判断所有是否都可以访问
     * 这样可以省去特殊情况的判断
     *
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        int n = rooms.size(), num = 0;
        boolean[] vis = new boolean[n];
        Queue<Integer> que = new LinkedList<Integer>();
        vis[0] = true;
        que.offer(0);
        while (!que.isEmpty()) {
            int x = que.poll();
            num++;
            for (int it : rooms.get(x)) {
                if (!vis[it]) {
                    vis[it] = true;
                    que.offer(it);
                }
            }
        }
        return num == n;
    }

    /**
     * 使用DFS来不断访问房间
     */
    boolean[] vis;
    int num;

    public boolean canVisitAllRooms3(List<List<Integer>> rooms) {
        int n = rooms.size();
        num = 0;
        vis = new boolean[n];
        dfs(rooms, 0);
        return num == n;
    }

    public void dfs(List<List<Integer>> rooms, int x) {
        vis[x] = true;
        num++;
        for (int it : rooms.get(x)) {
            if (!vis[it]) {
                dfs(rooms, it);
            }
        }
    }


    public static void main(String[] args) {
        CanVisitAllRooms c = new CanVisitAllRooms();
        List<List<Integer>> rooms = new ArrayList<>();


//    	[[1],[2],[3],[]]		
//    	rooms.add(Arrays.asList(1));
//    	rooms.add(Arrays.asList(2));
//    	rooms.add(Arrays.asList(3));
//    	rooms.add(Arrays.asList());

//    	[[1,3],[3,0,1],[2],[0]]
        rooms.add(Arrays.asList(1, 3));
        rooms.add(Arrays.asList(3, 0, 1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList(0));

//    	[[],[1,1],[2,2]]
        rooms.add(Arrays.asList());
        rooms.add(Arrays.asList(1, 1));
        rooms.add(Arrays.asList(2, 2));

        System.out.print(c.canVisitAllRooms(rooms));
    }


}
