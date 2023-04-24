package exercise.p01_Database;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {

    private static final int maxSizeArray = 16;
    private static final int minSizeArray = 1;

    private Database database;
    private Integer[] ELEMENTS = {1, 6, 8, 15, 2, 5};



    @Before
    public void setUp() throws OperationNotSupportedException {
        database = new Database(ELEMENTS);
    }

    @Test
    public void testConstructorCreatesValidDB() throws OperationNotSupportedException {
        Integer[] dbElements = database.getElements();
        assertArrayEquals(ELEMENTS, dbElements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowsWithMoreThen16Elements() throws OperationNotSupportedException {
        Integer[] bigArray = new Integer[maxSizeArray + 1];
        database = new Database(bigArray);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowsWithNoElements() throws OperationNotSupportedException {
        Integer[] emptyArray = new Integer[minSizeArray - 1];
        database = new Database(emptyArray);
    }


    @Test(expected = OperationNotSupportedException.class)
    public void testAddThrowsWhenElementIsNull() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testAddElements() throws OperationNotSupportedException {
        int sizeBefore = database.getElements().length;
        database.add(26);
        Integer[] dbElements = database.getElements();
        assertEquals(dbElements[dbElements.length - 1], Integer.valueOf(26));
        assertEquals(sizeBefore + 1, dbElements.length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowsWhenElementIsNull() throws OperationNotSupportedException {
        for (Integer element : ELEMENTS) {
            database.remove();
        }
        database.remove();
    }


    @Test
    public void testRemoveShouldRemoveLastElement() throws OperationNotSupportedException {
        Integer[] dbElementsBefore = database.getElements();
        database.remove();
        Integer[] dbElementsAfter = database.getElements();
        assertEquals(dbElementsBefore[dbElementsBefore.length - 2], dbElementsAfter[dbElementsAfter.length - 1]);
        assertEquals(dbElementsBefore.length - 1, dbElementsAfter.length);

    }

}