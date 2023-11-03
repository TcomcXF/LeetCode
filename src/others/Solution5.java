package others;

public class Solution5 {
    // 中心扩展算法
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        int maxLeft = 0;
        int maxRight = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = getMaxLength(s, i, i);
            int len2 = getMaxLength(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxRight - maxLeft) {
                maxLeft = i - (len - 1) / 2;
                maxRight = i + len / 2;
            }
        }
        return s.substring(maxLeft, maxRight + 1);
    }

    /**
     * 检查一当前left为左节点，right为右节点能找到的最长回文长度
     *
     * @param s     字符串
     * @param left  左边界
     * @param right 右边界
     * @return 最大回文字串长度
     */
    public int getMaxLength(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution5().longestPalindrome("a"));
    }
}

