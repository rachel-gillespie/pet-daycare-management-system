package models;

/**
 * The responsibility for this concrete class is to extend Pet and implement the class for a Cat.
 */

public class Cat extends Pet {

    //------------------------------------
    //              FIELDS
    //------------------------------------
    private boolean indoorCat = true;
    private String favouriteToy = "not known";

    //------------------------------------
    //            CONSTRUCTOR
    //------------------------------------
    public Cat(String owner, String petName, int age, char sex, int id, boolean neutered, boolean[] daysAttending, boolean indoorCat, String favouriteToy) {
        super(owner, petName, age, sex, id, neutered, daysAttending);
        this.indoorCat = indoorCat;
        this.favouriteToy = favouriteToy;
    }

    //------------------------------------
    //              GETTERS
    //------------------------------------
    public boolean isIndoorCat() {
        return indoorCat;
    }

    public String getFavouriteToy() {
        return favouriteToy;
    }

    //------------------------------------
    //              SETTERS
    //------------------------------------
    public void setIndoorCat(boolean indoorCat) {
        this.indoorCat = indoorCat;
    }

    public void setFavouriteToy(String favouriteToy) {
        this.favouriteToy = favouriteToy;
    }

    @Override
    public double calculateWeeklyFee() {
        // base rate is 20 per day,
        //an extra 5 is added if the cat is an indoor cat
        if (indoorCat) {
            return 25 * numberOfDaysInKennel();
        } else {
            return 20 * numberOfDaysInKennel();
        }
    }

    @Override
    public String toString() {
        return "Cat{" + "Indoor Cat = " + indoorCat + ", Favourite Toy = " + favouriteToy + '\'' + "} " + super.toString();
    }
}



