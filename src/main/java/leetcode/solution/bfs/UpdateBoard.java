package leetcode.solution.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，
 * 'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，
 * 数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
 * <p>
 * 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
 * <p>
 * 1.如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
 * 2.如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
 * 3.如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
 * 4.如果在此次点击中，若无更多方块可被揭露，则返回面板。
 *
 * @author Administrator
 */
public class UpdateBoard {
    //没有记录访问点，相同点可能多次被访问
    int n;
    int m;
    Queue<int[]> bq;
    int[] dirX = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dirY = {-1, 0, 1, -1, 1, -1, 0, 1};

    public char[][] updateBoard(char[][] board, int[] click) {
        n = board.length;
        m = board[0].length;
        int cn = click[0];
        int cm = click[1];
        bq = new LinkedList<>();
        if (cn < 0 || cn >= n || cm < 0 || cm >= m) return board;
        if (board[cn][cm] == 'M') {
            //规则1
            board[cn][cm] = 'X';
            return board;
        } else {
            bq.offer(new int[]{cn, cm});
            bfs(bq, board);
            return board;
        }
    }

    private void bfs(Queue<int[]> bq, char[][] board) {
        boolean[][] vist = new boolean[n][m];
        while (!bq.isEmpty()) {
            int[] bi = bq.poll();
            int x = bi[0];
            int y = bi[1];
            //搜索这个点的四周
            int bnum = 0;//周围的雷数
            for (int i = 0; i < 8; i++) {
                int ib = searchBoard(board, x + dirX[i], y + dirY[i]);
                bnum += ib;
            }

            if (bnum == 0) {
                //规则2
                board[x][y] = 'B';
                for (int j = 0; j < 8; j++) {
                    int tx = x + dirX[j];
                    int ty = y + dirY[j];
                    if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length
                            || board[tx][ty] != 'E' || vist[tx][ty]) {
                        continue;
                    }
                    bq.offer(new int[]{tx, ty});
                    vist[tx][ty] = true;
                }
            } else {
                //规则3
                board[x][y] = Character.forDigit(bnum, 10);
            }
        }
    }

    //探测周围的点
    private int searchBoard(char[][] board, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || (board[x][y] != 'M' && board[x][y] != 'E')) return 0;
        if (board[x][y] == 'M') {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        UpdateBoard u = new UpdateBoard();
        char[][] board = new char[][]{{'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'M', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}};
        System.out.println(u.updateBoard(board, new int[]{3, 0}));
    }


    /**
     * dfs查询更快？
     *
     * @param board
     * @param click
     * @return
     */
    public char[][] updateBoard2(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        if (board[x][y] == 'M') {
            // 规则 1
            board[x][y] = 'X';
        } else {
            dfs(board, x, y);
        }
        return board;
    }

    public void dfs(char[][] board, int x, int y) {
        int cnt = 0;
        for (int i = 0; i < 8; ++i) {
            int tx = x + dirX[i];
            int ty = y + dirY[i];
            if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length) {
                continue;
            }
            // 不用判断 M，因为如果有 M 的话游戏已经结束了
            if (board[tx][ty] == 'M') {
                ++cnt;
            }
        }
        if (cnt > 0) {
            // 规则 3
            board[x][y] = (char) (cnt + '0');
        } else {
            // 规则 2
            board[x][y] = 'B';
            for (int i = 0; i < 8; ++i) {
                int tx = x + dirX[i];
                int ty = y + dirY[i];
                // 这里不需要在存在 B 的时候继续扩展，因为 B 之前被点击的时候已经被扩展过了
                if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length || board[tx][ty] != 'E') {
                    continue;
                }
                dfs(board, tx, ty);
            }
        }
    }


}
