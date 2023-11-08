package dp;

public class Solution72 {
    public static void main(String[] args) {
        Solution72 solution72 = new Solution72();
        //System.out.println(solution72.minDistance("", ""));
        //System.out.println(solution72.minDistance("horse", "ros"));
        //System.out.println(solution72.minDistance("intention", "execution"));
        System.out.println(solution72.minDistance("pneumonoultramicroscopicsilicovolcanoconiosis", "ultramicroscopically"));
        //System.out.println(solution72.minDistance("silicovolcanoconiosis", "ally"));
    }

    // 回溯法
    // 对比A串和B串，如果当前字符相同就匹配下一个字符，A串和B串的坐标+1，莱文斯坦距离不变
    // 如果当前字符不同，分别执行删除当前字符，替换当前字符，新增当前字符的操作；
    // 其中对A串删除或对B串新增字符，A串坐标+1，B串坐标不变，莱文斯坦距离+1
    // 其中对B串删除和对A串新增字符，A串坐标不变，B串坐标+1，莱文斯坦距离+1
    // 替换操作为A串和B串的坐标+1，莱文斯坦距离+1
    // 涉及到的变量有A串坐标，B串坐标，莱文斯坦距离三个
    // 回溯时间复杂度O(3^n)

    // 状态转移表
    // 使用二维数组即可，数组值为莱文斯坦距离,
    // 状态转移方程：字符相同时f(i,j) = min(f(i-1,j-1), f(i-1,j)+1, f(i,j-1)+1),
    // 字符不同时f(i,j) = min(f(i-1,j), f(i-1,j-1), f(i,j-1)) + 1
    public int minDistance(String word1, String word2) {
        char[] word1Arr = word1.toCharArray();
        char[] word2Arr = word2.toCharArray();
        int m = word1Arr.length;
        int n = word2Arr.length;

        if (m == 0 || n == 0) {
            return m + n;
        }

        // 设置状态空间，数组存储当前的最小编辑距离
        int[][] dp = new int[m][n];

        // 初始化状态空间
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        // 初始化基本状态
        if (word1Arr[0] == word2Arr[0]) {
            dp[0][0] = 0;
        } else {
            dp[0][0] = 1;
        }

        // 长度为i+1的字符串与一个字符c的编辑距离只有i和i+1两种
        for (int i = 1; i < m; i++) {
            if (word1Arr[i] == word2Arr[0]) {
                // 所以如果字符串中包含这个字符时则编辑距离是i
                dp[i][0] = i;
            } else {
                dp[i][0] = dp[i - 1][0] + 1;
            }
        }

        for (int i = 1; i < n; i++) {
            if (word1Arr[0] == word2Arr[i]) {
                dp[0][i] = i;
            } else {
                dp[0][i] = dp[0][i - 1] + 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (word1Arr[i] == word2Arr[j]) {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1), dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
