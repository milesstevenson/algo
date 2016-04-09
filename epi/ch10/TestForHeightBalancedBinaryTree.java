package chapter10;

public class TestForHeightBalancedBinaryTree {

    public static boolean isTreeHeightBalanced(BinaryTreeNode<Integer> treeNode) {
        int leftHeight = traverseTreeHeight(treeNode.left);
        if (leftHeight < 0)
            return false;

        int rightHeight = traverseTreeHeight(treeNode.right);
        if (rightHeight < 0)
            return false;

        int comparedHeights = Math.abs(rightHeight - leftHeight);
        return comparedHeights <= 1;
    }

    private static int traverseTreeHeight(BinaryTreeNode<Integer> treeNode) {
        if (null == treeNode)
            return 0;

        int leftHeight = traverseTreeHeight(treeNode.left);
        int rightHeight = traverseTreeHeight(treeNode.right);

        if (Math.abs(leftHeight - rightHeight) <= 1)
            return Math.max(leftHeight, rightHeight) + 1;
        else
            return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        /*******************************
         *           (rootTreeNode1)
         *           /
         *    (subTreeNode1)
         *    /    \
         *(leaf1)  (leaf2)
         *
         *  The height of this tree is not balanced.
         *******************************/
        BinaryTreeNode<Integer> leaf1 = new BinaryTreeNode<>(1, null, null);
        BinaryTreeNode<Integer> leaf2 = new BinaryTreeNode<>(2, null, null);
        BinaryTreeNode<Integer> subTreeNode1 = new BinaryTreeNode<>(1, leaf1, leaf2);
        BinaryTreeNode<Integer> rootTreeNode1 = new BinaryTreeNode<>(1, subTreeNode1, null);

        System.out.println(isTreeHeightBalanced(rootTreeNode1));

        /*******************************
         *           (rootTreeNod2)
         *           /          \
         *    (subTreeNode2)   (subTreeNode3)
         *    /    \
         *(leaf3)  (leaf4)
         *
         *  The height of this tree is balanced.
         *******************************/
        BinaryTreeNode<Integer> leaf3 = new BinaryTreeNode<>(3, null, null);
        BinaryTreeNode<Integer> leaf4 = new BinaryTreeNode<>(4, null, null);
        BinaryTreeNode<Integer> subTreeNode2 = new BinaryTreeNode<>(2, leaf3, leaf4);
        BinaryTreeNode<Integer> subTreeNode3 = new BinaryTreeNode<>(3, null, null);
        BinaryTreeNode<Integer> rootTreeNode2 = new BinaryTreeNode<>(2, subTreeNode2, subTreeNode3);

        System.out.println(isTreeHeightBalanced(rootTreeNode2));

    }
}
