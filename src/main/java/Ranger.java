import java.util.Collection;
import java.util.List;
import org.sql2o.*;

public class Ranger {
    private String name;
    private String crew;
    private int id;

    public Ranger(String name, String crew) {

        this.name = name;
        this.crew = crew;
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
                    .executeAndFetch(Animal.class);
        }
    }

    public List<EndangeredAnimal> getEndangeredAnimals() {
        try (Connection connect = DB.sql2o.open()) {
            String sql = "SELECT * FROM endangeredAnimals where rangerId = :id";
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

    public void save() {
        try (Connection connect = DB.sql2o.open()) {
            String sql = "INSERT INTO ranger(name, crew) VALUES (:name, :crew)";
            this.id = (int) connect.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("crew", this.crew)
                    .executeUpdate()
                    .getKey();
        }
    }

    public int getId(){
        return id;
    }


}
