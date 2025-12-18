package main;

import controllers.DayCare;
import models.*;
import utils.ScannerInput;

import java.util.ArrayList;
import java.util.Scanner;

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
        return ScannerInput.readNextInt("==>> ");
    }

    private void runMenu() {

        int option = mainMenu();
        //TODO - write code to call appropiate method based on value in option

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
    //---------------------
    //  Search
    //---------------------
// TODO search by different criteria i.e. look at the list methods and give options based on that.

    //---------------------
    //  Helper Methods
    //---------------------

//TODO- write any helper methods that are required

}
