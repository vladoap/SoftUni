package toyStore;


import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.*;

public class ToyStoryTest {

    private ToyStore toyStore;

    @Before
    public void setUp() {
        toyStore = new ToyStore();
    }

    @Test
    public void getVaultCells() {
        Map<String, Toy> toyShelf;
        toyShelf = new LinkedHashMap<>();
        toyShelf.put("A", null);
        toyShelf.put("B", null);
        toyShelf.put("C", null);
        toyShelf.put("D", null);
        toyShelf.put("E", null);
        toyShelf.put("F", null);
        toyShelf.put("G", null);
        assertEquals(toyShelf, toyStore.getToyShelf());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToyShouldThrowsWhenShellDoesntExists() throws OperationNotSupportedException {
        String shelf = "Invalid";
        Toy toy = new Toy("Comset", "123456");
        toyStore.addToy(shelf, toy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToyShouldThrowsWhenShellIsAlreadyTaken() throws OperationNotSupportedException {
        String shelf = "A";
        Toy toy1 = new Toy("Comset", "123456");
        Toy toy2 = new Toy("TedyBear", "5784");
        toyStore.addToy(shelf, toy1);
        toyStore.addToy(shelf, toy2);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddToyShouldThrowsWhenTheToyIsAlreadyAdded() throws OperationNotSupportedException {
        String shelf1 = "A";
        String shelf2 = "C";

        Toy toy = new Toy("Comset", "123456");

        toyStore.addToy(shelf1, toy);
        toyStore.addToy(shelf2, toy);
    }

    @Test
    public void testAddToyShouldAddToy() throws OperationNotSupportedException {
        String shelf = "A";
        Toy toy = new Toy("Comset", "123456");

        assertNull(toyStore.getToyShelf().get(shelf));
        toyStore.addToy(shelf, toy);
        assertEquals(toyStore.getToyShelf().get(shelf), toy);
        assertEquals("Comset",toyStore.getToyShelf().get(shelf).getManufacturer());
        long exist = toyStore.getToyShelf().values().stream().filter(Objects::nonNull).count();
        assertEquals(1, exist);
    }

    @Test
    public void testRemoveToyShouldRemoveToy() throws OperationNotSupportedException {
        Toy i = new Toy("TestOwner", "TestItem");
        Toy i2 = new Toy("TestOwner", "TestItem2");
        toyStore.addToy("A", i);
        toyStore.addToy("B", i2);
        String result =  toyStore.removeToy("B", i2);
        long exist = toyStore.getToyShelf().values().stream().filter(Objects::nonNull).count();
        assertEquals(1, exist);
        assertEquals("Remove toy:TestItem2 successfully!", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveToyShouldThrowsWhenShelfDoesntExist() throws OperationNotSupportedException {
        String shelf = "A";
        Toy toy = new Toy("Comset", "123456");

        toyStore.addToy(shelf, toy);
        assertTrue(toyStore.getToyShelf().containsValue(toy));

        assertFalse(toyStore.getToyShelf().containsKey("Invalid"));
        toyStore.removeToy("Invalid", toy);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveToyShouldThrowsWhenToyIsNotOnTheShelf() throws OperationNotSupportedException {
        String shelf = "A";
        Toy toy1 = new Toy("Comset", "123456");
        toyStore.addToy(shelf, toy1);

        Toy toy2 = new Toy("Test", "555555");
        toyStore.removeToy(shelf, toy2);

    }



}