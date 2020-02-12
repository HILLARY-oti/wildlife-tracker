import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class RangerTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void ranger_instantiatesCorrectly_true() {
        Ranger testRanger = new Ranger("Rock Stock", "Nyayo");
        assertEquals(true,testRanger instanceof Ranger);
    }

    @Test

    public void getName_rangerInstantiatesWithName_String() {
        Ranger testRanger = new Ranger("Rock Stock", "Nyayo");
        assertEquals("Rock Stock",testRanger.getName());
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

    public void all_returnsAllInstancesOfRanger_true(){
        Ranger firstRanger = new Ranger("Rock Stock", "Nyayo");
        firstRanger.save();
        Ranger secondRanger = new Ranger("Joker Sam", "Swat");
        secondRanger.save();
        assertEquals(true, Ranger.all().get(0).equals(firstRanger));
        assertEquals(true, Ranger.all().get(1).equals(secondRanger));
    }
}
