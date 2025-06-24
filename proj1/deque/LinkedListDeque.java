package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>,Deque<T>{
	private DLList<T> list;

	public LinkedListDeque(){
		list = new DLList<>();
	}
	/** Adds an item of type T to the front of the deque.
	 *  You can assume that item is never null. */
	public void addFirst(T item){
		list.addFirst(item);
	}
	/** Adds an item of type T to the back of the deque.
	 *  You can assume that item is never null. */
	public void addLast(T item){
		list.addLast(item);
	}
	/** Returns true if deque is empty, false otherwise. */
	public boolean isEmpty(){
		return list.isEmpty();
	}
	/** Returns the number of items in the deque. */
	public int size(){
		return list.size();
	}
	/** Prints the items in the deque from first to last, separated by a space.
	 *  Once all the items have been printed, print out a new line. */
	public void printDeque(){
		list.print();
	}
	/** Removes and returns the item at the front of the deque.
	 *  If no such item exists, returns null. */
	public T removeFirst(){
		return list.removeFirst();
	}
	/** Removes and returns the item at the back of the deque.
	 *  If no such item exists, returns null. */
	public T removeLast(){
		return list.removeLast();
	}
	/** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
	 *  If no such item exists, returns null. Must not alter the deque! */
	public T get(int index){
		return list.get(index);
	}

	public T getRecursive(int index){
		return list.getRecursive(index);
	}

	/** The Deque objects we’ll make are iterable (i.e. Iterable<T>); so we must provide this method to return an iterator. */
	public Iterator<T> iterator(){
		return list.iterator();
	}

	/** Returns whether or not the parameter o is equal to the Deque.
	 *  o is considered equal if it is a Deque and if it contains the same contents
	 *  (as goverened by the generic T’s equals method) in the same order.
	 *  (ADDED 2/12: You’ll need to use the instance of keywords for this. Read here for more information) */
	public boolean equals(Object o){
		if(o == null) return false;
		if(o instanceof Deque) return false;
		Deque<T> other = (Deque<T>)o;
		if(this.size() != other.size()) return false;
		Iterator<T> it1 = this.iterator(),it2 = other.iterator();
		while(it1.hasNext()){
			if(!it1.next().equals(it2.next())){
				return false;
			}
		}
		return true;
	}
}
