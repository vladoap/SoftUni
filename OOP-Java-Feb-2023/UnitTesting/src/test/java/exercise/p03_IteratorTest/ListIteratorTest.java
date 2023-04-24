package exercise.p03_IteratorTest;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ListIteratorTest {

    private ListIterator listIterator;
    private static final String[] elements = {"Stefan", "George", "Vlado", "Ivan"};

    @Before
    public void setUp() throws OperationNotSupportedException {
        listIterator = new ListIterator(elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowsWhenNull() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator(null);
    }

    @Test
    public void testMoveShouldMoveTheIndex() {
        int currentIndex = listIterator.getCurrentIndex();
        listIterator.move();
        assertEquals(currentIndex + 1, listIterator.getCurrentIndex());
    }

    @Test
    public void testMoveShouldNotMoveTheIndex() {

        for (int i = 0; i < listIterator.getElements().size() - 1; i++) {
            listIterator.move();
        }
        assertFalse(listIterator.move());

    }

    @Test
    public void testHasNextReturnsTrueWhenThereIsNextElement() {
        assertTrue(listIterator.hasNext());
    }

    @Test
    public void testHasNextReturnsFalseWhenThereIsNoNextElement() throws OperationNotSupportedException {
        listIterator = new ListIterator("Gosho");
        assertFalse(listIterator.hasNext());
    }

    @Test
    public void testPrintShouldReturnCurrentIndex() {
        int currentIndex = listIterator.getCurrentIndex();
        String currentElement = listIterator.getElements().get(currentIndex);
        assertEquals(currentElement, listIterator.print());
    }

    @Test(expected = IllegalStateException.class)
    public void testPrintThrowsWhenNull() throws OperationNotSupportedException {
        ListIterator listIteratorEmpty = new ListIterator();
        listIteratorEmpty.print();
    }




}