import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BinarySearchTree insert and search methods.
 */
public class BinarySearchTreeTest {

    private BinarySearchTree bst;

    @BeforeEach
    public void setUp() {
        bst = new BinarySearchTree();
    }

    // ── INSERT TESTS ──────────────────────────────────────────────────────────

    @Test
    public void testInsertAndSearchSingleElement() {
        bst.insert("house", "casa");
        assertEquals("casa", bst.search("house"),
                "Inserting one element and searching it should return its translation.");
    }

    @Test
    public void testInsertMultipleElements() {
        bst.insert("dog", "perro");
        bst.insert("cat", "gato");
        bst.insert("bird", "pájaro");

        assertEquals("perro",   bst.search("dog"));
        assertEquals("gato",    bst.search("cat"));
        assertEquals("pájaro",  bst.search("bird"));
    }

    @Test
    public void testInsertIsCaseInsensitive() {
        bst.insert("HOUSE", "casa");
        // Keys are stored lowercase; searching with any case should work
        assertEquals("casa", bst.search("house"),
                "Keys inserted in uppercase should be stored and found in lowercase.");
    }

    @Test
    public void testInsertDuplicateKeyKeepsFirstValue() {
        bst.insert("bank", "banco");
        bst.insert("bank", "orilla");   // duplicate — should be ignored
        assertEquals("banco", bst.search("bank"),
                "Inserting a duplicate key should keep the first value.");
    }

    @Test
    public void testInsertLeftAndRightChildren() {
        // "house" is root; "dog" goes left, "woman" goes right
        bst.insert("house", "casa");
        bst.insert("dog",   "perro");
        bst.insert("woman", "mujer");

        assertEquals("casa",  bst.search("house"));
        assertEquals("perro", bst.search("dog"));
        assertEquals("mujer", bst.search("woman"));
    }

    // ── SEARCH TESTS ──────────────────────────────────────────────────────────

    @Test
    public void testSearchReturnsNullWhenEmpty() {
        assertNull(bst.search("hello"),
                "Searching an empty tree should return null.");
    }

    @Test
    public void testSearchReturnsNullForUnknownWord() {
        bst.insert("dog", "perro");
        assertNull(bst.search("cat"),
                "Searching for a word not in the tree should return null.");
    }

    @Test
    public void testSearchIsCaseInsensitive() {
        bst.insert("sun", "sol");
        assertEquals("sol", bst.search("SUN"),
                "Search should be case-insensitive.");
        assertEquals("sol", bst.search("Sun"));
    }

    @Test
    public void testSearchRootElement() {
        bst.insert("house", "casa");
        bst.insert("dog",   "perro");
        bst.insert("woman", "mujer");
        assertEquals("casa", bst.search("house"),
                "Root element should always be found.");
    }

    @Test
    public void testSearchDeepNode() {
        // Build a deeper tree
        bst.insert("house",    "casa");
        bst.insert("dog",      "perro");
        bst.insert("woman",    "mujer");
        bst.insert("homework", "tarea");
        bst.insert("town",     "pueblo");
        bst.insert("yes",      "sí");

        assertEquals("tarea",  bst.search("homework"),
                "Deep left node should be found.");
        assertEquals("pueblo", bst.search("town"),
                "Deep right node should be found.");
        assertEquals("sí",     bst.search("yes"),
                "Rightmost node should be found.");
    }

    @Test
    public void testSearchAfterManyInserts() {
        String[] keys   = {"apple","banana","cherry","date","elderberry"};
        String[] values = {"manzana","plátano","cereza","dátil","saúco"};

        for (int i = 0; i < keys.length; i++) {
            bst.insert(keys[i], values[i]);
        }

        for (int i = 0; i < keys.length; i++) {
            assertEquals(values[i], bst.search(keys[i]),
                    "All inserted words should be retrievable.");
        }
    }
}