package exercise.p02_ExtendedDatabase;


import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;



import static org.junit.Assert.*;

public class DatabaseTest {

    private static final int maxSizeArray = 16;
    private static final int minSizeArray = 1;
    private static final Person VLADO = new Person(210, "Vlado");
    private static final Person GOSHO = new Person(200, "Gosho");


    private Database database;
    private Person[] initialPersons;


    @Before
    public void setUp() throws OperationNotSupportedException {
        database = new Database(VLADO, GOSHO);
        initialPersons = database.getElements();
    }

    @Test
    public void testConstructorCreatesValidDB() throws OperationNotSupportedException {
        Person[] dbElements = database.getElements();
        assertArrayEquals(initialPersons, dbElements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowsWithMoreThen16Elements() throws OperationNotSupportedException {
        Person[] bigArray = new Person[maxSizeArray + 1];
        database = new Database(bigArray);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowsWithNoElements() throws OperationNotSupportedException {
        Person[] emptyArray = new Person[minSizeArray - 1];
        database = new Database(emptyArray);
    }


    @Test(expected = OperationNotSupportedException.class)
    public void testAddThrowsWhenElementIsNull() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testAddElements() throws OperationNotSupportedException {
        int sizeBefore = database.getElements().length;
        Person personToAdd = new Person(123, "Ivan");
        database.add(personToAdd);
        Person[] dbElements = database.getElements();
        assertEquals(dbElements[dbElements.length - 1], personToAdd);
        assertEquals(sizeBefore + 1, dbElements.length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowsWhenElementIsNull() throws OperationNotSupportedException {
        Person[] dbElements = database.getElements();
        for (Person person : database.getElements()) {
            database.remove();
        }
        database.remove();
    }


    @Test
    public void testRemoveShouldRemoveLastElement() throws OperationNotSupportedException {
        Person[] dbElementsBefore = database.getElements();
        database.remove();
        Person[] dbElementsAfter = database.getElements();
        assertEquals(dbElementsBefore[dbElementsBefore.length - 2], dbElementsAfter[dbElementsAfter.length - 1]);
        assertEquals(dbElementsBefore.length - 1, dbElementsAfter.length);

    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowsWhenNull() throws OperationNotSupportedException {
        String userName = null;
        database.findByUsername(userName);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowsWhenUsernameIsMissing() throws OperationNotSupportedException {
        database.findByUsername("Pencho");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowsWhenUsernameAlreadyExists() throws OperationNotSupportedException {
        Person firstPerson = database.getElements()[0];
        database.add(firstPerson);
        database.findByUsername("Marin");
    }

    @Test
    public void testFindByUsernameReturnsPerson() throws OperationNotSupportedException {
        Person person = database.findByUsername(VLADO.getUsername());
        assertEquals(VLADO.getUsername(), person.getUsername());
        assertEquals(VLADO.getId(), person.getId());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdTrowsWhenNotExists() throws OperationNotSupportedException {
        database.findById(1000000);
    }

    @Test
    public void testFindByIdReturnsPerson() throws OperationNotSupportedException {
        Person person = database.findById(VLADO.getId());
        assertEquals(VLADO.getId(), person.getId());
        assertEquals(VLADO.getUsername(), person.getUsername());

    }


}