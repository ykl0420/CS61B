package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author ykl
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private class MyHashMapIterator implements Iterator<K> {
        private int bucketIndex;
        private Iterator<Node> bucketIterator;

        /** update the state of bucketIterator  */
        private void update() {
            while(!bucketIterator.hasNext()) {
                if(bucketIndex == M - 1) {
                    break;
                }
                bucketIterator = buckets[++bucketIndex].iterator();
            }
        }
        public MyHashMapIterator() {
            bucketIndex = 0;
            bucketIterator = buckets[0].iterator();
            update();
        }
        public boolean hasNext() {
            return bucketIterator.hasNext();
        }
        public K next() {
            Node ret = bucketIterator.next();
            update();
            return ret.key;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;

    private int initialSize = 16;
    private double maxLoad = 0.75;

    private int M = 0;
    private int N = 0;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        buckets = createTable(initialSize);
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        buckets = createTable(initialSize);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.initialSize = initialSize;
        this.maxLoad = maxLoad;
        buckets = createTable(initialSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key,value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *`
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        int oldM = M;
        M = tableSize;
        Collection<Node>[] newBuckets = new Collection[M];
        for(int i = 0; i < M; i ++) {
            newBuckets[i] = createBucket();
        }
        for(int i = 0; i < oldM; i ++) {
            for(Node x : buckets[i]) {
                newBuckets[getIndex(x.key)].add(x);
            }
        }
        return newBuckets;
    }


    /** Removes all of the mappings from this map. */
    public void clear() {
        for(int i = 0; i < M; i ++) buckets[i] = null;
        N = 0;
        M = initialSize;
        for(int i = 0; i < M; i ++) buckets[i] = createBucket();
    }

    public int getIndex(K key){
        int hashCode = key.hashCode();
        return (hashCode % M + M) % M;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        int ind = getIndex(key);
        for(Node x : buckets[ind]) {
            if(x.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        int ind = getIndex(key);
        for(Node x : buckets[ind]) {
            if(x.key.equals(key)) {
                return x.value;
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return  N;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        int ind = getIndex(key);
        for(Node x : buckets[ind]) {
            if(x.key.equals(key)) {
                x.value = value;
                return;
            }
        }
        if(N > maxLoad * M) buckets = createTable(2 * M);
        ind = getIndex(key);
        buckets[ind].add(createNode(key,value));
        N ++;
    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        Set<K> ret = new HashSet<>();
        for(K key : this) {
            ret.add(key);
        }
        return ret;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        int ind = getIndex(key);
        for(Node x : buckets[ind]) {
            if (x.key.equals(key)) {
                V ret = x.value;
                buckets[ind].remove(x);
                return ret;
            }
        }
        return null;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        int ind = getIndex(key);
        for(Node x : buckets[ind]) {
            if (x.key.equals(key) && x.value.equals(value)) {
                V ret = x.value;
                buckets[ind].remove(x);
                return ret;
            }
        }
        return null;
    }

    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }

}
