package main;

import controllers.DayCare;
import models.*;
import utils.ScannerInput;
import utils.Utilities;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The responsibility of the Driver class is to run the app and perform I/O with the user.
 */

public class Driver {

    private static DayCare daycare;

    public static void main(String[] args) {
        daycare = new DayCare("peepeepoopoo", 20);
        new Driver();

    }

    public Driver() {

        //TODO - load all data

        runMenu();
    }
    //----------------------------------------------------------------------------
    // Private methods for displaying the menu and processing the selected options
    //----------------------------------------------------------------------------

    private int mainMenu() {

        //TODO write menu that user will see

        int option = ScannerInput.readNextInt("""
                ------------------------------------------------------------------
                |                        Pet Day Care                            |
                ------------------------------------------------------------------                                                              | 
                |   1) Pets CRUD Menu                                            |
                |   2) Reports Menu                                              | 
                ------------------------------------------------------------------
                |   3) Search Pets                                               |
                |   4) Sort Pets                                                 |            
                ------------------------------------------------------------------
                |   5) Save pets to pets.xml                                     |  
                |   6) Load pets from pets.xml                                   |  
                ------------------------------------------------------------------
                |   0)  Exit                                                     |  
                ------------------------------------------------------------------
                ==>> """);
        return option;
    }

    private void runMenu() {
    int option = mainMenu();

    while (option != 0) {
        switch (option) {
            case 1 -> crudMenu();
            case 2 -> reportsMenu();
            case 3 -> searchPets();
            case 4 -> sortPets();
            case 5 -> savePets();
            case 6 -> loadPets();
            case 0 -> exitApp();
            default -> System.out.println("Invalid option entered: " + option);
        }
    }
        //TODO - write code to call appropriate method based on value in option

    }

    private void crudMenu() {
        int option = ScannerInput.readNextInt("""
                    -------------------------------------
                    |           Pets CRUD Menu          |
                    -------------------------------------
                    |   1) Add a new Pet                |
                    |   2) List all Pets                |
                    |   3) Update Pet Information       |
                    |   4) Delete a Pet                 |
                    -------------------------------------
                    |   0) Return to Main Menu          |
                    -------------------------------------
                    ==>> """);

        while (option != 0) {
            switch (option) {
                case 1 -> addPetMenu();
                case 2 -> daycare.listAllPets();
                case 3 -> {

                }
                case 4 -> {

                }
                case 0 -> mainMenu();
                default -> System.out.println("Invalid option entered: " + option);
            }
        }
    }

    private void reportsMenu() {
        int option = ScannerInput.readNextInt("""
                    ---------------------------------------------
                    |              Pets Report Menu             |
                    ---------------------------------------------
                    |   1) List all Pets                        |
                    |   2) List all Dogs                        |
                    |   3) List all Cats                        |
                    |   4) List all Dangerous Dogs              |
                    |   5) List all Indoor Cats                 |
                    |   6) List all Dogs older than an age      |
                    |   7) List all Cats by Favourite Toy       |
                    |   8) List all Pets that are neutered      |
                    |   9) Produce Weekly Income Report         |
                    ---------------------------------------------
                    |   0) Return to Main Menu                  |
                    ---------------------------------------------
                    ==>> """);

        while (option != 0) {
            switch (option) {
                case 1 -> daycare.listAllPets();
                case 2 -> daycare.listAllDogs();
                case 3 -> daycare.listAllCats();
                case 4 -> daycare.listAllDangerousDogs();
                case 5 -> daycare.listAllIndoorCats();
                case 6 -> {
                    int age = ScannerInput.readNextInt("Enter an age: ");
                    daycare.listAllDogsOlderThan(age);
                }
                case 7 -> {
                    String toy = ScannerInput.readNextLine("Enter a toy: ");
                    daycare.listAllCatsByFavToy(toy);
                }
                case 8 -> daycare.listAllNeuteredPets();
                case 9 -> daycare.getWeeklyIncome();
                case 0 -> mainMenu();
                default -> System.out.println("Invalid option entered: " + option);
            }
        }
    }

    private void addPetMenu() {
        int option = ScannerInput.readNextInt("""
                    -------------------------------------
                    |           Add Pet Menu            |
                    -------------------------------------
                    |   1) Add a new Dog                |
                    |   2) Add a new Cat                |
                    -------------------------------------
                    |   0) Return to Main Menu          |
                    -------------------------------------
                    ==>> """);

        while (option != 0) {
            switch (option) {
                case 1 -> {
                    String owner = ScannerInput.readNextLine("Enter Owner's Name: ");
                    int age = ScannerInput.readNextInt("Enter Pet's age: ");
                    char sex = 'x';
                    while((int) sex != 'm' ^(int) sex != 'f'){
                        sex = ScannerInput.readNextChar("Enter Pet's sex (m/f): ");
                        switch (sex){
                            case 'm' -> sex = 'm';
                            case 'f' ->  sex = 'f';
                            default -> System.out.println("Invalid option entered: " + sex);
                        }
                    }
                    boolean[] daysAttending = new boolean[5];
                    String[] weekDays = {"Mon","Tues","Wed","Thurs","Fri"};
                    for(int i =0; i < 5; i++){
                        daysAttending[i] = Utilities.YNtoBoolean(ScannerInput.readNextChar("Attending "+weekDays[i]+ " y/n"));
                    }
                    int id = ScannerInput.readNextInt("Enter Pet id number: ");
                    while (!daycare.isValidId(id)) {
                        id = ScannerInput.readNextInt("Invalid Pet id, Enter another Pet id number: ");
                    }
                    String name = ScannerInput.readNextLine("Enter a name: ");
                    char neuteredInput = 'x';
                    boolean neutered = false;
                    while(neuteredInput != ('y') || neuteredInput != 'n'){
                        neuteredInput = ScannerInput.readNextChar("Is pet neutered? (y/n): ");
                        switch (neuteredInput){
                            case 'y' -> neutered = true;
                            case 'n' ->  neutered = false;
                            default -> System.out.println("Invalid option entered: " + neuteredInput);
                        }
                    }
                }
                case 2 -> {

                }
                default -> System.out.println("Invalid option entered: " + option);
            }
        }
    }

    private void exitApp() {

        System.out.println("Exiting....");
        System.exit(0);
    }

//todo update methods counting methods
    //---------------------
    //  General Menu Items
    //---------------------

//TODO - write all the methods that are called from your menu

    private void searchPets() {

    }

    private void sortPets() {

    }

    //---------------------------------------------------------------------
    //  Options 5 and 6 - Save and Load Pets
    //---------------------------------------------------------------------

    //save all the pets in the DayCare to a file on the hard disk
    private void savePets() {
        try {
            System.out.println("Saving to file: " + daycare.fileName());
            daycare.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    //load all the posts into the DayCare from a file on the hard disk
    private void loadPets() {
        try {
            System.out.println("Loading from file: " + daycare.fileName());
            daycare.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }

// TODO search by different criteria i.e. look at the list methods and give options based on that.

    //---------------------
    //  Helper Methods
    //---------------------

//TODO- write any helper methods that are required

}