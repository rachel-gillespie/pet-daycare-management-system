package models;

import utils.Utilities;

import java.util.Arrays;

/**
 * The responsibility for this abstract class is to be the super class for the projects.
 */

public abstract class Pet { // Object Type/ Class Name. Pet must be abstract as it has an abstract method

    //------------------------------------
    //              FIELDS                  i.e. the attributes of the class
    //------------------------------------
    private String owner = ""; //truncates to 20 chars
    private int age = 0;
    private char sex = 'f';
    private boolean[] daysAttending = new boolean[5];
    private int id = 1000;
    private String name; //truncates to 30 chars
    private boolean neutered = false;

    //------------------------------------
    //            CONSTRUCTOR
    //------------------------------------
    public Pet(String owner, int age, char sex, int id, String name, boolean neutered) { // these are the local variables/parameters
        this.owner = owner; //this keyword is used to distinguish between the variables, this refers to the current object fields
        this.age = age;
        this.sex = sex;
        this.id = id;
        this.name = name;
        this.neutered = neutered;
    }

    //------------------------------------
    //              GETTERS
    //------------------------------------
    public String getOwner() {
        return owner;
    }

    public int getAge() {
        return age;
    }

    public char getSex() {
        return sex;
    }

    public boolean[] getDaysAttending() {
        return daysAttending;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isNeutered() {
        return neutered;
    }

    //------------------------------------
    //              SETTERS
    //------------------------------------
    public void setOwner(String owner) {
        this.owner = Utilities.truncateString(owner, 20);
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public void setDaysAttending(boolean[] daysAttending) {
        this.daysAttending = daysAttending;
    }

    public void setId(int id) {
        if ((id >= 1000) && id <= 9999) {
            this.id = id;
        }
    }

    public void setName(String name) {
        this.name = Utilities.truncateString(name, 30);
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    public abstract double calculateWeeklyFee(); // all subclasses of this class will implement this method as part of its code

    public void checkIn(int dayIndex) { // returns nothing. A number indicating the day of the week is passed to the method.
        // if valid dayIndex >=0 < 5
        if ((dayIndex >= 0) && (dayIndex < 5)) {
            //  assign that element of the array to true
            daysAttending[dayIndex] = true;
        }
    }

    public void checkOut(int dayIndex) { // returns nothing. A number indicating the day of the week is passed to the method.
        // if valid dayIndex >=0 < 5
        if ((dayIndex >= 0) && (dayIndex < 5)) {
            //  assign that element of the array to false.
            daysAttending[dayIndex] = false;
        }
    }

    public int numberOfDaysInKennel() { //
        // iterates through array
        int days = 0;
        for (int i = 0; i < daysAttending.length; i++) {
            //  Adds 1 to a variable if the element of the array is true
            if (daysAttending[i]) {
                days++;
            }
        }
        // returns variable
        return days;
    }

    public String toString() { // builds and returns a String containing a user-friendly representation of the object state
        return "Pet{" + "Owner = " + owner + '\'' + ", Age = " + age + ", Sex = " + sex + ", Days Attending = " + Arrays.toString(daysAttending) + ", ID = " + id + ", Name = " + name + '\'' + '}';
    }
}
