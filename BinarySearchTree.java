/**
 * Binary Search Tree that stores Association<String, String> entries.
 * Keys are English words (stored in lowercase), values are Spanish translations.
 *
 * If a duplicate key is inserted, the existing value is kept (first-one-wins),
 * since the dictionary file contains many repeated entries.
 */
public class BinarySearchTree {

    private BinaryTree<Association<String, String>> root;

    public BinarySearchTree() {
        root = null;
    }

    /**
     * Inserts a new key-value association into the BST.
     * If the key already exists, the value is NOT overwritten (first-one-wins).
     *
     * @param key   English word (will be stored in lowercase)
     * @param value Spanish translation
     */
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

        int cmp = assoc.compareTo(node.getValue());

        if (cmp < 0) {
            node.setLeft(insertRec(node.getLeft(), assoc));
        } else if (cmp > 0) {
            node.setRight(insertRec(node.getRight(), assoc));
        }
        // cmp == 0: duplicate key, keep existing value (do nothing)

        return node;
    }

    /**
     * Searches for a key in the BST.
     *
     * @param key English word to look up (case-insensitive)
     * @return Spanish translation, or null if not found
     */
    public String search(String key) {
        return searchRec(root, key.toLowerCase());
    }

    private String searchRec(BinaryTree<Association<String, String>> node, String key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.getValue().getKey());

        if (cmp == 0) return node.getValue().getValue();
        if (cmp < 0)  return searchRec(node.getLeft(), key);
        else          return searchRec(node.getRight(), key);
    }

    /**
     * Prints all associations in ascending order by English key (in-order traversal).
     */
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