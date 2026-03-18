public class BinarySearchTree {

    private BinaryTree<Association<String, String>> root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(String key, String value) {
        Association<String, String> assoc = new Association<>(key.toLowerCase(), value);
        root = insertRec(root, assoc);
    }

    private BinaryTree<Association<String, String>> insertRec(
            BinaryTree<Association<String, String>> node,
            Association<String, String> assoc) {

        if (node == null) {
            return new BinaryTree<>(assoc);
        }

        if (assoc.compareTo(node.getValue()) < 0) {
            node.setLeft(insertRec(node.getLeft(), assoc));

        } else if (assoc.compareTo(node.getValue()) > 0) {
            node.setRight(insertRec(node.getRight(), assoc));
        }

        return node;
    }

    public String search(String key) {
        return searchRec(root, key.toLowerCase());
    }

    private String searchRec(BinaryTree<Association<String, String>> node, String key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.getValue().getKey());

        if (cmp == 0) return node.getValue().getValue();
        if (cmp < 0) return searchRec(node.getLeft(), key);
        else return searchRec(node.getRight(), key);
    }

    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(BinaryTree<Association<String, String>> node) {
        if (node != null) {
            inorderRec(node.getLeft());
            System.out.println(node.getValue());
            inorderRec(node.getRight());
        }
    }
}
