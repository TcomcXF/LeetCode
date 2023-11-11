package dp;

import leetCode.bfs.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class Solution95 {
    public static void main(String[] args) {
        Solution95 solution95 = new Solution95();
        System.out.println(solution95.generateTrees(3));
        System.out.println(solution95.generateTrees1(3));

    }

    // 递归解法
    // 观察题目中的二叉树图，可以获取如下规律：
    // 节点数为n时，树的种类由 null-1-右子树(2,n), 1-2-右子树(3,n), 一直加到 左子树(1,n-1)-n-null
    // 由此得出需要一个求节点i到j的二叉树递归方法
    public List<TreeNode> generateTrees(int n) {
        return generateTreesImpl(1, n);
    }

    // 计算从start到end能生成的二叉树
    public List<TreeNode> generateTreesImpl(int start, int end) {
        List<TreeNode> result = new LinkedList<>();
        if (start > end) {
            result.add(null);
            return result;
        }
        if (start == end) {
            TreeNode root = new TreeNode(start);
            result.add(root);
            return result;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTree = generateTreesImpl(start, i - 1);
            List<TreeNode> rightTree = generateTreesImpl(i + 1, end);
            for (TreeNode right : rightTree) {
                for (TreeNode left : leftTree) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }

    // 上面的递归解法中包含了很多重复的操作，可以通过动态规划来优化
    // 节点数固定时，能生成的树的种类也是固定的，如树(1,n)和树(2,n+1)的种类是一样多的，只是每个节点中的数字不同
    // 所以我们可以优化这部分的重复操作，创建一个复制树方法，通过给每个节点的值+1的方式，将树（1-n）转换为树（2-n+1）
    public TreeNode cloneTree(TreeNode oldTree, int offset) {
        if (oldTree == null) {
            return null;
        }
        TreeNode newTree = new TreeNode(oldTree.val + offset);
        if (oldTree.left != null) {
            newTree.left = cloneTree(oldTree.left, offset);
        }
        if (oldTree.right != null) {
            newTree.right = cloneTree(oldTree.right, offset);
        }
        return newTree;
    }

    // 动态规划解法
    public List<TreeNode> generateTrees1(int n) {
        // 定义状态空间,坐标表示树中的节点数量i，值存放不同种类树的集合
        LinkedList[] dp = new LinkedList[n + 1];

        // 初始化
        LinkedList<TreeNode> tree0 = new LinkedList<>();
        tree0.add(null);
        dp[0] = tree0;

        for (int i = 1; i <= n; i++) {
            LinkedList<TreeNode> tree = new LinkedList<>();
            for (int j = 1; j <= i; j++) {
                LinkedList<TreeNode> leftTree = dp[j-1];
                LinkedList<TreeNode> rightTree = dp[i-j];
                for (TreeNode right : rightTree) {
                    for (TreeNode left : leftTree) {
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        root.right = cloneTree(right, j);
                        tree.add(root);
                    }
                }

            }
            dp[i] = tree;
        }

        return dp[n];
    }
}
