package dp;

public class Solution96 {
    public static void main(String[] args) {
        Solution96 solution96 = new Solution96();
        System.out.println(solution96.numTrees(1));
        System.out.println(solution96.numTrees(3));
    }

    // 二叉树的种类可以根据左子树的种类乘右子树的种类的类加得出,即f(k) = Σf(i) * f(j)
    // 由于k = i+j+1，所以f(k) = Σf(i) * f(k-i-1)
    public int numTrees(int n) {
        // 定义状态空间
        int[] dp = new int[n+1];
        // 初始化状态
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i-j-1];
            }
        }
        return dp[n];
    }
}
