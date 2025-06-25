package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct, 
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
//        /*
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
//        */
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
//        /*
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());
//        */
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
//        /*
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
//        */
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

//        /*
        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
//        */
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
//        /*
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque, ", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque, ", null, lld1.removeLast());

//        */
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
//        /*
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }

//        */
    }

    @Test
    public void ownTestByHand1() {
        LinkedListDeque<Integer> t1 = new LinkedListDeque<Integer>();
        ArrayDeque<Integer> t2 = new ArrayDeque<>();
        t1.addFirst(1);
        t2.addFirst(1);
        t1.addFirst(2);
        t2.addFirst(2);
        t2.printDeque();
        assertEquals(2, t2.size());
        t1.removeLast();
        t2.removeLast();
        t2.printDeque();
        t1.addFirst(3);
        t2.addFirst(3);
        t2.printDeque();
        t1.addLast(4);
        t2.addLast(4);
        t2.printDeque();
        t1.removeFirst();
        t2.removeFirst();
//        t1.printDeque();
        t2.printDeque();
        assertTrue(t1.equals(t2));
        assertTrue(t2.equals(t1));
        assertEquals(t1.size(), 2);
        assertEquals(t2.size(), 2);
        assertEquals((int)t1.get(0), 2);
        assertEquals((int)t2.get(0), 2);
        assertEquals((int)t1.get(1), 4);
        assertEquals((int)t2.get(1), 4);
        assertEquals((int)t1.get(0), (int)t1.getRecursive(0));
        assertEquals((int)t1.get(1), (int)t1.getRecursive(1));
        assertEquals(null, t1.getRecursive(2));
        assertEquals(t1.get(2), null);
        assertEquals(t2.get(2), null);

    }

    @Test
    public void testResize() {

        ArrayDeque<Integer> t2 = new ArrayDeque<>();

        for (int i = 0; i < 16; i++) t2.addFirst(i);
        assertEquals(16, t2.size());
        t2.printDeque();
        for (int i = 0; i < 16; i++) assertEquals(15 - i, (int)t2.get(i));
        for (int i = 15; i >= 2; i--) assertEquals(15 - i, (int)t2.removeLast());
        assertEquals(2, t2.size());
        assertEquals(15, (int)t2.get(0));
        assertEquals(14, (int)t2.get(1));
    }

    public class MinCompartor<T extends Comparable<? super T> > implements Comparator<T>{
        public int compare(T x, T y) {
            if (x == null) {
                return -1;
            }
            return -x.compareTo(y);
        }
    }class MaxCompartor<T extends Comparable<? super T> > implements Comparator<T>{
        public int compare(T x, T y) {
            if (x == null) {
                return -1;
            }
            return x.compareTo(y);
        }
    }

    @Test
    public void testMaxArrayDeque() {
        MaxArrayDeque<Integer> t = new MaxArrayDeque<> (new MinCompartor<Integer>());
        t.addFirst(1);
        t.addFirst(10);
        t.addFirst(1);
        t.addFirst(1);
        t.addFirst(1);
        t.addLast(1);
        t.addLast(1);
        t.addLast(1);
        t.addLast(1);
        t.addLast(1);
        t.addFirst(1);
        t.addFirst(3);
        t.addFirst(3);
        t.addLast(-5);
        assertEquals(-5, (int)t.max());
        assertEquals(10, (int)t.max(new MaxCompartor<>()));
    }
}
