package dp;

public class Solution91 {
    public static void main(String[] args) {
        Solution91 solution91 = new Solution91();
        System.out.println(solution91.numDecodings("11106"));
        System.out.println(solution91.numDecodings("2226"));
        System.out.println(solution91.numDecodings("10"));
        System.out.println(solution91.numDecodings("227"));
//        System.out.println(solution91.numDecodings1("11106"));
//        System.out.println(solution91.numDecodings1("2226"));
//        System.out.println(solution91.numDecodings1("10"));
//        System.out.println(solution91.numDecodings1("227"));
    }

    public int numDecodings(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        sum = 0;
        numDecodingsImpl(str, n, 0);
        return sum;
    }

    int sum;

    // 回溯法
    // 变量只有一个就是字符串坐标
    // i为下一个字符的起始坐标
    public void numDecodingsImpl(char[] str, int n, int i) {
        if (i > n) {
            return;
        }
        if (i == n) {
            sum++;
            return;
        }
        if (str[i] == '0') {
            return;
        }


        // 当前字符占一个数字
        numDecodingsImpl(str, n, i + 1);

        // 当前字符占两个数字 str[i]只能为1和2，str[i+1]只能为0-6
        if (i+1 < n && isTwoNumber(str[i], str[i + 1])) {
            numDecodingsImpl(str, n, i + 2);
        }
    }

    public boolean isTwoNumber(char c1, char c2) {
        if (c1 == '1') {
            return true;
        }
        if (c1 == '2' && c2 - '0' <= 6) {
            return true;
        }
        return false;
    }

    // 状态转移表法
    // 状态转移方程为f(n) = f(n-1) + f(n-2)
    public int numDecodings1(String s) {
        int n = s.length();
        if (s.charAt(0) == '0') {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        // 定义状态空间为一维
        int[] dp = new int[n];

        // 初始化状态
        dp[0] = 1;
        if (s.charAt(1) != '0') {
            dp[1] += 1;
        }
        // 如果前两个数字在10到27之间
        if (isTwoNumber(s.charAt(0), s.charAt(1))) {
            dp[1] += 1;
        }

        for (int i = 2; i < n; i++) {
            int lastDp1 = 0;
            int lastDp2 = 0;
            if (s.charAt(i) != '0') {
                lastDp1 = dp[i - 1];
            }
            if (isTwoNumber(s.charAt(i - 1), s.charAt(i))) {
                lastDp2 = dp[i - 2];
            }
            dp[i] = lastDp1 + lastDp2;
        }
        return dp[n - 1];
    }
}
