import java.util.List;
import org.sql2o.*;

public class Ranger {
    private String name;
    private String crew;

    public Ranger(String name, String crew) {

        this.name = name;
        this.crew = crew;
    }

    public static List<Ranger> all() {
        String sql = "SELECT*FROM rangers";
        try (Connection connect = DB.sql2o.open()) {
            return connect.createQuery(sql).executeAndFetch(Ranger.class);
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
            String sql = "INSERT INTO rangers(name, crew) VALUES (:name, :crew)";
            connect.createQuery(sql)
                    .addParameter("name", this.name)
                    .addParameter("crew", this.crew)
                    .executeUpdate();
        }
    }
}
