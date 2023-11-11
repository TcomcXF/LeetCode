package dp;

import java.util.LinkedList;
import java.util.List;

public class Solution22 {
    public static void main(String[] args) {
        Solution22 solution22 = new Solution22();
        List<String> result = solution22.generateParenthesis(5);
        System.out.println(result.toString());
        System.out.println("k=" + solution22.k);
    }

    public List<String> generateParenthesis(int n) {

        // 回溯法
        // 左括号1<=i<=n,右括号1<=j<=n,且i>=j
        // 第一次必须是左括号，然后后面每一次都可以选择左括号或者右括号
        selectParenthesis(n, 1, 0, "(");
        return result;
    }

    List<String> result = new LinkedList<>();
    int k = 0;

    public void selectParenthesis(int n, int i, int j, String str) {
        // 设置递归出口
        if (i > n || j > n || j > i) {
            return;
        }
        if (i == n && j == n) {
            k++;
            result.add(str);
        }

        selectParenthesis(n, i + 1, j, str + "(");
        selectParenthesis(n, i, j + 1, str + ")");
    }
}
