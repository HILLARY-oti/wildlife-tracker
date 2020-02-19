import models.Sightings;
import org.junit.*;
import static org.junit.Assert.*;

public class SightingsTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void sight_instantiatesCorrectly_true(){
        Sightings testSighting = new Sightings("ZoneA", "west");
        assertEquals(true,testSighting instanceof  Sightings);
    }

    @Test
    public void getName_sightingInstantiatesWithName_String(){
        Sightings testSighting = new Sightings("ZoneA", "west");
        assertEquals("ZoneA", testSighting.getName());
    }

    @Test
    public void getLocation_sightingInstantiatesWithLocation_String(){
        Sightings testSighting = new Sightings("ZoneA", "west");
        assertEquals("west", testSighting.getLocation());
    }

    @Test
    public void equals_returnsTrueIfNameAndLocationAreSame_True(){
        Sightings testSighting = new Sightings("ZoneA", "west");
        Sightings anotherSighting = new Sightings("ZoneA", "west");
        assertTrue(testSighting.equals(anotherSighting));
    }

    @Test
    public void save_insertsObjectIntoDatabase_Sighting(){
        Sightings testSighting = new Sightings("ZoneA", "west");
        testSighting.save();
        assertEquals(true, Sightings.all().get(0).equals(testSighting));
    }

    @Test
    public void all_returnsAllInstancesOfSightings_true(){
       Sightings firstSighting = new Sightings("ZoneA", "west");
       firstSighting.save();
       Sightings secondSighting = new Sightings("ZoneB", "East");
       secondSighting.save();
       assertEquals(true, Sightings.all().get(0).equals(firstSighting));
       assertEquals(true, Sightings.all().get(1).equals(secondSighting));
    }

//    @Test
//    public void addRanger_addsRangerToSightings(){
//        models.Sightings testSighting = new models.Sightings("ZoneA", "west");
//        testSighting.save();
//        models.Ranger testRanger = new models.Ranger("Rock Stock", "Nyayo");
//        testRanger.save();
//        testSighting.addRanger(testRanger);
//        models.Ranger savedRanger = testSighting.getRangers().get(0);
//        assertTrue(testRanger.equals(savedRanger));
//    }

//    @Test
//
//    public void getRangers_returnsAllRangers_List(){
//        models.Ranger ranger = new models.Ranger("Rock", "Nyayo");
//        ranger.save();
//        assertEquals(ranger.getAll());
//

}
