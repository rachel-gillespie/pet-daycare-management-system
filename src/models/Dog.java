package models;

import java.util.Objects;

/**
 * The responsibility for this concrete class is to extend Pet and implement the class for a Dog.
 */

public class Dog extends Pet {

    private String breed = ""; //20 chars
    private boolean dangerousBreed = false;
    private static float DANGEROUS_DAILY_RATE = 40;
    private static float NONDANGEROUS_DAILY_RATE = 30;

    public Dog(String name, int age, char sex, String owner, boolean neutered, int id, String breed, boolean dangerousBreed) {
        super(owner, age, sex, id, name, neutered);
        this.breed = breed;
        this.dangerousBreed = dangerousBreed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public boolean isDangerousBreed() {
        return dangerousBreed;
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
        return "Dog{" +
                "breed='" + breed + '\'' +
                ", dangerousBreed=" + dangerousBreed +
                "} " + super.toString();
    }
}
