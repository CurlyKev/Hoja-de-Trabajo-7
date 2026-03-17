public class BinaryTree<E> {

    protected E value;
    protected BinaryTree<E> left;
    protected BinaryTree<E> right;

    public BinaryTree(E value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public E getValue() { return value; }
    public BinaryTree<E> getLeft() { return left; }
    public BinaryTree<E> getRight() { return right; }

    public void setLeft(BinaryTree<E> left) { this.left = left; }
    public void setRight(BinaryTree<E> right) { this.right = right; }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}