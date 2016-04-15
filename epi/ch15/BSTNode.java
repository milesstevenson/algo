package chapter15;

public class BSTNode {
    private Integer data;
    private BSTNode left, right;

    public  BSTNode(Integer data, BSTNode left, BSTNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Integer data() { return data; }
    public BSTNode left() { return left; }
    public BSTNode right() { return right; }
}