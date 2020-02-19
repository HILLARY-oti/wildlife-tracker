import models.Animal;
import models.EndangeredAnimal;
import models.Ranger;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class RangerTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void ranger_instantiatesCorrectly_true() {
        Ranger testRanger = new Ranger("Rock Stock", "Nyayo");
        assertEquals(true, testRanger instanceof Ranger);
    }

    @Test

    public void getName_rangerInstantiatesWithName_String() {
        Ranger testRanger = new Ranger("Rock Stock", "Nyayo");
        assertEquals("Rock Stock", testRanger.getName());
    }


    @Test

    public void getCrew_personInstantiatesWithCrew_String() {
        Ranger testRanger = new Ranger("Rock Stock", "Nyayo");
        assertEquals("Nyayo", testRanger.getCrew());
    }

    @Test

    public void equals_returnTrueIfNameAndCrewSame_true() {
        Ranger firstRanger = new Ranger("Rock Stock", "Nyayo");
        Ranger anotherRanger = new Ranger("Rock Stock", "Nyayo");
        assertTrue(firstRanger.equals(anotherRanger));
    }

    @Test

    public void save_insertsObjectIntoDatabase_Ranger() {
        Ranger testRanger = new Ranger("Rock Stock", "Nyayo");
        testRanger.save();
        assertTrue(Ranger.all().get(0).equals(testRanger));
    }

    @Test

    public void all_returnsAllInstancesOfRanger_true() {
        Ranger firstRanger = new Ranger("Rock Stock", "Nyayo");
        firstRanger.save();
        Ranger secondRanger = new Ranger("Joker Sam", "Swat");
        secondRanger.save();
        assertEquals(true, Ranger.all().get(0).equals(firstRanger));
        assertEquals(true, Ranger.all().get(1).equals(secondRanger));
    }

    @Test

    public void save_assignIdToObject() {
        Ranger testRanger = new Ranger("Rock Stock", "Nyayo");
        testRanger.save();
        Ranger savedRanger = Ranger.all().get(0);
        assertEquals(testRanger.getId(), savedRanger.getId());
    }

    @Test

    public void find_returnsRangerWithSameId_secondRanger() {
        Ranger firstRanger = new Ranger("Rock Stock", "Nyayo");
        firstRanger.save();
        Ranger secondRanger = new Ranger("Joker Sam", "Swat");
        secondRanger.save();
        assertEquals(Ranger.find(secondRanger.getId()), secondRanger);
    }

    @Test

    public void getAnimals_retrievesAllAnimalsFromDatabase_animalsList() {

        Ranger testRanger = new Ranger("Rock Stock", "Nyayo");
        testRanger.save();
        Animal firstAnimal = new Animal(testRanger.getId(), "Lion");
        firstAnimal.save();
        Animal secondAnimal = new Animal(testRanger.getId(), "Elephant");
        secondAnimal.save();
        Animal[] animals = new Animal[]{firstAnimal, secondAnimal};
        assertTrue(testRanger.getAnimals().containsAll(Animal.all()));
    }

    @Test

    public void getEndangeredAnimals_retrievesAllEndangeredAnimalsFromDatabase_endangeredAnimalsList() {

        Ranger testRanger = new Ranger("Rock Stock", "Nyayo");
        testRanger.save();
        EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal(testRanger.getId(), "Rhino", "ill", "young");
        firstEndangeredAnimal.save();
        EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal(testRanger.getId(), "Elephant", "healthy", "adult");
        secondEndangeredAnimal.save();
        EndangeredAnimal[] endangeredAnimals = new EndangeredAnimal[]{firstEndangeredAnimal, secondEndangeredAnimal};
        assertTrue(testRanger.getEndangeredAnimals().containsAll(EndangeredAnimal.all()));
    }

}