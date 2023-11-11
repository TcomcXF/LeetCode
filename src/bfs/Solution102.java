package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        ArrayList<Integer> currentLevel = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<TreeNode> nextQueue = new LinkedList<>();
        queue.add(root);
        while(queue.size() > 0) {
            if (queue.getFirst() != null) {
                currentLevel.add(queue.getFirst().val);
                nextQueue.add(queue.getFirst().left);
                nextQueue.add(queue.getFirst().right);
            }
            queue.removeFirst();
            if (queue.size() == 0 && currentLevel.size() > 0) {
                result.add(currentLevel);
                queue = nextQueue;
                nextQueue = new LinkedList<>();
                currentLevel = new ArrayList<>();
            }
        }
        return result;
    }
}
