package exercise.p04_BubbleSortTest;

import org.junit.Before;
import org.junit.Test;

import java.sql.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

public class BubbleTest {

    private static final int[] numbers = {2, 5, -5, 10,3};
    private static final int[] sortedNumbers = {-5, 2, 3, 5, 10};

   @Test
   public void testSortShouldSortElements() {
       int[] elements = Bubble.sort(numbers);
       assertArrayEquals(sortedNumbers, numbers);
   }

}