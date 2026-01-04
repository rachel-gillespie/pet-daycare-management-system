package main;

import com.thoughtworks.xstream.io.xml.DocumentWriter;
import controllers.DayCare;
import models.*;
import utils.CatToyUtility;
import utils.DogBreedUtility;
import utils.ScannerInput;
import utils.Utilities;

import java.security.PrivateKey;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The responsibility of the Driver class is to run the app and perform I/O with the user.
 */

public class Driver {

    private static DayCare daycare;

    public static void main(String[] args) {
        daycare = new DayCare("Paws and Whistles Day Care", 0);
        new Driver();
    }

    public Driver() {
        System.out.println("");
        runMenu();
    }

    //----------------------------------------------------------------------------
    // Private methods for displaying the menu and processing the selected options
    //----------------------------------------------------------------------------

    private int mainMenu() {
        int option = ScannerInput.readNextInt("""
                -----------------------------------------
                |         WELCOME TO THE DAYCARE         |
                -----------------------------------------
                |   1) Register a new pet                |
                |   2) Pets in the Daycare               |
                |   3) Update & Remove Pets              |
                |   4) Check Pet In/Out                  |
                |   5) Search & Sort Pets                |
                |   6) Today's Attendance                |
                -----------------------------------------
                |   7) DayCare Settings                  |
                -----------------------------------------
                |   8) Save pets to pets.xml             |
                |   9) Load pets from pets.xml           |
                -----------------------------------------
                |   0)  Exit                             |
                -----------------------------------------
                ==>>""");
        return option;
    }

    private void runMenu() {
        int option = mainMenu();

        while (option != 0) {

            switch (option) {
                case 1 -> addPetMenu();
                case 2 -> reportsMenu();
                case 3 -> crudMenu();
                case 4 -> checkPetInOut();
                case 5 -> searchAndSortMenu();
                case 6 -> todaysAttendance();
                case 7 -> daycareSettings();
                case 8 -> savePets();
                case 9 -> loadPets();
                default -> System.out.println("Invalid option entered: " + option);
            }
            //pause the program so that the user can read what we just printed to the terminal window
            ScannerInput.readNextLine("\nPress enter key to continue...");

            //display the main menu again
            option = mainMenu();
        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting...bye");
        System.exit(0);

    }

    private void addPetMenu() {
        int option = ScannerInput.readNextInt("""
                -------------------------------------
                |         REGISTER A NEW PET        |
                -------------------------------------
                |   1) Dog                          |
                |   2) Cat                          |
                -------------------------------------
                |   0) Return to Main Menu          |
                -------------------------------------
                ==>>""");

        String owner = ScannerInput.readNextLine("Enter Owner's Name: ");
        int age = ScannerInput.readNextInt("Enter Pet's age: ");
        char sex = ScannerInput.readNextChar("Enter Pet's sex (m/f): ");
        while (sex != 'm' && sex != 'f') {
            sex = ScannerInput.readNextChar("Invalid option " + sex + ", Enter Pet's sex (m/f): ");
        }
        boolean[] daysAttending = new boolean[5];
        String[] weekDays = {"Mon", "Tues", "Wed", "Thurs", "Fri"};
        for (int i = 0; i < 5; i++) {
            daysAttending[i] = Utilities.YNtoBoolean(ScannerInput.readNextChar("Attending " + weekDays[i] + "? (y/n)"));
        }
        int id = ScannerInput.readNextInt("Enter Pet id number: ");
        while (!daycare.isValidId(id)) {
            id = ScannerInput.readNextInt("Invalid Pet id, Enter another Pet id number: ");
        }
        String petName = ScannerInput.readNextLine("Enter the Pet's Name: ");
        char neuteredInput = ScannerInput.readNextChar("Is pet neutered? (y/n): ");
        while (neuteredInput != ('y') && neuteredInput != 'n') {
            neuteredInput = ScannerInput.readNextChar("Invalid option " + neuteredInput + " Is pet neutered? (y/n): ");
        }
        boolean neutered = Utilities.YNtoBoolean(neuteredInput);

        boolean isAdded = false;

        switch (option) {
            case 1 -> {
                String breed = ScannerInput.readNextLine("What breed is your dog?: ");
                while (!DogBreedUtility.checkBreed(breed)) {
                    breed = ScannerInput.readNextLine("Invalid breed, What breed is your dog?: ");
                }
                char dangerousBreedInput = ScannerInput.readNextChar("Is dog dangerous breed? (y/n): ");
                while (dangerousBreedInput != ('y') && dangerousBreedInput != 'n') {
                    dangerousBreedInput = ScannerInput.readNextChar("Invalid option " + dangerousBreedInput + " Is dog dangerous breed? (y/n): ");
                }
                boolean dangerousBreed = Utilities.YNtoBoolean(dangerousBreedInput);

                Dog dog = new Dog(owner, petName, age, sex, id, neutered, daysAttending, breed, dangerousBreed);
                isAdded = daycare.addPet(dog);
            }
            case 2 -> {
                char indoorCatInput = ScannerInput.readNextChar("Is the cat an indoor cat? (y/n): ");
                while (indoorCatInput != 'y' && indoorCatInput != 'n') {
                    indoorCatInput = ScannerInput.readNextChar("Invalid option " + indoorCatInput + ", Is the cat an indoor cat? (y/n): ");
                }
                boolean indoorCat = Utilities.YNtoBoolean(indoorCatInput);
                System.out.println(CatToyUtility.getCatToys());
                String favouriteToy = ScannerInput.readNextLine("What is the cat's favourite toy?: ");
                while (!CatToyUtility.isCatToy(favouriteToy.toUpperCase())) {
                    favouriteToy = ScannerInput.readNextLine("Invalid cat toy, What is the cat's favourite toy?: ");
                }

                Cat cat = new Cat(owner, petName, age, sex, id, neutered, daysAttending, indoorCat, favouriteToy);
                isAdded = daycare.addPet(cat);
            }
            case 0 -> runMenu();
            default -> System.out.println("Invalid option entered: " + option);
        }
        if (isAdded) {
            System.out.println("Pet Added Successfully");
        } else {
            System.out.println("No Pet Added");
        }
    }

    private void reportsMenu() {
        int option = ScannerInput.readNextInt("""
                ---------------------------------------------
                |            PETS IN THE DAYCARE            |
                ---------------------------------------------
                |   1) List all Pets                        |
                |   2) List all Dogs                        |
                |   3) List all Cats                        |
                |   4) List all Dangerous Dogs              |
                |   5) List all Indoor Cats                 |
                |   6) List all Dogs older than an age      |
                |   7) List all Cats by Favourite Toy       |
                |   8) List all Pets that are neutered      |
                |   9) Number of Dogs                       |
                |   10) Number of Dangerous Dogs            |
                |   11) Number of Cats                      |
                |   12) Number of Indoor Cats               |
                |   13) Produce Weekly Income Report        |
                ---------------------------------------------
                |   0) Return to Main Menu                  |
                ---------------------------------------------
                ==>>""");

        switch (option) {
            case 1 -> showPets();
            case 2 -> showDogs();
            case 3 -> showCats();
            case 4 -> showDangerousDogs();
            case 5 -> showIndoorCats();
            case 6 -> showAllDogsOlderThan();
            case 7 -> showCatsByFavToy();
            case 8 -> showPetsNeutered();
            case 9 -> dogsCount();
            case 10 -> dangerousDogsCount();
            case 11 -> catsCount();
            case 12 -> indoorCatsCount();
            case 13 -> showWeeklyIncome();
            case 0 -> runMenu();
            default -> System.out.println("Invalid option entered: " + option);
        }

    }

    //print all the pets in daycare
    private void showPets() {
        System.out.println("List of all Pets are: ");
        System.out.println(daycare.listAllPets());
    }

    //print all the dogs in daycare
    private void showDogs() {
        System.out.println("List of all Dogs are: ");
        System.out.println(daycare.listAllDogs());
    }

    //print all the cats in daycare
    private void showCats() {
        System.out.println("List of all Cats are: ");
        System.out.println(daycare.listAllCats());
    }

    //print all the dangerous dogs in daycare
    private void showDangerousDogs() {
        System.out.println("List of all Dangerous Dogs are: ");
        System.out.println(daycare.listAllDangerousDogs());
    }

    //print all the indoor cats in daycare
    private void showIndoorCats() {
        System.out.println("List of all Indoor Cats are: ");
        System.out.println(daycare.listAllIndoorCats());
    }

    //print all the dogs older than age input by user in daycare
    private void showAllDogsOlderThan() {
        int age = ScannerInput.readNextInt("Enter an age: ");
        System.out.println("List of all Dogs Older than" + age + " are: ");
        System.out.println(daycare.listAllDogsOlderThan(age));
    }

    //print all the cats whose fav toy input by user is in daycare
    private void showCatsByFavToy() {
        String toy = ScannerInput.readNextLine("Enter a toy: ");
        System.out.println("List of all cats whose Favourite Toy is " + toy + ": ");
        System.out.println(daycare.listAllCatsByFavToy(toy));
    }

    //print all the pets that are neutered in daycare
    private void showPetsNeutered() {
        System.out.println("List of all Pets that are neutered: ");
        System.out.println(daycare.listAllNeuteredPets());
    }

    //print the weekly income of the daycare based on current pets
    private void showWeeklyIncome() {
        System.out.println("Weekly Income based on current pets: ");
        System.out.println(daycare.getWeeklyIncome());
    }

    //print number of dogs in the daycare
    private void dogsCount() {
        System.out.println("Number of dogs: ");
        System.out.println(daycare.numberOfDogs());
    }

    //print number of dogs in the daycare
    private void dangerousDogsCount() {
        System.out.println("Number of Dangerous Dogs : ");
        System.out.println(daycare.numberOfDangerousDogs());
    }

    //print number of dogs in the daycare
    private void catsCount() {
        System.out.println("Number of Cats: ");
        System.out.println(daycare.numberOfCats());
    }

    //print number of dogs in the daycare
    private void indoorCatsCount() {
        System.out.println("Number of Indoor Cats: ");
        System.out.println(daycare.numberOfIndoorCats());
    }

    private void crudMenu() {
        int option = ScannerInput.readNextInt("""
                -------------------------------------
                |        UPDATE & REMOVE PETS       |
                -------------------------------------
                |   1) Update Pet Information       |
                |   2) Delete a Pet                 |
                -------------------------------------
                |   0) Return to Main Menu          |
                -------------------------------------
                ==>>""");

        switch (option) {
            case 0 -> runMenu();
            case 1 -> updatePet();
            case 2 -> deletePetMenu();
            default -> System.out.println("Invalid option entered: " + option);
        }
    }

    //---------------------
    //  Update Pet Method
    //---------------------
    private void updatePet() {
        boolean isUpdated = false;
        showPets();
        if (daycare.numberOfPets() > 0) {
            int idToUpdate = ScannerInput.readNextInt("Enter the id of the pet to update ==> ");
            while (daycare.isValidId(idToUpdate)) {
                idToUpdate = ScannerInput.readNextInt("Enter the correct id of the pet to update ==> ");
            }
            System.out.println("Pet you are updating: " + daycare.getPetById(idToUpdate).toString());
            String owner = ScannerInput.readNextLine("Enter Owner's Name: ");
            int age = ScannerInput.readNextInt("Enter Pet's age: ");
            char sex = ScannerInput.readNextChar("Enter Pet's sex (m/f): ");
            while (sex != 'm' && sex != 'f') {
                sex = ScannerInput.readNextChar("Invalid option " + sex + ", Enter Pet's sex (m/f): ");
            }
            boolean[] daysAttending = new boolean[5];
            String[] weekDays = {"Mon", "Tues", "Wed", "Thurs", "Fri"};
            for (int i = 0; i < 5; i++) {
                daysAttending[i] = Utilities.YNtoBoolean(ScannerInput.readNextChar("Attending " + weekDays[i] + "? (y/n)"));
            }
            int id = ScannerInput.readNextInt("Enter Pet id number: ");
            while (!daycare.isValidId(id) && id != idToUpdate) {
                id = ScannerInput.readNextInt("Invalid Pet id (already in use), Enter another Pet id number: ");
            }
            String petName = ScannerInput.readNextLine("Enter the Pet's Name: ");
            char neuteredInput = ScannerInput.readNextChar("Is pet neutered? (y/n): ");
            while (neuteredInput != ('y') && neuteredInput != 'n') {
                neuteredInput = ScannerInput.readNextChar("Invalid option " + neuteredInput + " Is pet neutered? (y/n): ");
            }
            boolean neutered = Utilities.YNtoBoolean(neuteredInput);
            if (daycare.getPetById(idToUpdate) instanceof Dog) {
                String breed = ScannerInput.readNextLine("What breed is your dog?: ");
                while (!DogBreedUtility.checkBreed(breed)) {
                    breed = ScannerInput.readNextLine("Invalid breed, What breed is your dog?: ");
                }
                char dangerousBreedInput = ScannerInput.readNextChar("Is dog dangerous breed? (y/n): ");
                while (dangerousBreedInput != ('y') && dangerousBreedInput != 'n') {
                    dangerousBreedInput = ScannerInput.readNextChar("Invalid option " + dangerousBreedInput + " Is dog dangerous breed? (y/n): ");
                }
                boolean dangerousBreed = Utilities.YNtoBoolean(dangerousBreedInput);

                Dog dog = new Dog(owner, petName, age, sex, id, neutered, daysAttending, breed, dangerousBreed);
                isUpdated = daycare.updatePet(idToUpdate, dog);
            } else if (daycare.getPetById(idToUpdate) instanceof Cat) {
                char indoorCatInput = ScannerInput.readNextChar("Is the cat an indoor cat? (y/n): ");
                while (indoorCatInput != 'y' && indoorCatInput != 'n') {
                    indoorCatInput = ScannerInput.readNextChar("Invalid option " + indoorCatInput + ", Is the cat an indoor cat? (y/n): ");
                }
                boolean indoorCat = Utilities.YNtoBoolean(indoorCatInput);
                String favouriteToy = ScannerInput.readNextLine("What is the cat's favourite toy?: ");
                CatToyUtility.getCatToys();
                while (!CatToyUtility.isCatToy(favouriteToy)) {
                    favouriteToy = ScannerInput.readNextLine("Invalid cat toy, What is the cat's favourite toy?: ");
                }

                Cat cat = new Cat(owner, petName, age, sex, id, neutered, daysAttending, indoorCat, favouriteToy);
                isUpdated = daycare.updatePet(idToUpdate, cat);
            }
            if (isUpdated) {
                System.out.println("Pet Updated Successfully");
            } else {
                System.out.println("No Pet Updated");
            }
        } else {
            System.out.println("No pets added yet");
        }
    }

    private void deletePetMenu() {
        if (daycare.numberOfPets() > 0) {

            int option = ScannerInput.readNextInt("""
                    -------------------------------------
                    |            DELETE A PET           |
                    -------------------------------------
                    |   1) Delete Pet by Index          |
                    |   2) Delete Pet by ID             |
                    -------------------------------------
                    |   0) Return to Main Menu          |
                    -------------------------------------
                    ==>>""");
            switch (option) {
                case 1 -> {
                    showPets();
                    if (daycare.numberOfPets() > 0) {
                        //only ask the user to choose the pet to delete if pets exist
                        int indexToDelete = ScannerInput.readNextInt("Enter the index of the pet to delete ==> ");
                        while (Utilities.isValidIndex(daycare.getPets(), indexToDelete)) {
                            indexToDelete = ScannerInput.readNextInt("Enter the correct id of the pet to update ==> ");
                        }
                        //pass the index of the pet to DayCare for deleting and check for success
                        Pet petToDelete = daycare.deletePetByIndex(indexToDelete);
                        if (petToDelete != null) {
                            System.out.println("Delete Successful! Deleted pet: " + petToDelete.toString());
                        } else {
                            System.out.println("Delete NOT Successful");
                        }
                    }
                }
                case 2 -> {
                    showPets();
                    if (daycare.numberOfPets() > 0) {
                        //only ask the user to choose the pet to delete if pets exist
                        int idToDelete = ScannerInput.readNextInt("Enter the index of the pet to delete ==> ");
                        while (daycare.isValidId(idToDelete)) {
                            idToDelete = ScannerInput.readNextInt("Enter the correct id of the pet to update ==> ");
                        }
                        //pass the id of the pet to DayCare for deleting and check for success.
                        Pet petToDelete = daycare.deletePetById(idToDelete);
                        if (petToDelete != null) {
                            System.out.println("Delete Successful! Deleted pet: " + petToDelete.toString());
                        } else {
                            System.out.println("Delete NOT Successful");
                        }
                    }
                }
                case 0 -> runMenu();
                default -> System.out.println("Invalid option entered: " + option);
            }
        }
    }

    //------------------------------------
    //    CHECK PET IN/OUT METHOD
    // ------------------------------------
    private void checkPetInOut() {

    }

    private void searchAndSortMenu() {
        int option = ScannerInput.readNextInt("""
                -------------------------------------
                |         SEARCH & SORT PETS        |
                -------------------------------------
                |   1) Search Pets by Name          |
                |   2) Sort Pets by Age             |
                -------------------------------------
                |   0) Return to Main Menu          |
                -------------------------------------
                ==>>""");

        switch (option) {
            case 0 -> runMenu();
            case 1 -> searchPetsByName();
            case 2 -> sortPetsByAge();
            default -> System.out.println("Invalid option entered: " + option);
        }
    }

    private void searchPetsByName() {
        String petName = ScannerInput.readNextLine("Please enter a pet petName to search by:");
        System.out.println(daycare.searchByName(petName));
    }

    private void sortPetsByAge() {
    }

    //------------------------------------
    //      TODAYS ATTENDANCE METHOD
    // ------------------------------------
    private void todaysAttendance() {

    }

    private void daycareSettings() {
        int option = ScannerInput.readNextInt("""
                ---------------------------------------------------
                |                 DAYCARE SETTINGS                |
                ---------------------------------------------------
                |   1) Name of Daycare                            |
                |   2) List of all Pets in the Daycare            |
                |   3) Maximum Number of Pets                     |
                ---------------------------------------------------
                |   0) Return to Main Menu                        |
                ---------------------------------------------------
                ==>>""");

        switch (option) {
            case 0 -> runMenu();
            case 1 -> daycareName();
            case 2 -> petsList();
            case 3 -> maxNumPets();
            default -> System.out.println("Invalid option entered: " + option);
        }
    }

    private void daycareName() {

    }

    private void petsList() {

    }

    private void maxNumPets() {

    }

//---------------------
//  Helper Methods
//---------------------
//    private Pet askUserToSelectPet() {
//        printProducts();
//        if (store.numberOfProducts() > 0) {
//            Product product = store.findProduct(ScannerInput.readNextInt("Enter the index of the product: "));
//            if (product != null) {
//                return product;
//            } else {
//                System.out.println("Product index is not valid");
//            }
//        }
//        return null;
//    }

    //------------------------------------
    //        PERSISTENCE METHODS
    // ------------------------------------
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

//    private void exitApp() {
//
//        System.out.println("Exiting....");
//        System.exit(0);
//    }

}