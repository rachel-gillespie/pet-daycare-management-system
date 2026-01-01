package main;

import controllers.DayCare;
import models.*;
import utils.ScannerInput;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The responsibility of the Driver class is to run the app and perform I/O with the user.
 */

public class Driver {

    DayCare daycare;

    public static void main(String[] args) {

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
            default -> System.out.println("Invalid option entered: " + option);
        }
    }
        //TODO - write code to call appropriate method based on value in option

        exitApp();

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

    private void crudMenu() {
        int option = ScannerInput.readNextInt("""
                    ---------------------------
                    |   1) Add a Message Post |
                    |   2) Add a Photo Post   |
                    |   3) Add an Event Post  |
                    ---------------------------
                    ==>> """);
    }

    private void reportsMenu() {
        int option = ScannerInput.readNextInt("""
                    ---------------------------
                    |   1) Add a Message Post |
                    |   2) Add a Photo Post   |
                    |   3) Add an Event Post  |
                    ---------------------------
                    ==>> """);
    }

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