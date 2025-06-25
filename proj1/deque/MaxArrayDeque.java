package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

	Comparator<T> c;

	public MaxArrayDeque(Comparator<T> cp){
		c = cp;
	}

	public T max(){
		T ret = null;
		for(T y : this){
			if(y == null){
				throw new IllegalArgumentException("bbb");
			}
			if(c.compare(ret,y) <= 0){
				ret = y;
			}
		}
		return ret;
	}

	public T max(Comparator<T> c){
		T ret = null;
		for(T x : this){
			if(c.compare(ret,x) <= 0){
				ret = x;
			}
		}
		return ret;
	}

}
