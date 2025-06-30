package bstmap;

import java.util.Stack;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.StreamSupport;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {

	private Node root;

	private V removedValue;

	private final Node EMPTY_NODE;

	private int size;

	private class Node {
		K key;
		V value;
		Node left, right;
		Node() {
			key = null;
			value = null;
			left = null;
			right = null;
		}
		Node(K k,V v,Node l,Node r) {
			key = k;
			value = v;
			left = l;
			right = r;
		}
	}

	private class BSTMapIterator implements Iterator<K>  {
		private final Stack<Node> stack;
		BSTMapIterator() {
			stack = new Stack<>();
			Node u = root;
			while (u != null) {
				stack.push(u);
				u = u.left;
			}
		}
		public boolean hasNext() {
			return !stack.empty();
		}
		public K next() {
			Node u = stack.pop();
			K nextKey = u.key;
			u = u.right;
			while (u != null) {
				stack.push(u);
				u = u.left;
			}
			return nextKey;
		}
	}

	public Iterator<K> iterator() {
		return new BSTMapIterator();
	}

	BSTMap() {
		root = null;
		EMPTY_NODE = new Node();
	}

	/** Removes all of the mappings from this map. */
	public void clear() {
		root = null;
		size = 0;
	}

	private Node access(Node u, K key) {
		if (u == null) {
			return EMPTY_NODE;
		}
		int cmp = key.compareTo(u.key);
		if (cmp == 0) {
			return u;
		}
		else if (cmp < 0) {
			return access(u.left, key);
		}
		else {
			return access(u.right, key);
		}
	}

	/* Returns true if this map contains a mapping for the specified key. */
	public boolean containsKey(K key) {
		return access(root, key) != EMPTY_NODE;
	}

	/* Returns the value to which the specified key is mapped, or null if this
	 * map contains no mapping for the key.
	 */
	public V get(K key) {
		return access(root, key).value;
	}

	/* Returns the number of key-value mappings in this map. */
	public int size() {
		return size;
	}

	private Node put(Node u, K key, V value) {
		if (u == null) {
			Node newNode = new Node(key,value,null,null);
			size ++;
			return newNode;
		}
		int cmp = key.compareTo(u.key);
		if (cmp == 0) {
			u.value = value;
		} else if (cmp < 0) {
			u.left = put(u.left,key,value);
		} else {
			u.right = put(u.right,key,value);
		}
		return u;
	}

	private void print(Node u) {
		if (u == null) {
			return;
		}
		print(u.left);
		System.out.print(u.key + ", ");
		print(u.right);
	}

	private void print() {
		System.out.print("BST Keys List: [ ");
		print(root);
		System.out.println("]\n");
		System.out.println("Size: " + size());
	}

	/* Associates the specified value with the specified key in this map. */
	public void put(K key, V value) {
		root = put(root,key,value);
//		print();
	}

	private void KeySet(Node u,Set<K> S) {
		if (u == null) {
			return;
		}
		S.add(u.key);
		KeySet(u.left,S);
		KeySet(u.right,S);
	}

	/* Returns a Set view of the keys contained in this map. Not required for Lab 7.
	 * If you don't implement this, throw an UnsupportedOperationException. */
	public Set<K> keySet() {
		Set<K> S = new TreeSet<>();
		KeySet(root,S);
		return S;
	}

	private Node findMax(Node u) {
		if(u.right == null) {
			return u;
		} else {
			return findMax(u.right);
		}
	}

	private Node remove(Node u,K key,V value,boolean needCompare) {
		if (u == null) {
			removedValue = null;
			return null;
		}
		int cmp = key.compareTo(u.key);
		if (cmp == 0) {
			if (needCompare && u.value != value) {
				removedValue = null;
				return u; // do nothing
			}

			size --;
			removedValue = u.value;

			if (u.left != null && u.right != null) {
				Node max = findMax(u.left);
				u.key = max.key;
				u.value = max.value;
				u.left = remove(u.left,max.key,max.value,false);
				return u;
			} else {
				if (u.left == null) {
					return u.right;
				} else {
					return u.left;
				}
			}
		}
		if (cmp < 0) {
			u.left = remove(u.left,key,value,needCompare);
		} else {
			u.right = remove(u.right,key,value,needCompare);
		}
		return u;
	}

	/* Removes the mapping for the specified key from this map if present.
	 * Not required for Lab 7. If you don't implement this, throw an
	 * UnsupportedOperationException. */
	public V remove(K key) {
		root = remove(root,key,null,false);
		return removedValue;
	}

	/* Removes the entry for the specified key only if it is currently mapped to
	 * the specified value. Not required for Lab 7. If you don't implement this,
	 * throw an UnsupportedOperationException.*/
	public V remove(K key, V value) {
		root = remove(root,key,value,true);
		return removedValue;
	}

}
