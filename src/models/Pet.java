package models;

import java.util.Arrays;

/**
 * The responsibility for this abstract class is to be the super class for the projects.
 */

public abstract class Pet {

    private String owner = ""; //truncates to 20 chars
    private int age = 0;
    private char sex = 'f';
    private boolean[] daysAttending = new boolean[5];
    private int id = 1000;
    private String name = ""; //truncates to 30 chars
    private boolean neutered = false;

    public Pet(String owner, int age, char sex, int id, String name, boolean neutered) {
        this.owner = owner;
        this.age = age;
        this.sex = sex;
        this.id = id;
        this.name = name;
        this.neutered = neutered;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public boolean[] getDaysAttending() {
        return daysAttending;
    }

    public void setDaysAttending(boolean[] daysAttending) {
        this.daysAttending = daysAttending;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNeutered() {
        return neutered;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    public abstract double calculateWeeklyFee();

    public void checkIn(int dayIndex) {
        // if valid dayIndex >=0 <= 5
        if ((dayIndex >= 0) && (dayIndex <= 5)) {
            //  assign that element of the array to true
            daysAttending[dayIndex] = true;
        }
    }

    public void checkOut(int dayIndex) {
        // if valid dayIndex >=0 <= 5
        if ((dayIndex >= 0) && (dayIndex <= 5)) {
            //  assign that element of the array to false.
            daysAttending[dayIndex] = false;
        }
    }

    public int numberOfDaysInKennel() {
        // iterates through array
        int days = 0;
        for (int i = 0; i <= daysAttending.length; i++) {
            //  Adds 1 to a variable if the element of the array is true
            if (daysAttending[i]) {
                days++;
            }
        }
        // returns variable
        return days;
    }

    public String toString() {
        return "Pet{" +
                "owner='" + owner + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", daysAttending=" + Arrays.toString(daysAttending) +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
