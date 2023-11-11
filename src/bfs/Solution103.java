package bfs;

import java.util.*;

public class Solution103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<Integer> currentLevel = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<TreeNode> nextQueue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (queue.size() > 0) {
            if (queue.getFirst() != null) {
                if (level % 2 == 0) {
                    currentLevel.addLast(queue.getFirst().val);
                } else {
                    currentLevel.addFirst(queue.getFirst().val);
                }
                nextQueue.add(queue.getFirst().left);
                nextQueue.add(queue.getFirst().right);
            }
            queue.removeFirst();

            if (queue.size() == 0 && currentLevel.size() > 0) {
                result.add(currentLevel);
                System.out.println(currentLevel);
                level++;
                queue = nextQueue;
                nextQueue = new LinkedList<>();
                currentLevel = new LinkedList<>();
            }

        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        TreeNode node20 = new TreeNode(20, node15, node7);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node9 = new TreeNode(9, node1, node2);
        TreeNode node3 = new TreeNode(3,node9, node20);
        new Solution103().zigzagLevelOrder(node3);
    }
}
