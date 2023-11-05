package dp;

public class Solution64 {
    public static void main(String[] args) {
        System.out.println(new Solution64().minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println(new Solution64().minPathSum1(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println(new Solution64().minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
        System.out.println(new Solution64().minPathSum1(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    // 状态转移表法
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 设置状态表
        int[][] dp = new int[m][n];

        // 初始化第一个元素的数据
        dp[0][0] = grid[0][0];

        // 正序递推公式：dp[i+1][j] = dp[i][j] + grid[i+1][j]
        // 正序递推公式：dp[i][j+1] = dp[i][j] + grid[i][j+1]
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i + 1 < m) {
                    if (dp[i + 1][j] > 0) {
                        dp[i + 1][j] = Math.min(dp[i][j] + grid[i + 1][j], dp[i + 1][j]);
                    } else {
                        dp[i + 1][j] = dp[i][j] + grid[i + 1][j];
                    }

                }
                if (j + 1 < n) {
                    if (dp[i][j + 1] > 0) {
                        dp[i][j + 1] = Math.min(dp[i][j] + grid[i][j + 1], dp[i][j + 1]);
                    } else {
                        dp[i][j + 1] = dp[i][j] + grid[i][j + 1];
                    }

                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public int minPathSum1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        return minPathSum11Impl(grid, m - 1, n - 1);
    }

    public int[][] dp = null;

    // 状态转移方程法
    // 倒序递推公式：dp[i][[j] = main(dp[i-1][j], dp[i][j-1]) + grid[i][j]
    public int minPathSum11Impl(int[][] grid, int m, int n) {
        if (dp == null) {
            dp = new int[m + 1][n + 1];
            for (int i = 0; i < m + 1; i++) {
                for (int j = 0; j < n + 1; j++) {
                    dp[i][j] = -1;
                }
            }
            dp[0][0] = grid[0][0];
        }

        if (dp[m][n] == -1) {
            int leftNodePathsSum = Integer.MAX_VALUE;
            if (n > 0) {
                leftNodePathsSum = minPathSum11Impl(grid, m, n - 1);
            }

            int topNodePathsSum = Integer.MAX_VALUE;
            if (m > 0) {
                topNodePathsSum = minPathSum11Impl(grid, m - 1, n);
            }
            dp[m][n] = Math.min(leftNodePathsSum, topNodePathsSum) + grid[m][n];
        }

        return dp[m][n];
    }
}
