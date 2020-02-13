import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class AnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test

   public void animal_instantiatesCorrectly_true() {

        Animal testAnimal = new Animal(1, "Lion");
        assertEquals(true,testAnimal instanceof Animal);
    }

    @Test

    public void Animal_instantiatesWithRangerId_int(){

        Animal testAnimal = new Animal(1, "Lion");
        assertEquals(1,testAnimal.getRangerId());
    }

    @Test

    public void Animal_instantiatesWithName_String() {

        Animal testAnimal = new Animal(1, "Lion");
        assertEquals("Lion",testAnimal.getName());
    }

    @Test

    public void equals_returnsTrueIfNameAndRangerIdAreSame_true(){

        Animal testAnimal = new Animal(1, "Lion");
        Animal anotherAnimal = new Animal(1, "Lion");
        assertTrue(testAnimal.equals(anotherAnimal));
    }

    @Test

    public void save_returnsTrueIfDescriptionsAreSame(){

        Animal testAnimal = new Animal(1, "Lion");
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }

    @Test

    public void save_assignsIdToAnimal(){

        Animal testAnimal = new Animal(1, "Lion");
        testAnimal.save();
        Animal savedAnimal =  Animal.all().get(0);
        assertEquals(savedAnimal.getId(),testAnimal.getId());
    }

    @Test

    public void all_returnsAllInstancesOfAnimal_true(){

        Animal firstAnimal = new Animal(0, "Lion");
        firstAnimal.save();
        Animal secondAnimal = new Animal(1, "Panther");
        secondAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(firstAnimal));
        assertEquals(true, Animal.all().get(1).equals(secondAnimal));
    }

    @Test

    public void save_savesRangerIdIntoDB_true() {

        Ranger testRanger = new Ranger("Rock Stock", "Nyayo");
        testRanger.save();
        Animal testAnimal = new Animal(testRanger.getId(), "Lion");
        testAnimal.save();
        Animal savedAnimal = Animal.find(testAnimal.getId());
        assertEquals(savedAnimal.getRangerId(),testRanger.getId());
    }
}
