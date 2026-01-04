package models;

import java.util.Objects;

/**
 * The responsibility for this concrete class is to extend Pet and implement the class for a Dog.
 */

public class Dog extends Pet {

    //------------------------------------
    //              FIELDS
    //------------------------------------
    private String breed = ""; //20 chars
    private boolean dangerousBreed = false;
    private static float DANGEROUS_DAILY_RATE = 40;
    private static float NONDANGEROUS_DAILY_RATE = 30;

    //------------------------------------
    //            CONSTRUCTOR
    //------------------------------------
    public Dog(String owner, String petName, int age, char sex, int id, boolean neutered, boolean[] daysAttending, String breed, boolean dangerousBreed) {
        super(owner, petName, age, sex, id, neutered, daysAttending);
        this.breed = breed;
        this.dangerousBreed = dangerousBreed;
    }

    //------------------------------------
    //              GETTERS
    //------------------------------------
    public String getBreed() {
        return breed;
    }

    public boolean isDangerousBreed() {
        return dangerousBreed;
    }

    //------------------------------------
    //              SETTERS
    //------------------------------------
    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setDangerousBreed(boolean dangerousBreed) {
        this.dangerousBreed = dangerousBreed;
    }

    @Override
    public double calculateWeeklyFee() {
        // gets daily rate for dog depending on whether dangerous or not
        //multiply rate by number of days in kennel and returns answer
        if (dangerousBreed) {
            return DANGEROUS_DAILY_RATE * numberOfDaysInKennel();
        } else {
            return NONDANGEROUS_DAILY_RATE * numberOfDaysInKennel();
        }
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Dog dog)) return false;

        return dangerousBreed == dog.dangerousBreed && Objects.equals(breed, dog.breed);
    }

    @Override
    public String toString() {
        return "Dog{" + "Breed = " + breed + '\'' + ", Dangerous Breed = " + dangerousBreed + "} " + super.toString();
    }
}
