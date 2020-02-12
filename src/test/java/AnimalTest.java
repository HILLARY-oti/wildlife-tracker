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
}
