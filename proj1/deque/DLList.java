package deque;

import java.util.Iterator;

public class DLList<T> implements Iterable<T> {

    private Node sentinel;
    private int size;

    private class Node {
        private T first;
        private Node next, pre;
        Node() {
            first = null;
            next = null;
            pre = null;
        }
        Node(T fst, Node  nxt, Node pr) {
            first = fst;
            next = nxt;
            pre = pr;
        }
        Node(Node p) {
            first = p.first;
            next = p.next;
            pre = p.pre;
        }
    }

    private class DLListIterator implements Iterator<T> {
        Node p;
        DLListIterator() {
            p = new Node(sentinel);
        }
        public boolean hasNext() {
            return p.next.first != null;
        }
        public T next() {
            p = p.next;
            return p.first;
        }
    }

    public DLList() {
        size = 0;
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }

    /** Adds an item of type T to the front of the deque.
     *  You can assume that item is never null. */
    public void addFirst(T item) {
        size++;
        Node tmpNext = sentinel.next;
        sentinel.next = new Node(item, tmpNext, sentinel);
        tmpNext.pre = sentinel.next;
    }

    /** Adds an item of type T to the back of the deque.
     *  You can assume that item is never null. */
    public void addLast(T item) {
        size++;
        Node tmpPre = sentinel.pre;
        sentinel.pre = new Node(item, sentinel, tmpPre);
        tmpPre.next = sentinel.pre;
    }
    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }
    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }
    /** Prints the items in the deque from first to last, separated by a space.
     *  Once all the items have been printed, print out a new line. */
    public void print() {
        for (T x : this) System.out.print(x + " ");
        System.out.println();
    }
    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null. */
    public T removeFirst() {
        Node tmpNext = sentinel.next;
        sentinel.next = sentinel.next.next;
        if (tmpNext != sentinel) {
            size--;
            tmpNext.next = null;
            tmpNext.pre = null;
        }
        sentinel.next.pre = sentinel;
        return tmpNext.first;
    }
    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null. */
    public T removeLast() {
        Node tmpPre = sentinel.pre;
        sentinel.pre = sentinel.pre.pre;
        if (tmpPre != sentinel) {
            size--;
            tmpPre.pre = null;
            tmpPre.next = null;
        }
        sentinel.pre.next = sentinel;
        return tmpPre.first;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     *  If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (index > size) {
            return null;
        }
        int indCount = 0;
        for (T x : this) {
            if (indCount == index) {
                return x;
            }
            indCount++;
        }
        return null;
    }

    private T getRecursiveByNode(Node node, int index) {
        if (node == null) {
            return null;
        } else if (index == 0) {
            return node.first;
        } else {
            return getRecursiveByNode(node.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        return getRecursiveByNode(sentinel.next, index);
    }

    public Iterator<T> iterator() {
        return new DLListIterator();
    }
}
