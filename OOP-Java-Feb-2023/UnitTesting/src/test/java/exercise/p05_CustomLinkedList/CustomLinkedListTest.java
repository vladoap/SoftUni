package exercise.p05_CustomLinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomLinkedListTest {

    private CustomLinkedList<String> list;
    private static final String element1 = "Gosho";
    private static final String element2 = "Pesho";
    private static final String element3 = "Ivan";
    private static final String element4 = "Penka";

    @Before
    public void testConstructorWithElement() {
        list = new CustomLinkedList<>();
        list.add(element1);
        list.add(element2);
        list.add(element3);
        list.add(element4);
    }

    @Test
    public void testAddShouldAdd() {
        int previousSize = list.getCount();
        String personToAdd = "Vlado";
        list.add(personToAdd);
        int currentSize = list.getCount();
        assertEquals(currentSize, previousSize + 1);
        assertEquals(list.getCount() - 1, list.indexOf(personToAdd));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtShouldThrowWithNegativeIndex() {
        list.removeAt(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtShouldThrowWithBiggerIndex() {
        list.removeAt(list.getCount());
    }

    @Test
    public void testRemoveAtShouldRemoveAtIndex() {
        int previousSize = list.getCount();
        int indexToRemove = 2;
        assertEquals("Ivan", list.removeAt(indexToRemove));
        int currentSize = list.getCount();
        assertEquals(currentSize, previousSize -1);
    }

    @Test
    public void testRemoveWithMissingElement() {
        assertEquals(-1, list.remove("Misho"));
    }

    @Test
    public void testRemoveShouldRemoveElement() {
        int indexToRemove = 1;
        int previousSize = list.getCount();
        assertEquals(indexToRemove, list.remove(list.get(indexToRemove)));
        int currentSize = list.getCount();
        assertEquals(currentSize, previousSize -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetThrowsWithNegativeIndex() {
        list.set(-1, "Pesho");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetThrowsWithBiggerIndex() {
        list.set(list.getCount(), "Pesho");
    }

    @Test
    public void testSetShouldSetElementAtIndex() {
        list.set(1, "Mitko");
        assertEquals("Mitko", list.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetThrowsWithNegativeIndex() {
        list.get(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetThrowsWithBiggerIndex() {
        list.get(list.getCount());
    }

    @Test
    public void testGetShouldGetElementAtIndex() {
        assertEquals("Gosho", list.get(0));
    }

    @Test
    public void testIndexOfWhenMissingElement() {
        assertEquals(-1, list.indexOf("Blago"));
    }

    @Test
    public void testIndexOfShouldReturnTheIndexOfElement() {
        assertEquals(2, list.indexOf("Ivan"));
    }

    @Test
    public void testContainsWhenMissingElement() {
        assertFalse(list.contains("Margi"));
    }

    @Test
    public void testContainsWhenElementExists() {
        assertTrue(list.contains("Ivan"));
    }

    @Test
    public void testRemoveListNode() {
        list = new CustomLinkedList<>();
        int previousSize = list.getCount();
        list.add("Vlado");
        int currentSize = list.getCount();
        assertEquals(0, list.remove("Vlado"));
        assertEquals(currentSize, previousSize + 1);

    }








}