package chapter15;

import java.util.LinkedList;
import java.util.Queue;

public class SatisfiesBSTProperty {

    /**
     * Time complexity: O(n)
     * Additional space: O(h)
     *
     * Uses implicit DFS, and can be inefficient if the left branch
     * does not hold the BST property. Can we shrink the time complexity
     * down a little?
     */
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

    /**
     * If we take a BST approach, we can save some computation time if we
     * encounter a node that doesn't hold the property.
     */
    public static boolean isBST_BFS(BSTNode tree) {
        Queue<QEntry> q = new LinkedList<>();
        QEntry entry = new QEntry(tree, Integer.MAX_VALUE, Integer.MIN_VALUE);
        q.add(entry);

        while (!q.isEmpty()) {
            QEntry item = q.poll();
            BSTNode node = item.node;

            if (node.data() > item.less || node.data() < item.more)
                return false;

            QEntry leftEntry = new QEntry(node.left(), node.data(), item.more);
            QEntry rightEntry = new QEntry(node.right(), item.less, node.data());

            if (null != node.left())
                q.add(leftEntry);
            if (null != node.right())
                q.add(rightEntry);
        }

        return true;
    }

    private static class QEntry {
        public BSTNode node;
        public int less, more;

        public QEntry(BSTNode node, int less, int more) {
            this.node = node;
            this.less = less;
            this.more = more;
        }
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

        System.out.println("Tree1: " + isBST_BFS(tree));


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

        System.out.println("Tree2: " + isBST_BFS(t_ree));
    }
}
