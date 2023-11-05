package dp;

import java.util.Arrays;

public class Solution62 {
    public static void main(String[] args) {
        System.out.println(new Solution62().uniquePaths(3, 7));
        System.out.println(new Solution62().uniquePaths1(3, 7));
        System.out.println(new Solution62().uniquePaths1Pro(3, 7));
    }

    public int[][] dp = null;

    // 状态转移方程法
    // 倒序递推公式：dp[i][[j] = dp[i-1][j] + dp[i][j-1]
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }

        if (dp == null) {
            dp = new int[m + 1][n + 1];
        }

        if (dp[m][n] == 0) {
            dp[m][n] = uniquePaths(Math.max(m - 1, 1), n) + uniquePaths(m, Math.max(n - 1, 1));
        }

        return dp[m][n];
    }

    // 状态转移表法
    public int uniquePaths1(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }

        // 设置状态表
        int[][] dp = new int[m][n];

        // 初始化第一行和第一列的数据为1
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        //  倒序递推公式：dp[i][[j] = dp[i-1][j] + dp[i][j-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    // 优化状态表法的空间
    public int uniquePaths1Pro(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }

        // 设置状态表
        int[] dp = new int[n];

        // 初始化第一行和第一列的数据为1
        Arrays.fill(dp, 1);

        // 倒序递推公式：dp[j] = dp[j-1] + dp[j]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j-1] + dp[j];
            }
        }
        return dp[n - 1];
    }
}