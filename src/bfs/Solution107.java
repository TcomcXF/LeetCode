package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> tree = new LinkedList<>();
        if (root == null || root.val < -1000 || root.val > 1000) {
            return tree;
        }
        List<Integer> currentTreeLevel;
        LinkedList<TreeNode> currentLevel = new LinkedList<>();
        LinkedList<TreeNode> nextLevel = new LinkedList<>();
        nextLevel.add(root);
        while (nextLevel.size() > 0) {
            LinkedList<TreeNode> temp = currentLevel;
            currentLevel = nextLevel;
            nextLevel = temp;

            currentTreeLevel = new ArrayList<>();
            TreeNode currentNode = null;
            while (currentLevel.size() > 0) {
                currentNode = currentLevel.removeFirst();
                currentTreeLevel.add(currentNode.val);
                if (currentNode.left != null) {
                    nextLevel.addLast(currentNode.left);
                }
                if (currentNode.right != null) {
                    nextLevel.addLast(currentNode.right);
                }
            }
            tree.addFirst(currentTreeLevel);
        }
        return tree;
    }

    public static void main(String[] args) {
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        TreeNode node20 = new TreeNode(20, node15, node7);
        TreeNode node9 = new TreeNode(9);
        TreeNode node3 = new TreeNode(3, node9, node20);
        List<List<Integer>> tree = new Solution107().levelOrderBottom(node3);
        System.out.println(tree);
    }
}


