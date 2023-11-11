package bfs;

import java.util.ArrayList;
import java.util.List;

public class Solution94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>(100);
        if (root != null) {
            found(root, result);
        }
        return result;
    }

    public void found(TreeNode root, List<Integer> result) {
        if (root.left != null) {
            found(root.left, result);
        }
        result.add(root.val);
        if (root.right != null) {
            found(root.right, result);
        }
    }
}
