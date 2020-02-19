import java.util.List;
import java.util.Objects;

import org.sql2o.*;

public class Animal implements DatabaseManagement{

    private int rangerId;
    private String name;
    private int id;

    public Animal(int rangerId, String name){

        this.id = id;
        this.rangerId = rangerId;
        this.name = name;
    }

    public static List<Animal> all() {

        String sql = "SELECT*FROM animals";
        try(Connection connect = DB.sql2o.open()) {
            return connect.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animal.class);
        }
    }

    public int getRangerId(){
        return rangerId;
    }

    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return rangerId == animal.rangerId &&
                id == animal.id &&
                name.equals(animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rangerId, name, id);
    }

    @Override
    public void save() {
        try(Connection connect = DB.sql2o.open()) {
            String sql = "INSERT INTO animals(rangerId, name) VALUES (:rangerId, :name)";
            this.id = (int) connect.createQuery(sql, true)
                    .addParameter("rangerId", this.rangerId)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }

    @Override
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM animals WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
            String joinDeleteQuery = "DELETE FROM animals_ranger WHERE animals_id = :animalsId";
            con.createQuery(joinDeleteQuery)
                    .addParameter("animalsId", this.getId())
                    .executeUpdate();
        }
    }

    public static Animal find(int id) {
        try (Connection connect = DB.sql2o.open()) {
            String sql = "SELECT*FROM animals where id=:id";
            Animal animal = connect.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }
    }


    public void setRangerId(int rangerId) {
        this.rangerId = rangerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
