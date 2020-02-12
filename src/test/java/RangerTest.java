import org.junit.*;
import static org.junit.Assert.*;

public class RangerTest {

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
}
