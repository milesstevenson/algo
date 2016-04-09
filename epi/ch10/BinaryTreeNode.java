package chapter10;

public class BinaryTreeNode<T> {
    public final T data;
    public final BinaryTreeNode<T> left, right;

    public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
