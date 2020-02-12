import org.junit.*;
import static org.junit.Assert.*;

public class RangerTest {

    @Test
    public void ranger_instantiatesCorrectly_true() {
        Ranger testRanger = new Ranger("Rock Stock", "Nyayo");
        assertEquals(true,testRanger instanceof Ranger);
    }
}
