package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return levelOrder(this).toString();
    }

    public List<Integer> levelOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(queue.size() > 0) {
            TreeNode currentNode = queue.getFirst();
            if (currentNode != null) {
                result.add(currentNode.val);
                if (currentNode.left != null || currentNode.right != null) {
                    queue.add(currentNode.left);
                    queue.add(currentNode.right);
                }
            } else {
                result.add(null);
            }
            queue.removeFirst();
        }
        return result;
    }
}
