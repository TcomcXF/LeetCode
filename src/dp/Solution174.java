package dp;

public class Solution174 {
    public static void main(String[] args) {
        System.out.println(new Solution174().calculateMinimumHP1(new int[][]{{1, -3, 3}, {0, -2, 0}, {-3, -3, -3}}));
        System.out.println(new Solution174().calculateMinimumHP2(new int[][]{{1, -3, 3}, {0, -2, 0}, {-3, -3, -3}}));
    }

    // 如果使用正序递推，需要关注两个变量，一个是到达当前节点的路径和x，一个是到达当前节点的需要的最小生命值y
    // 到达节点(i，j)需要y最小，x最大，而后面的节点数值会影响到达节点(i，j)的路径选择，所以正序递推是无法实现的
    // 只能反向从右下走到左上

    // 回溯法
    public int calculateMinimumHP1(int[][] dungeon) {
        calculateMinimumHP1Impl(dungeon, 0, 0, 0, 0);
        if (minHp >= 0) {
            return 1;
        } else {
            return Math.abs(minHp) + 1;
        }
    }

    int minHp = Integer.MIN_VALUE;

    public void calculateMinimumHP1Impl(int[][] dungeon, int m, int n, int lastHp, int lastHistoryHp) {
        int currentHp = lastHp + dungeon[m][n];
        int currentHistoryHp = Math.min(lastHistoryHp, currentHp);
        if (m == dungeon.length - 1 && n == dungeon[0].length - 1) {
            minHp = Math.max(currentHistoryHp, minHp);
            return;
        }

        // 向右走
        if (n < dungeon[0].length - 1) {
            calculateMinimumHP1Impl(dungeon, m, n + 1, currentHp, currentHistoryHp);
        }


        // 向下走
        if (m < dungeon.length - 1) {
            calculateMinimumHP1Impl(dungeon, m + 1, n, currentHp, currentHistoryHp);
        }
    }

    // 状态转移表法
    public int calculateMinimumHP2(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        // 设置状态表 记录从(i,j)到达(m-1,n-1)需要的最小血量,最小血量大于0
        int[][] dp = new int[m][n];
        // 初始化(m-1,n-1)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // 设置左节点
                if (j > 0) {
                    dp[i][j - 1] = Math.max(Math.min(dp[i][j] - dungeon[i][j - 1], dp[i][j - 1]), 1);

                }

                // 设置上节点
                if (i > 0) {
                    dp[i - 1][j] = Math.max(Math.min(dp[i][j] - dungeon[i - 1][j], dp[i - 1][j]), 1);
                }
            }
        }

        return dp[0][0];
    }

}
