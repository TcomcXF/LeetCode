package bfs;


import java.util.LinkedList;

public class Solution100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        LinkedList<TreeNode> queueP = new LinkedList<>();
        LinkedList<TreeNode> queueQ = new LinkedList<>();
        queueP.add(p);
        queueQ.add(q);
        while (queueP.size() > 0 || queueQ.size() > 0) {
            TreeNode currentP = queueP.getFirst();
            TreeNode currentQ = queueQ.getFirst();
            if (currentP == null && currentQ == null) {
                queueP.removeFirst();
                queueQ.removeFirst();
                continue;
            }
            if (currentQ == null || currentP == null ) {
                return false;
            }
            if (currentP.val != currentQ.val) {
                return false;
            }
            queueP.add(currentP.left);
            queueP.add(currentP.right);
            queueQ.add(currentQ.left);
            queueQ.add(currentQ.right);
            queueP.removeFirst();
            queueQ.removeFirst();
        }
        return true;
    }
}
