package implementations;

import junit.framework.TestCase;
import org.junit.Test;

public class ArrayDequeTest extends TestCase {

   @Test
    public void testArrayDeque() {
       ArrayDeque<Integer> deque = new ArrayDeque<>();

//       deque.addFirst(10);
//       deque.addFirst(11);
//       deque.addFirst(12);
//       deque.addFirst(13);
//       deque.addFirst(14);
//
//       for (Integer integer : deque) {
//           System.out.println(integer);
//       }

       deque.add(13);
       deque.add(14);
       deque.add(15);
       deque.add(16);
//       deque.add(17);
      System.out.println(deque.get(3));

       System.out.println();
   }

}