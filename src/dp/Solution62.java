package dp;

public class Solution62 {
    public static void main(String[] args) {
        System.out.println(new Solution62().uniquePaths(3, 7));
    }

    public int[][] dp = null;

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
}