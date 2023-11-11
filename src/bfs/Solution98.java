package bfs;

public class Solution98 {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return false;
        }
        return check(root, -2147483649d, 2147483648d);
    }

    public boolean check(TreeNode root,double leftParentVal ,double rightPrentVal) {
        boolean checkLeft = root.left == null ||
                ((leftParentVal < root.left.val) && root.left.val < root.val
                        && check(root.left, leftParentVal, root.val));
        boolean checkRight = root.right == null ||
                (root.val < root.right.val && root.right.val < rightPrentVal
                        && check(root.right, root.val, rightPrentVal));
        return checkLeft && checkRight;
    }

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node7 = new TreeNode(7);
        TreeNode node6 = new TreeNode(6, node3, node7);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5= new TreeNode(5, node4, node6);
        System.out.println(new Solution98().isValidBST(node5));
//        TreeNode node3 = new TreeNode(3);
//        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(2, node1, node3);
//        System.out.println(new Solution98().isValidBST(node2));
    }
}
