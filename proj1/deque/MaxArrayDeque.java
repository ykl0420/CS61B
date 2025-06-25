package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> c;

    public MaxArrayDeque(Comparator<T> cp) {
        c = cp;
    }

    public T max(Comparator<T> cp) {
        T ret = null;
        for (T x : this) {
            if (ret == null) {
                ret = x;
            } else if (cp.compare(ret, x) <= 0) {
                ret = x;
            }
        }
        return ret;
    }

    public T max() {
        return max(c);
    }

}
