package dp;

import java.lang.reflect.Array;

public class Solution63 {
    public static void main(String[] args) {
        System.out.println(new Solution63().uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
        System.out.println(new Solution63().uniquePathsWithObstacles1(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
    }

    // 状态转移表法
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // 设置状态表
        int[][] dp = new int[m][n];

        // 初始化第一行和第一列的数据为1
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            dp[0][i] = 1;
        }

        // 倒序递推公式：dp[i][[j] = dp[i-1][j] + dp[i][j-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        return uniquePathsWithObstacles1Impl(obstacleGrid, m - 1, n - 1);
    }

    public int[][] dp = null;

    // 状态转移方程法
    // 倒序递推公式：dp[i][[j] = dp[i-1][j] + dp[i][j-1]
    public int uniquePathsWithObstacles1Impl(int[][] obstacleGrid, int m, int n) {
        if (obstacleGrid[m][n] == 1) {
            return 0;
        }

        if (dp == null) {
            dp = new int[m + 1][n + 1];
            for (int i = 0; i < m + 1; i++) {
                for (int j = 0; j < n + 1; j++) {
                    dp[i][j] = -1;
                }
            }
            dp[0][0] = 1;
        }

        if (dp[m][n] == -1) {
            int leftNodePathsNum = 0;
            if (n > 0 && obstacleGrid[m][n - 1] == 0) {
                leftNodePathsNum = uniquePathsWithObstacles1Impl(obstacleGrid, m, n - 1);
            }

            int topNodePathsNum = 0;
            if (m > 0 && obstacleGrid[m - 1][n] == 0) {
                topNodePathsNum = uniquePathsWithObstacles1Impl(obstacleGrid, m - 1, n);
            }
            dp[m][n] = leftNodePathsNum + topNodePathsNum;
        }

        return dp[m][n];
    }
}
