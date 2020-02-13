import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class EndangeredAnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test

    public void endangeredAnimal_instantiatesCorrectly_true() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal(1, "Rhino", "healthy", "newborn");
        assertEquals(true, testEndangeredAnimal instanceof  EndangeredAnimal);
    }

    @Test

    public void EndangeredAnimal_instantiatesWithRangerId_int(){
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal(1, "Rhino", "ill", "newborn");
        assertEquals(1,testEndangeredAnimal.getRangerId());
    }

    @Test

    public void EndangeredAnimal_instantiatesWithName_String(){
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal(1, "Rhino", "healthy", "young");
        assertEquals("Rhino",testEndangeredAnimal.getName());
    }

    @Test

    public void EndangeredAnimal_instantiatesWithHealth_String(){
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal(1, "Rhino", "ill", "adult");
        assertEquals("ill",testEndangeredAnimal.getHealth());
    }

    @Test

    public void EndangeredAnimal_instantiatesWithAge_String(){
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal(1, "Elephant", "ill", "adult");
        assertEquals("adult",testEndangeredAnimal.getAge());
    }

    @Test

    public void save_returnTrueIfDescriptionsAreSame(){
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal(1, "Elephant", "healthy", "adult");
        testEndangeredAnimal.save();
        assertTrue(EndangeredAnimal.all().get(0).equals(testEndangeredAnimal));
    }

    @Test

    public void equals_returnsTrueIfRangerIdNameHealthAndAgeAreSame_true(){
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal(1, "Elephant", "ill", "adult");
        EndangeredAnimal anotherEndangeredAnimal = new EndangeredAnimal(1, "Elephant", "ill", "adult");
        assertTrue(testEndangeredAnimal.equals(anotherEndangeredAnimal));
    }

    @Test

    public void all_returnsAllInstancesOfEndangeredAnimal_true(){
        EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal(1, "Rhino", "ill", "adult");
        firstEndangeredAnimal.save();
        EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal(1, "Elephant", "healthy", "young");
        secondEndangeredAnimal.save();
        assertEquals(true, EndangeredAnimal.all().get(0).equals(firstEndangeredAnimal));
        assertEquals(true, EndangeredAnimal.all().get(1).equals(secondEndangeredAnimal));
    }

    @Test

    public void find_returnsEndangeredAnimalWithSameId_secondEndangeredAnimal(){
        EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal(1, "Rhino", "ill", "adult");
        firstEndangeredAnimal.save();
        EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal(1, "Elephant", "healthy", "young");
        secondEndangeredAnimal.save();
        assertEquals(EndangeredAnimal.find(secondEndangeredAnimal.getId()),secondEndangeredAnimal);

    }

    @Test

    public void save_savesRangerIdIntoDB_true(){
        Ranger testRanger = new Ranger("Rock Stock", "Nyayo");
        testRanger.save();
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal(testRanger.getId(), "Rhino", "ill", "young");
        testEndangeredAnimal.save();
        EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.find(testEndangeredAnimal.getId());
        assertEquals(savedEndangeredAnimal.getRangerId(), testRanger.getId());
    }
}
