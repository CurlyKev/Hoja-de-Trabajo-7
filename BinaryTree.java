/**
 * Generic Binary Tree node class.
 * Based on the structure described in:
 * Bailey, D.A. (2003). Java Structures: Data Structures in Java
 * for the Principled Programmer. McGraw-Hill.
 *
 * @param <E> Element type stored in each node
 */
public class BinaryTree<E> {

    protected E value;
    protected BinaryTree<E> left;
    protected BinaryTree<E> right;

    public BinaryTree(E value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public BinaryTree<E> getLeft() {
        return left;
    }

    public BinaryTree<E> getRight() {
        return right;
    }

    public void setLeft(BinaryTree<E> left) {
        this.left = left;
    }

    public void setRight(BinaryTree<E> right) {
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}