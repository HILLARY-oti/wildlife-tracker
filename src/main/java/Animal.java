import java.util.List;
import java.util.Objects;

import org.sql2o.*;

public class Animal {

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
            return connect.createQuery(sql).executeAndFetch(Animal.class);
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

    public void save() {
        try(Connection connect = DB.sql2o.open()) {
            String sql = "INSERT INTO animals(rangerid, name) VALUES (:rangerId, :name)";
            this.id = (int) connect.createQuery(sql, true)
                    .addParameter("rangerId", this.rangerId)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static Animal find(int id) {
        try (Connection connect = DB.sql2o.open()) {
            String sql = "SELECT*FROM animals where id=:id";
            Animal animal = connect.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }
    }


    public int getId(){
        return id;
    }
}
