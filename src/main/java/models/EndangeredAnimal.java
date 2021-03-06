package models;

import Data.*;
import org.sql2o.*;

import java.util.List;
import java.util.Objects;

public class EndangeredAnimal implements DatabaseManagement {

    private int rangerId;
    private String name;
    private String health;
    private String age;
    private int id;

    public EndangeredAnimal(int rangerId, String name, String health, String age) {
        this.rangerId = rangerId;
        this.name = name;
        this.health = health;
        this.age = age;
        this.id = id;
    }

    public static List<EndangeredAnimal> all() {
        String sql = "SELECT * FROM animals";
        try(Connection connect = DB.sql2o.open()) {
            return connect.createQuery(sql).executeAndFetch(EndangeredAnimal.class);
        }
    }

    public static EndangeredAnimal find(int id) {
        try(Connection connect = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id = :id";
            EndangeredAnimal endangeredAnimal = connect.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(EndangeredAnimal.class);
            return endangeredAnimal;

        }
    }

    public int getRangerId() {
        return rangerId;
    }

    public String getName() {
        return name;
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }

    public  int getId(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndangeredAnimal that = (EndangeredAnimal) o;
        return rangerId == that.rangerId &&
                id == that.id &&
                name.equals(that.name) &&
                health.equals(that.health) &&
                age.equals(that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rangerId, name, health, age, id);
    }

    @Override
    public void save() {
        try(Connection connect = DB.sql2o.open()) {
            String sql = "INSERT INTO animals(rangerId, name, health, age) VALUES (:rangerId, :name, :health, :age)";
            this.id = (int) connect.createQuery(sql, true)
                    .addParameter("rangerId", this.rangerId)
                    .addParameter("name", this.name)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
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

    public void setRangerId(int rangerId) {
        this.rangerId = rangerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }
}
