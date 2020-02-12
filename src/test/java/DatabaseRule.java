import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {

    @Override

    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "hillary", "hillary");
    }

    @Override

    protected void after() {
        try(Connection connect = DB.sql2o.open()) {
            String deleteRangerQuery = "DELETE FROM ranger *;";
            connect.createQuery(deleteRangerQuery).executeUpdate();
        }
    }
}
