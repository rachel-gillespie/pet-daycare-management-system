package models;

/**
 * The responsibility for this concrete class is to extend Pet and implement the class for a Cat.
 */

public class Cat extends Pet {

    private boolean indoorCat = true;
    private String favouriteToy = "not known";

    public Cat(String owner, int age, char sex, int id, String name, boolean indoorCat, String favouriteToy) {
        super(owner, age, sex, id, name);
        this.indoorCat = indoorCat;
        this.favouriteToy = favouriteToy;
    }

    public boolean isIndoorCat() {
        return indoorCat;
    }

    public void setIndoorCat(boolean indoorCat) {
        this.indoorCat = indoorCat;
    }

    public String getFavouriteToy() {
        return favouriteToy;
    }

    public void setFavouriteToy(String favouriteToy) {
        this.favouriteToy = favouriteToy;
    }

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
        return "Cat{" +
                "indoorCat=" + indoorCat +
                ", favouriteToy='" + favouriteToy + '\'' +
                "} " + super.toString();
    }
}



