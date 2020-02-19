package models;

import java.util.Objects;
import Data.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
public class Sightings implements DatabaseManagement {

    private String name;
    private String location;
    private int id;

    public Sightings(String name, String location){
        this.name = name;
        this.location = location;
        this.id = id;
    }

    public Sightings() {

    }

    public static List<Sightings> all() {
        String sql = "SELECT * FROM sightings";
        try(Connection connect = DB.sql2o.open()){
            return connect.createQuery(sql).executeAndFetch(Sightings.class);
        }
    }

    public String getName(){
        return name;
    }

    public String getLocation(){
        return location;
    }

    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sightings sightings = (Sightings) o;
        return id == sightings.id &&
                name.equals(sightings.name) &&
                location.equals(sightings.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, id);
    }

    @Override
    public void save() {
        try(Connection connect = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (name, location) VALUES (:name, :location)";
            this.id = (int) connect.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("location", this.location)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }

    @Override
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM sightings WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
            String joinDeleteQuery = "DELETE FROM sightings_rangers WHERE sighting_id = :sightingId";
            con.createQuery(joinDeleteQuery)
                    .addParameter("sightingId", this.getId())
                    .executeUpdate();
        }
    }

    public void addRanger(Ranger ranger) {
        try(Connection connect = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings_rangers(sighting_id, ranger_id) VALUES (:sighting_id, :ranger_id)";
            connect.createQuery(sql)
                    .addParameter("sighting_id", this.getId())
                    .addParameter("ranger_id", ranger.getId())
                    .executeUpdate();
        }
    }

    public List<Ranger> getRangers() {
        try(Connection connect = DB.sql2o.open()) {
            String joinQuery = "SELECT ranger_id FROM sightings_rangers WHERE sighting_id = :sighting_id";

            List<Integer> rangerIds = connect.createQuery(joinQuery)
                    .addParameter("sighting_id", this.getId())
                    .executeAndFetch(Integer.class);

            List<Ranger> rangers = new ArrayList<Ranger>();

            for (Integer rangerId : rangerIds){
                String rangerQuery = "SELECT * FROM ranger WHERE id = :rangerId";
                Ranger ranger = connect.createQuery(rangerQuery)
                        .addParameter("rangerId", rangerId)
                        .executeAndFetchFirst(Ranger.class);
                ranger.add(ranger);
            }
            return rangers;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setId(int id) {
        this.id = id;
    }
}