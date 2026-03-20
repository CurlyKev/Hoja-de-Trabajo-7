/**
 * Represents a key-value pair (association) for the dictionary.
 * Based on the concept of map entries from Bailey, D.A. (2003).
 * Java Structures: Data Structures in Java for the Principled Programmer.
 *
 * @param <K> Key type, must be Comparable
 * @param <V> Value type
 */
public class Association<K extends Comparable<K>, V> implements Comparable<Association<K, V>> {

    private K key;
    private V value;

    public Association(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public int compareTo(Association<K, V> other) {
        return this.key.compareTo(other.key);
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}