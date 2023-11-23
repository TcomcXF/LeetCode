# LeetCode
leetcode solution

```
package dp;

import java.util.List;

public class Solution120 {
    // f(i,j) = min(f(i-1,j) + f(i-1,j-1)) +  triangle[i][j]
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // 定义状态空间
        int[][] dp = new int[n][n];

        // 初始化状态
        dp[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] + triangle.get(i).get(0);
            dp[i][i] = dp[i-1][i-1] + triangle.get(i).get(i);
        }


        int minDist = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i-1][j-1],dp[i-1][j]) + triangle.get(i).get(j);
            }
        }
        for (int i = 0; i < n; i++) {
            minDist = Math.min(minDist, dp[n-1][i]);
        }
        return minDist;
    }
}
```
