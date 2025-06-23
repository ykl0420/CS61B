package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
	public static boolean testEqual(AListNoResizing list,BuggyAList bugList){
		if(list.size() != bugList.size()) return false;
		for(int i = 0; i < list.size(); i ++){
			if(list.get(i) != bugList.get(i)){
				return false;
			}
		}
		return true;
	}
	@Test
	public void testThreeAddThreeRemove(){
		AListNoResizing<Integer> list = new AListNoResizing<>();
		BuggyAList<Integer> bugList = new BuggyAList<>();
		list.addLast(3);
		list.addLast(4);
		list.addLast(5);
		bugList.addLast(3);
		bugList.addLast(4);
		bugList.addLast(5);
		assertTrue(testEqual(list,bugList));
		for(int i = 0; i < 3; i ++){
			list.removeLast();
			bugList.removeLast();
			assertTrue(testEqual(list,bugList));
		}
	}

	@Test
	public void randomizedTest(){
		AListNoResizing<Integer> L = new AListNoResizing<>();
		BuggyAList<Integer> bL = new BuggyAList<>();

		int N = 5000;
		for (int i = 0; i < N; i += 1) {

//			assertTrue(testEqual(L,bL));


			int operationNumber = StdRandom.uniform(0, 4);
			if (operationNumber == 0) {
				// addLast
				int randVal = StdRandom.uniform(0, 100);
				L.addLast(randVal);
				bL.addLast(randVal);

				System.out.println("addLast(" + randVal + ")");
			} else if (operationNumber == 1) {
				// size
				int size = L.size();
				int bsize = bL.size();
				assertEquals(size,bsize);
				System.out.println("size: " + size);
			}else if(operationNumber == 2) {

				assertEquals(L.size(),bL.size());

				if(L.size() == 0) continue;
				if(bL.size() == 0) continue;
				assertEquals(L.getLast(),bL.getLast());

				System.out.println(L.getLast());
			}else if(operationNumber == 3){

				assertEquals(L.size(),bL.size());

				if(L.size() == 0) continue;
				if(bL.size() == 0) continue;

				int Lret = L.removeLast();
				int bLret = bL.removeLast();

				assertEquals(Lret,bLret);

				System.out.println(Lret);
			}
		}
	}
}
