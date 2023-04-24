package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PetStoreTests {

    private static final Animal animal1 = new Animal("mammal", 50, 20);
    private static final Animal animal2 = new Animal("fish", 2, 10);
    private static final Animal animal3 = new Animal("reptile", 10, 5);
    private static final Animal animal4 = new Animal("reptile", 6, 2);
    private PetStore petStore;


    @Before
    public void setUp() {
        petStore = new PetStore();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalShouldThrowWhenNull() {
        petStore.addAnimal(null);
    }

    @Test
    public void testAddAnimalShouldAddAnimalToList() {
        assertEquals(0, petStore.getCount());
        petStore.addAnimal(animal1);
        assertEquals(1, petStore.getCount());
        assertTrue(petStore.getAnimals().contains(animal1));
    }

    @Test
    public void testFindAllAnimalsWithMaxKilogramsShouldFilterTheAnimals() {
        addMultipleAniamls();
        int maxKg = 10;
        List<Animal> filteredAnimals = petStore.findAllAnimalsWithMaxKilograms(maxKg);

        assertEquals(1, filteredAnimals.size());
        for (Animal animal : filteredAnimals) {
            assertTrue(animal.getMaxKilograms() > maxKg);
        }
    }



    @Test
    public void testGetTheMostExpensiveAnimal() {
        addMultipleAniamls();

        Animal animal = petStore.getTheMostExpensiveAnimal();

        assertEquals(animal1, animal);
    }

    @Test
    public void testFindAllAnimalBySpecie() {
        addMultipleAniamls();

        List<Animal> animals = petStore.findAllAnimalBySpecie("reptile");

        for (Animal animal : animals) {
            assertEquals("reptile", animal.getSpecie());
        }
    }

    private void addMultipleAniamls() {
        petStore.addAnimal(animal1);
        petStore.addAnimal(animal2);
        petStore.addAnimal(animal3);
        petStore.addAnimal(animal4);
    }

}

