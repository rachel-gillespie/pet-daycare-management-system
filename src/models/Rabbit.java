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
    public Rabbit(String owner, String petName, int age, char sex, int id, boolean neutered, boolean[] daysAttending, boolean litterTrained, String breed) {
        super(owner, petName, age, sex, id, neutered, daysAttending);
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
        return "Rabbit{" + "litter trained= " + litterTrained + ", breed= '" + breed + '\'' + "} " + super.toString();
    }
}
