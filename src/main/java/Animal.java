public class Animal {

    private int rangerId;
    private String name;

    public Animal(int rangerId, String name){

        this.rangerId = rangerId;
        this.name = name;
    }

    public int getRangerId(){
        return rangerId;
    }

    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object anotherAnimal){
        if(!(anotherAnimal instanceof Animal)){
            return false;
        } else {

            Animal newAnimal = (Animal) anotherAnimal;
            return this.getRangerId() == newAnimal.getRangerId() &&
                    this.getName() == newAnimal.getName();
        }
    }
}
