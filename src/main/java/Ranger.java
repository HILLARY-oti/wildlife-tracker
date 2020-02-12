public class Ranger {
    private String name;
    private String crew;

    public Ranger(String name, String crew) {

        this.name = name;
        this.crew = crew;
    }

    public String getName(){
        return name;
    }

    public String getCrew(){
        return crew;
    }

    @Override

    public boolean equals(Object anotherRanger){
        if(!(anotherRanger instanceof Ranger)){
            return false;
        } else {
            Ranger newRanger = (Ranger) anotherRanger;
            return this.getName().equals(newRanger.getName()) &&
                    this.getCrew().equals(newRanger.getCrew());
        }
    }
}
