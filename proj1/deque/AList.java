package deque;

import java.util.Iterator;

public class AList<T> implements Iterable<T>{

	private T[] items;
	private int size,left,right;

	private	int capacity(){
		return items.length;
	}

	private int pre(int pos){
		return (pos + capacity() - 1) % capacity();
	}

	private int nxt(int pos){
		return (pos + 1) % capacity();
	}

	private class AListIterator implements Iterator<T>{
		private int pos;
		public AListIterator(){
			pos = left;
		}
		public boolean hasNext(){
			return pos != right;
		}
		public T next(){
			T retVal = items[pos];
			pos = nxt(pos);
			return retVal;
		}
	}

	public AList(){
		left = 0;
		right = 0;
		size = 0;
		items = (T[])(new Object[8]);
	}

	private void resize(int newSize){
		T[] newItems = (T[])(new Object[newSize]);
		int ind = 0;
		for(T x : this) newItems[ind++] = x;
		items = newItems;
		left = 0;
		right = size;
	}

	/** Adds an item of type T to the front of the deque.
	 *  You can assume that item is never null. */
	public void addFirst(T item){
		size ++;
		if(pre(left) == right) resize(capacity() * 2);
		left = pre(left);
		items[left] = item;
	}

	/** Adds an item of type T to the back of the deque.
	 *  You can assume that item is never null. */
	public void addLast(T item) {
		size ++;
		if(nxt(right) == left) resize(capacity() * 2);
		right = nxt(right);
		items[right] = item;
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
		for(T x : this) System.out.print(x + " ");
		System.out.println();
	}
	/** Removes and returns the item at the front of the deque.
	 *  If no such item exists, returns null. */
	public T removeFirst() {
		if(size == 0) return null;
		size --;
		if(capacity() >= 16 && 4 * size < capacity()) resize(capacity() / 2);
		T retVal = items[left];
		items[left] = null;
		left = nxt(left);
		return retVal;
	}
	/** Removes and returns the item at the back of the deque.
	 *  If no such item exists, returns null. */
	public T removeLast() {
		if(size == 0) return null;
		size --;
		if(capacity() >= 16 && 4 * size < capacity()) resize(capacity() / 2);
		T retVal = items[right];
		items[right] = null;
		right = pre(right);
		return retVal;
	}

	/** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
	 *  If no such item exists, returns null. Must not alter the deque! */
	public T get(int index){
		if(index >= size) return null;
		int indCount = 0;
		for(T x : this){
			if(indCount == index) return x;
			indCount ++;
		}
		return null;
	}

	public Iterator<T> iterator(){
		return new AListIterator();
	}

}
