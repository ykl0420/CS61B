package deque;

import java.util.Iterator

public class AList<T> implements Iterable<T>{

	T[] items;
	int size;

	private class AListIterator implements Iterator<T>{
		public boolean hasNext(){

		}
		public T next(){

		}
	}

	void resize(int newSize){

	}

	/** Adds an item of type T to the front of the deque.
	 *  You can assume that item is never null. */
	public void addFirst(T item){
	}

	/** Adds an item of type T to the back of the deque.
	 *  You can assume that item is never null. */
	public void addLast(T item) {
		if(size == items.length) resize(items.length * 2);
		items[size ++] = item;
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
		DLList.Node tmpNext = sentinel.next;
		sentinel.next = sentinel.next.next;
		if(tmpNext != sentinel) {
			size --;
			tmpNext.next = null;
			tmpNext.pre = null;
		}
		sentinel.next.pre = sentinel;
		return tmpNext.first;
	}
	/** Removes and returns the item at the back of the deque.
	 *  If no such item exists, returns null. */
	public T removeLast() {
		DLList.Node tmpPre = sentinel.pre;
		sentinel.pre = sentinel.pre.pre;
		if(tmpPre != sentinel) {
			size --;
			tmpPre.pre = null;
			tmpPre.next = null;
		}
		sentinel.pre.next = sentinel;
		return tmpPre.first;
	}

	/** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
	 *  If no such item exists, returns null. Must not alter the deque! */
	public T get(int index){
		if(index > size) return null;
		int indCount = 0;
		for(T x : this){
			indCount ++;
			if(indCount == index) return x;
		}
		return null;
	}

	public Iterator<T> iterator(){
		return new AList.AListIterator();
	}

}
