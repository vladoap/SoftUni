package archeologicalExcavations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExcavationTests {


    private Excavation excavation;


    @Before
    public void setUp() {
        excavation = new Excavation("excavation_1", 2);
    }


    @Test
    public void testConstructorShouldSetValidData() {
        String excavationName = "excavation_valid";
        int capacity = 10;
        Excavation validExcavation = new Excavation(excavationName, capacity);

        assertEquals(excavationName, validExcavation.getName());
        assertEquals(capacity, validExcavation.getCapacity());
    }


    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowWhenNameNull() {
        String excavationName = null;
        int capacity = 10;
        Excavation invalidExcavation = new Excavation(excavationName, capacity);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowWhenNameEmpty() {
        String excavationName = "        ";
        int capacity = 10;
        Excavation invalidExcavation = new Excavation(excavationName, capacity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowWhenCapacityNegative() {
        String excavationName = "Test";
        int capacity = -1;
        Excavation invalidExcavation = new Excavation(excavationName, capacity);
    }

    @Test
    public void testAddArchaeologistShouldAddWhenDoesntExist() {
        Archaeologist archaeologist1 = new Archaeologist("Gosho", 50);
        int sizeBefore = excavation.getCount();
        excavation.addArchaeologist(archaeologist1);
        int sizeAfter = excavation.getCount();

        assertEquals(sizeBefore + 1, sizeAfter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArchaeologistShouldThrowWhenExistsAlready() {
        Archaeologist archaeologist1 = new Archaeologist("Gosho", 50);
        excavation.addArchaeologist(archaeologist1);

        Archaeologist archaeologist2 = new Archaeologist("Gosho", 10);
        excavation.addArchaeologist(archaeologist2);


    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArchaeologistShouldThrowWhenNoCapacity() {
        Archaeologist archaeologist1 = new Archaeologist("Gosho", 50);
        Archaeologist archaeologist2 = new Archaeologist("Peca", 50);
        Archaeologist archaeologist3 = new Archaeologist("Joro", 50);
        excavation.addArchaeologist(archaeologist1);
        excavation.addArchaeologist(archaeologist2);
        excavation.addArchaeologist(archaeologist3);

    }

    @Test
    public void testRemoveArchaeologistShouldRemove() {
        Archaeologist archaeologist1 = new Archaeologist("Gosho", 50);
        excavation.addArchaeologist(archaeologist1);
        int sizeBefore = excavation.getCount();

        boolean isRemoved = excavation.removeArchaeologist("Gosho");
        int sizeAfter = excavation.getCount();

        assertEquals(sizeBefore - 1, sizeAfter);
        assertTrue(isRemoved);
    }

    @Test
    public void testRemoveArchaeologistShouldNotRemove() {
        Archaeologist archaeologist1 = new Archaeologist("Gosho", 50);
        int sizeBefore = excavation.getCount();

        boolean isRemoved = excavation.removeArchaeologist("Ivan");
        int sizeAfter = excavation.getCount();

        assertEquals(sizeBefore, sizeAfter);
        assertFalse(isRemoved);
    }





}
