import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Ranger implements DatabaseManagement{
    private String name;
    private String crew;
    private int id;

    public Ranger(String name, String crew) {

        this.name = name;
        this.crew = crew;
    }

    public static List<Ranger> getAll() {
        String sql = "SELECT*FROM ranger";
        try (Connection connect = DB.sql2o.open()) {
            return connect.createQuery(sql).executeAndFetch(Ranger.class);
        }
    }

    public static List<Ranger> all() {
        String sql = "SELECT*FROM ranger";
        try (Connection connect = DB.sql2o.open()) {
            return connect.createQuery(sql).executeAndFetch(Ranger.class);
        }
    }


    public List<Animal> getAnimals() {
        try (Connection connect = DB.sql2o.open()) {
            String sql = "SELECT*FROM animals where rangerId=:id";
            return connect.createQuery(sql)
                    .addParameter("id", this.id)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animal.class);
        }
    }

    public List<EndangeredAnimal> getEndangeredAnimals() {
        try (Connection connect = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where rangerId = :id";
            return connect.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(EndangeredAnimal.class);
        }
    }

    public static Ranger find(int id) {
        try (Connection connect = DB.sql2o.open()) {
            String sql = "SELECT*FROM ranger where id=:id";
            Ranger ranger = connect.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Ranger.class);
            return ranger;
        }
    }

    public String getName() {
        return name;
    }

    public String getCrew() {
        return crew;
    }

    @Override

    public boolean equals(Object anotherRanger) {
        if (!(anotherRanger instanceof Ranger)) {
            return false;
        } else {
            Ranger newRanger = (Ranger) anotherRanger;
            return this.getName().equals(newRanger.getName()) &&
                    this.getCrew().equals(newRanger.getCrew());
        }
    }

    @Override
    public void save() {
        try (Connection connect = DB.sql2o.open()) {
            String sql = "INSERT INTO ranger(name, crew) VALUES (:name, :crew)";
            this.id = (int) connect.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("crew", this.crew)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }

    @Override
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM ranger WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
            String joinDeleteQuery = "DELETE FROM sightings_ranger WHERE ranger_id = :rangerId";
            con.createQuery(joinDeleteQuery)
                    .addParameter("rangerId", this.getId())
                    .executeUpdate();
        }
    }

    public int getId(){
        return id;
    }


    public void add(Ranger ranger) {
        try(Connection connect = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings_rangers(sighting_id, ranger_id) VALUES (:sighting_id, :ranger_id)";
            connect.createQuery(sql)
                    .addParameter("sighting_id", this.getId())
                    .addParameter("ranger_id", ranger.getId())
                    .executeUpdate();
        }
    }

    public List<Sightings> getSightings() {
        try(Connection connect = DB.sql2o.open()){
            String joinQuery = "SELECT sighting_id FROM sightings_rangers WHERE sighting_id = :sighting_id";
            List<Integer> sightingIds = connect.createQuery(joinQuery)
                    .addParameter("sighting_id", this.getId())
                    .executeAndFetch(Integer.class);

            List<Sightings> sightings = new ArrayList<Sightings>();

            for (Integer sightingId:sightingIds) {
                String sightingQuery = "SELECT * FROM sightings WHERE id = :sightingId";
                Sightings sighting = (Sightings) connect.createQuery(sightingQuery)
                        .addParameter("sightingId", sightingId)
                        .executeAndFetch(Sightings.class);
                sightings.add(sighting);
            }
            return sightings;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public void setId(int id) {
        this.id = id;
    }
}
