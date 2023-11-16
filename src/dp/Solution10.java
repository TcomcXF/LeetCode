package dp;

import java.util.*;

public class Solution10 {
    public static void main(String[] args) {
        Solution10 solution10 = new Solution10();
        System.out.println(solution10.isMatch1("aa", "a")); // false
        System.out.println(solution10.isMatch1("aa", "a.")); // true
        System.out.println(solution10.isMatch1("ab", "a.")); // true
        System.out.println(solution10.isMatch1("aba", "a.a")); // true
        System.out.println(solution10.isMatch1("abab", "a..b")); // true
        System.out.println(solution10.isMatch1("abab", "a.bb")); // false
        System.out.println(solution10.isMatch1("aab", "a*")); // false
        System.out.println(solution10.isMatch1("aab", "a*b")); // true
        System.out.println(solution10.isMatch1("aab", "a*bb")); // false
        System.out.println(solution10.isMatch1("aabb", "a*b")); // false
        System.out.println(solution10.isMatch1("aabb", "a*.b")); // true
        System.out.println(solution10.isMatch1("aabb", "a*.*")); // true
        System.out.println(solution10.isMatch1("aabb", "a*b*")); // true
        System.out.println(solution10.isMatch1("aabb", "a*abb*")); // true
        //System.out.println(solution10.isMatch("aasdf", ".*"));  // true
    }

    // 常规思路：根据字符串p来匹配字符串s，但是这方式无法解决*号表示0个的问题，如s="aabb", p="a*abb*"
    public boolean isMatch(String s, String p) {
        if (".*".equals(p)) {
            return true;
        }
        int i = 0;
        int j = 0;
        while (j < p.length()) {
            if (i == s.length()) {
                if (p.charAt(j) != '.' && p.charAt(j) != '*') {
                    return false;
                }
                j++;
                continue;
            }
            if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                i++;
                j++;
                continue;
            }
            if (p.charAt(j) != '*') {
                return false;

            }
            if (p.charAt(j - 1) == '.') {
                i = s.length();
                j++;
                continue;
            }
            if (p.charAt(j - 1) == s.charAt(i)) {
                i++;
            } else {
                j++;
            }
        }
        if (i < s.length()) {
            return false;
        }

        return true;
    }

    // 参考答案：动态规划
    // 设置状态空间boolean[][] dp, dp[i][j]代表s串中下标从0到i-1的子串s(0,i-1)和p串中下标从0到j-1的子串p(0,j-1)是否能匹配
    // 1. 当s[i-1] == p[j-1] || p[j-1] == '.'时,dp[i][j] = dp[i-1][j-1];
    // 2. 当s[i-1] != p[j-1]时
    //   2.1 p[j-1] != '*', dp[i][j] = false;
    //   2.2 p[j-1] == '*', 分两种情况
    //     2.2.1 p[j-2] == s[i-1] || p[j-2] == '.',分三种情况, *表示0, *表示1, *表示>=2
    //       2.2.1.1 *表示0，dp[i][j] = dp[i][j-2]
    //       2.2.1.2 *表示1，dp[i][j] = dp[i-1][j-2]
    //       2.2.1.3*表示>=2，dp[i][j] = dp[i-1][j]
    //     2.2.2 p[j-2] != s[i-1],dp[i][j] = dp[i][j-2],题目保证了这里的p[j-1]不为'*号'，所以无需再细分场景
    public boolean isMatch1(String s, String p) {
        if (".*".equals(p)) {
            return true;
        }
        int m = s.length();
        int n = p.length();

        // 设置状态空间
        boolean[][] dp = new boolean[m + 1][n + 1];
        // 初始化状态
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                dp[i][j] = false;
            }
        }
        // 初始化第一行和第一列状态
        // 如果s和p都是空串，则能匹配，即dp[0][0] = true;
        // 如果p是空串，s非空，则dp[i][0] = false;
        // 如果s是空串，p不是，则当且仅当p中所有的字母后面都带一个*时能匹配
        // 题目保证了'*'号前必定为字符或者'.'，所以p的第一个字符不会是'*',也不会出现连续的'*'
        dp[0][0] = true;
        for (int j = 1; j < n + 1; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                // 1. 当s[i-1] == p[j-1] || p[j-1] == '.'时
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }
                // 2. 当s[i-1] != p[j-1]时
                // 2.1 p[j-1] != '*'
                if (p.charAt(j - 1) != '*') {
                    dp[i][j] = false;
                    continue;
                }
                // 2.2 p[j-1] == '*'
                // 2.2.1 p[j-2] == s[i-1] || p[j-2] == '.'
                if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                    dp[i][j] = dp[i][j - 2] || dp[i - 1][j - 2] || dp[i - 1][j];
                } else {
                    // 2.2.2 p[j-2] != s[i-1]
                    dp[i][j] = dp[i][j - 2];
                }
            }
        }

        return dp[m][n];
    }
}
