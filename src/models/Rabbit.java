package models;

/**
 * The responsibility for this concrete class is to extend Pet and implement the class for a Rabbit.
 */

public class Rabbit extends Pet {

    //------------------------------------
    //              FIELDS
    //------------------------------------
    private boolean litterTrained = true;
    private String breed = "";

    //------------------------------------
    //            CONSTRUCTOR
    //------------------------------------
    public Rabbit(String owner, int age, char sex, int id, String name, boolean neutered, boolean litterTrained, String breed) {
        super(owner, age, sex, id, name, neutered);
        this.litterTrained = litterTrained;
        this.breed = breed;
    }

    //------------------------------------
    //              GETTERS
    //------------------------------------
    public boolean isLitterTrained() {
        return litterTrained;
    }

    public String getBreed() {
        return breed;
    }

    //------------------------------------
    //              SETTERS
    //------------------------------------
    public void setLitterTrained(boolean litterTrained) {
        this.litterTrained = litterTrained;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public double calculateWeeklyFee() {
        // base rate is 15 per day,
        // an extra 3 is added if the rabbit is not litter trained
        if (litterTrained) {
            return 15 * numberOfDaysInKennel();
        } else {
            return 18 * numberOfDaysInKennel();
        }
    }

    @Override
    public String toString() {
        return "Rabbit{" + "litter_trained=" + litterTrained + ", breed='" + breed + '\'' + "} " + super.toString();
    }
}
