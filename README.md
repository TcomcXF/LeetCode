# LeetCode
leetcode solution

##### 问题120解法
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

##### 问题131解法
```
package dp;

import java.util.LinkedList;
import java.util.List;

// 相关问题：132
public class Solution131 {
    public static void main(String[] args) {
        Solution131 solution131 = new Solution131();
        long start = System.currentTimeMillis();
        System.out.println(solution131.partitionPro("aab"));
        System.out.println(System.currentTimeMillis() - start);
    }

    // 动态规划法：
    // 假设s(0,i)表示s的子串，其坐标从0到i
    // 如果字符s[i]无法与其他字符构成回文子串，那么s(0,i)的分割方式为在s(0,i-1)的所有分割方式后面加上单字符子串s[i]，即f(0,i) = f(0,i-1)
    // 如果字符s[i]能和前面的字符构成长度为k的回文子串，那么除了s(0,i-1)的所有分割方式加s[i]外，还有s(0,i-k)的所有分割方式加s[i-k+1,i]
    // 如果k只有一个解，则状态转移方程为：f(0,i) = f(0,i-1) + f(0,i-k)
    // 如果k有多个解，则状态转移方程为：f(0,i) = f(0,i-1) + f(0,i-k1) + ... + f(0,i-kn) ,i>=k>1
    // 所以最终的状态转移方程为：f(0,i) = f(0,i-k1) + ... + f(0,i-kn) ,i>=k>=1，k为字符s[i]做最后一个字符时构成的回文子串长度
    public List<List<String>> partition(String s) {
        int len = s.length();
        // 设置状态空间,dp[i+1] = f(0,i)
        List<List<String>>[] dp = new LinkedList[len + 1];
        // 初始化状态
        for (int i = 0; i < len + 1; i++) {
            dp[i] = new LinkedList<>();
        }
        dp[0].add(new LinkedList<>());

        // 由于s[i]做最后一个字符时构成的回文子串长度k是可以提前计算的，所以增加一个状态空间保存每个字符的k值
        // kdp[i][k]中i为字符坐标，k为以字符s[i]为最后一个字符时的回文子串长度，kdp[i][k]的值表示此回文子串是否存在
        boolean[][] kdp = new boolean[len][len + 1];
        // 初始化k值为1的状态
        for (int i = 0; i < len; i++) {
            kdp[i][1] = true;
        }
        // 求每个字符的k值
        for (int i = 1; i < len; i++) {
            for (int k = 2; k <= i + 1; k++) {
                kdp[i][k] = isPalindromeSubString(s, i - k + 1, i);
            }
        }

        // 字符坐标=i-1
        for (int i = 1; i < len + 1; i++) {
            for (int k = 1; k < kdp[i - 1].length; k++) {
                if (kdp[i - 1][k]) {
                    // dp[i] += dp[i - k];
                    List<List<String>> temp = cloneList(dp[i - k]);
                    batchAdd(temp, s.substring(i - k, i));
                    dp[i].addAll(temp);
                }
            }
        }

        return dp[len];
    }

    public List<List<String>> cloneList(List<List<String>> list) {
        List<List<String>> newList = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            List<String> subList = new LinkedList<>();
            subList.addAll(list.get(i));
            newList.add(subList);
        }
        return newList;
    }

    public void batchAdd(List<List<String>> list, String s) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).add(s);
        }
    }

    public boolean isPalindromeSubString(String s, int start, int end) {
        while (s.charAt(start++) == s.charAt(end--)) {
            if (start > end) {
                return true;
            }
        }
        return false;
    }

    public List<List<String>> partitionPro(String s) {
        int len = s.length();
        // preDp[i][j] = true 表示s(i,j)是回文子串
        boolean[][] preDp = new boolean[len][len];
        // 状态转移方程：preDp[i][j] = s[i] == s[j] && preDp[i+1][j-1]
        // 从状态转移方程可以看出，i需要从大到小，j需要从小到大
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    preDp[i][j] = j < i + 2 || preDp[i + 1][j - 1];
                }
            }
        }

        // 设置状态空间,dp[i+1] = f(0,i)
        List<List<String>>[] dp = new LinkedList[len + 1];
        // 初始化状态
        for (int i = 0; i < len + 1; i++) {
            dp[i] = new LinkedList<>();
        }
        dp[0].add(new LinkedList<>());

        // 字符坐标=i-1
        for (int i = 1; i < len + 1; i++) {
            for (int k = 1; k < i + 1; k++) {
                if (preDp[i - k][i - 1]) {
                    List<List<String>> temp = cloneList(dp[i - k]);
                    batchAdd(temp, s.substring(i - k, i));
                    dp[i].addAll(temp);
                }
            }
        }

        return dp[len];
    }
}

```
##### 问题132解法
```
package dp;

// 相关问题：131
public class Solution132 {
    public static void main(String[] args) {
        Solution132 solution132 = new Solution132();
        long start = System.currentTimeMillis();
        System.out.println(solution132.minCutPro("aab"));
        System.out.println(System.currentTimeMillis() - start);
    }

    // 动态规划法：
    // 假设s(0,i)表示s的子串，其坐标从0到i
    // 如果字符s[i]无法与其他字符构成回文子串，那么s(0,i)的最短分割方式为在s(0,i-1)的最短分割方式后面加上单字符子串s[i]，即f(0,i) = f(0,i-1) + 1
    // 如果字符s[i]能和前面的字符构成长度为k的回文子串，那么除了s(0,i-1)的最短分割方式加s[i]外，还有s(0,i-k)的最短分割方式加s[i-k+1,i]
    // 如果k有两个或以上的解，则状态转移方程为：f(0,i) = min(f(0,i-k1), ... , f(0,i-kn)) + 1,i>=k>1
    // 所以最终的状态转移方程为：f(0,i) = min(f(0,i-k1), ... , f(0,i-kn)) + 1,i>=k>1，k为字符s[i]做最后一个字符时构成的回文子串长度
    public int minCut(String s) {
        int len = s.length();
        // 设置状态空间,dp[i+1] = f(0,i)
        int[] dp = new int[len + 1];
        // 初始化状态
        for (int i = 0; i < len + 1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = -1;

        // 字符坐标=i-1，k为回文子串长度
        for (int i = 1; i < len + 1; i++) {
            for (int k = i; k > 0; k--) {
                if (isPalindromeSubString(s, i - k, i - 1)) {
                    dp[i] = Math.min(dp[i], dp[i - k] + 1);
                }
            }
        }

        return dp[len];
    }

    // 每次用到时再判断是否回文，会导致求回文的时间复杂度为O(n^3)，导致超时
    public boolean isPalindromeSubString(String s, int start, int end) {
        while (s.charAt(start++) == s.charAt(end--)) {
            if (start > end) {
                return true;
            }
        }
        return false;
    }

    // 通过动态规划将求s(i,j)是否回文的时间复杂度降为O(n^2)
    public int minCutPro(String s) {
        int len = s.length();
        // preDp[i][j] = true 表示s(i,j)是回文子串
        boolean[][] preDp = new boolean[len][len];
        // 状态转移方程：preDp[i][j] = s[i] == s[j] && preDp[i+1][j-1]
        // 从状态转移方程可以看出，i需要从大到小，j需要从小到大
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    preDp[i][j] = j < i + 2 || preDp[i + 1][j - 1];
                }
            }
        }


        // 设置状态空间,dp[i+1] = f(0,i)
        int[] dp = new int[len + 1];
        // 初始化状态
        for (int i = 0; i < len + 1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = -1;

        // 字符坐标=i-1，k为回文子串长度
        for (int i = 1; i < len + 1; i++) {
            for (int k = 1; k < i + 1; k++) {
                if (preDp[i - k][i - 1]) {
                    dp[i] = Math.min(dp[i], dp[i - k] + 1);
                }
            }
        }

        return dp[len];
    }
}
```
##### 问题139解法
```
package dp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// 关联问题：140
public class Solution139 {
    public static void main(String[] args) {
        Solution139 solution139 = new Solution139();
        long start = System.currentTimeMillis();
        List<String> case1 = new LinkedList<>();
        case1.add("aaaa");
        case1.add("aaa");
        System.out.println(solution139.wordBreak("aaaaaaa", case1));
        System.out.println(System.currentTimeMillis() - start);
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        int len = wordDict.size();
        // 预处理，需要先将字典中的属于s子串的字符串过滤出来，然后按首字母分类好
        // 定义map，键为a-z，值为字典构成的List
        HashMap<Character, List<String>> dictMap = new HashMap<>();
        String tempString;
        for (int i = 0; i < len; i++) {
            tempString = wordDict.get(i);
            if (s.contains(tempString)) {
                dictMap.computeIfAbsent(tempString.charAt(0), k -> new LinkedList<>());
                dictMap.get(tempString.charAt(0)).add(tempString);
            }
        }

        // 定义状态空间，dp[i]表示s(0, i)能否由字典构成
        int sLen = s.length();
        boolean[] dp = new boolean[sLen + 1];
        // 初始化状态
        dp[0] = true;

        List<String> tempDict = null;
        for (int i = 1; i < sLen + 1; i++) {
            if (!dp[i - 1]) {
                continue;
            }
            tempDict = dictMap.get(s.charAt(i - 1));
            if (tempDict == null) {
                continue;
            }

            for (int j = 0; j < tempDict.size(); j++) {
                String sm = tempDict.get(j);
                int next = i - 1 + sm.length();
                if (next <= sLen && sm.equals(s.substring(i - 1, next))) {
                    dp[next] = dp[i - 1] || dp[next];
                }
            }
        }

        return dp[sLen];
    }

}
```
##### 问题140解法
```
package dp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

// 关联问题：139
public class Solution140 {
    public static void main(String[] args) {
        Solution140 solution140 = new Solution140();
        long start = System.currentTimeMillis();
        List<String> case1 = new LinkedList<>();
        case1.add("aaaa");
        case1.add("aaa");
        System.out.println(solution140.wordBreak("aaaaaaa", case1));
        System.out.println(System.currentTimeMillis() - start);
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        int len = wordDict.size();
        // 预处理，需要先将字典中的属于s子串的字符串过滤出来，然后按首字母分类好
        // 定义map，键为a-z，值为字典构成的List
        HashMap<Character, List<String>> dictMap = new HashMap<>();
        String tempString;
        for (int i = 0; i < len; i++) {
            tempString = wordDict.get(i);
            if (s.contains(tempString)) {
                dictMap.computeIfAbsent(tempString.charAt(0), k -> new LinkedList<>());
                dictMap.get(tempString.charAt(0)).add(tempString);
            }
        }

        // 定义状态空间，dp[i]表示s(0, i)能否由字典构成
        int sLen = s.length();
        List<StringBuilder>[] dp = new LinkedList[sLen + 1];
        // 初始化状态
        dp[0] = new LinkedList<>();
        dp[0].add(new StringBuilder());

        List<String> tempDict = null;
        for (int i = 1; i < sLen + 1; i++) {
            if (dp[i - 1] == null) {
                continue;
            }
            tempDict = dictMap.get(s.charAt(i - 1));
            if (tempDict == null) {
                continue;
            }

            for (int j = 0; j < tempDict.size(); j++) {
                String sm = tempDict.get(j);
                int next = i - 1 + sm.length();
                if (next <= sLen && sm.equals(s.substring(i - 1, next))) {
                    dp[next] = cloneList(dp[i - 1], dp[next], sm);
                }
            }
        }
        if (dp[sLen] == null) {
            return new LinkedList<>();
        }
        return dp[sLen].stream().map(StringBuilder::toString).collect(Collectors.toList());
//        List<String> result  = new LinkedList<>();
//        for (int i = 0; i < dp[sLen].size(); i++) {
//            result.add(dp[sLen].get(i).toString());
//        }
//        return result;
    }

    public List<StringBuilder> cloneList(List<StringBuilder> oldList, List<StringBuilder> newList, String newWord) {
        if (newList == null) {
            newList = new LinkedList<>();
        }
        for (int i = 0; i < oldList.size(); i++) {
            StringBuilder newString = new StringBuilder();
            if (oldList.get(i).length() > 0) {
                newString.append(oldList.get(i));
                newString.append(" ");
            }
            newString.append(newWord);
            newList.add(newString);
        }
        return newList;
    }
}

```
##### 问题152解法
```
package dp;

public class Solution152 {
    public static void main(String[] args) {
        Solution152 solution152 = new Solution152();
        long start = System.currentTimeMillis();
        System.out.println(solution152.maxProductPro(new int[]{2, 3, -2, 0, -1}));
        System.out.println(solution152.maxProductPro(new int[]{-2, 0, -1}));
        System.out.println(System.currentTimeMillis() - start);
    }

    public int maxProduct(int[] nums) {
        // 假设 从nums[i] 到nums[j]的乘积最大，则可以从0开始找i，然后从i开始找j
        int maxProduct = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int currentProduct = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                currentProduct = currentProduct * nums[j];
                maxProduct = Math.max(maxProduct, currentProduct);
            }
        }
        return maxProduct;
    }

    // 优化时间复杂度
    // 首先想到，如果数组中没有0，那么n个数相乘，其结果f(n)的绝对值会越来越大，这时候，需要求的乘积最大值就取决于f(n)的符号，如果是正数直接返回就行
    // 如果是负数，那么就需要少乘一个负数，让结果变成正数，这个负数只能取左边第一个负数和右边第一个负数中绝对值小的那个
    // 所以设置一个currentProduct分别从左边开始连乘和从右边开始连乘，用maxProduct记录currentProduct的最大值即可
    // 如果数组中有0，那么0就需要排除，当currentProduct遇到0时，currentProduct就需要从下一个不为0的数开始重新累积连乘
    public int maxProductPro(int[] nums) {
        int maxProduct = Integer.MIN_VALUE;
        int currentProduct = 0;
        for (int i = 0; i < nums.length; i++) {
            if (currentProduct == 0) {
                currentProduct = nums[i];
            } else {
                currentProduct = currentProduct * nums[i];
            }
            maxProduct = Math.max(maxProduct, currentProduct);
        }
        currentProduct = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (currentProduct == 0) {
                currentProduct = nums[i];
            } else {
                currentProduct = currentProduct * nums[i];
            }
            maxProduct = Math.max(maxProduct, currentProduct);
        }
        return maxProduct;
    }
}
```
##### 问题198解法
```
package dp;

// 关联问题：213
public class Solution198 {
    public static void main(String[] args) {
        Solution198 solution198 = new Solution198();
        long start = System.currentTimeMillis();
        System.out.println(solution198.robPro(new int[]{1, 2, 3, 1}));
        System.out.println(solution198.robPro(new int[]{2, 7, 9, 3, 1}));
        System.out.println(solution198.robPro(new int[]{2, 1, 1, 2}));
        System.out.println(System.currentTimeMillis() - start);
    }

    // 直接想到的方法是求奇数和与偶数和的最大值，但是遇到[2,1,1,2]时，求得的结果就不对了
    public int rob(int[] nums) {
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                sum1 += nums[i];
            } else {
                sum2 += nums[i];
            }
        }
        return Math.max(sum1, sum2);
    }

    // 还是考虑动态规划
    // 状态转移方程：f(i) = Math(f(i-2) + nums(i), f(i-1))
    public int robPro(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        // 设置状态空间 dp[i] 标识从第0家到第i家能偷到的最大值
        int[] dp = new int[len];
        // 初始化状态
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[len - 1];
    }
}
```
##### 问题213解法
```
package dp;

// 关联问题：198
public class Solution213 {
    public static void main(String[] args) {
        Solution213 solution213 = new Solution213();
        long start = System.currentTimeMillis();
        System.out.println(solution213.rob(new int[]{1, 2, 3, 1}));
        System.out.println(solution213.rob(new int[]{2, 7, 9, 3, 1}));
        System.out.println(solution213.rob(new int[]{2, 1, 1, 10}));
        System.out.println(solution213.rob(new int[]{2, 3, 2}));
        System.out.println(solution213.rob(new int[]{1, 2, 3}));
        System.out.println(System.currentTimeMillis() - start);
    }

    // 还是考虑动态规划，在问题198上升级一下，需要在状态空间中保存起始位置
    // 状态转移方程：f(i) = Math(f(i-2) + nums(i), f(i-1))
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        if (len == 2) {
            return Math.max(nums[0], nums[1]);
        }

        if (len == 3) {
            return Math.max(Math.max(nums[0], nums[1]), nums[2]);
        }
        // 设置状态空间 dp[i] 标识从第0家到第i家能偷到的最大值
        // 先计算从0开始偷到len-2的最大值
        int[] dp0 = new int[len];
        // 初始化状态
        dp0[0] = nums[0];
        dp0[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < len; i++) {
            dp0[i] = Math.max(dp0[i - 2] + nums[i], dp0[i - 1]);
        }

        // 再计算从1偷到len-1的最大值
        int[] dp1 = new int[len];
        // 初始化状态
        dp1[1] = nums[1];
        dp1[2] = Math.max(nums[1], nums[2]);

        for (int i = 3; i < len; i++) {
            dp1[i] = Math.max(dp1[i - 2] + nums[i], dp1[i - 1]);
        }

        return Math.max(dp0[len - 2], dp1[len - 1]);
    }
}
```
