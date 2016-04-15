package chapter15;

public class SatisfiesBSTProperty {

    public static boolean isBST(BSTNode tree) {
        return checkProperty(tree, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private static boolean checkProperty(BSTNode tree, int less, int more) {
        if (null == tree)
            return true;
        if (tree.data() > less || tree.data() < more)
            return false;

        boolean leftVerdict = checkProperty(tree.left(), tree.data(), more);
        boolean rightVerdict = checkProperty(tree.right(), less, tree.data());

        if (leftVerdict && rightVerdict)
            return true;

        return false;
    }

    public static void main(String[] args) {

        /**
         * BST:
         *
         *      10
         *     /  \
         *    8   12
         *   /
         * 5
         */
        BSTNode llsub = new BSTNode(5, null, null);
        BSTNode rsub = new BSTNode(12, null, null);
        BSTNode lsub = new BSTNode(8, llsub, null);
        BSTNode tree = new BSTNode(10, lsub, rsub);

        System.out.println("Tree1: " + isBST(tree));


        /**
         * NOT A BST:
         *
         *      10
         *     /  \
         *    8   12
         *   /
         * 10
         */
        BSTNode ll_sub = new BSTNode(10, null, null);
        BSTNode r_sub = new BSTNode(12, null, null);
        BSTNode l_sub = new BSTNode(8, ll_sub, null);
        BSTNode t_ree = new BSTNode(10, l_sub, r_sub);

        System.out.println("Tree2: " + isBST(t_ree));
    }
}
