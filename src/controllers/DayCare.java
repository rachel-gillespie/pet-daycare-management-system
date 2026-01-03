package controllers;

import models.Cat;
import models.Dog;
import models.Pet;
import utils.ISerializer;
import utils.Utilities;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * DayCare
 * This DayCare class is responsible for storing and managing ALL the Pets in the system.
 *
 * @author Rachel Gillespie
 * @version 1.0
 */

public class DayCare implements ISerializer {

    // FIELDS
    private List<Pet> pets;
    private String name = ""; //max 20 chars
    private int maxNumberOfPets = 0;

    /**
     * Constructor for objects of class DayCare.
     * Creates a new DayCare with a specified name and maximum capacity for pets.
     *
     * @param name            The name of the day care (maximum 20 characters)
     * @param maxNumberOfPets The maximum number of pets the day care can accommodate
     */
    public DayCare(String name, int maxNumberOfPets) {
        this.name = name;
        pets = new ArrayList<>();
        this.maxNumberOfPets = maxNumberOfPets;
    }

    // GETTERS

    /**
     * Returns the name of the day care.
     *
     * @return The day care name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of all pets in the day care.
     *
     * @return A list of Pet objects
     */
    public List<Pet> getPets() {
        return pets;
    }

    /**
     * Returns the maximum number of pets the day care can accommodate.
     *
     * @return The maximum capacity of pets
     */
    public int getMaxNumberOfPets() {
        return maxNumberOfPets;
    }

    // SETTERS

    /**
     * Sets the name of the day care.
     *
     * @param name The new name for the day care (maximum 20 characters)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the list of pets in the day care.
     *
     * @param pets A list of Pet objects to replace the current list
     */
    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    /**
     * Sets the maximum number of pets the day care can accommodate.
     *
     * @param maxNumberOfPets The new maximum capacity for pets
     */
    public void setMaxNumberOfPets(int maxNumberOfPets) {
        this.maxNumberOfPets = maxNumberOfPets;
    }

    // BASIC CRUD Methods

    /**
     * Adds a Pet object to the day care's list of pets.
     *
     * @param pet The Pet object to be added
     * @return true if the Pet was successfully added, false otherwise
     */
    public boolean addPet(Pet pet) {
        return pets.add(pet);
    }

    /**
     * Removes a Pet object at the specified index from the list.
     *
     * @param indexToDelete The index of the pet to be removed
     * @return The Pet object that was removed, or null if the index is invalid
     */
    public Pet deletePetByIndex(int indexToDelete) {
        if (Utilities.isValidIndex(pets, indexToDelete)) {
            return pets.remove(indexToDelete);
        }
        return null;
    }

    /**
     * Removes a Pet object with the specified ID from the list.
     *
     * @param idToDelete The ID of the pet to be removed
     * @return The Pet object that was removed, or null if no pet with that ID exists
     */
    public Pet deletePetById(int idToDelete) {
        Pet petToDelete = getPetById(idToDelete);
        if (petToDelete != null) {
            pets.remove(petToDelete);
            return petToDelete;
        } else {
            return null;
        }
    }

    /**
     * Finds and returns a Pet object with the specified name.
     *
     * @param name The name of the pet to find
     * @return The Pet object with the matching name, or null if not found
     */
    public Pet getPet(String name) {
        for (Pet p : pets) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Returns a Pet object at the specified index.
     *
     * @param PetIndex The index of the pet to retrieve
     * @return The Pet object at that index, or null if the index is invalid
     */
    public Pet getPetByIndex(int PetIndex) {
        if (Utilities.isValidIndex(pets, PetIndex)) {
            return pets.get(PetIndex);
        }
        return null;
    }

    /**
     * Returns a Pet object with the specified ID.
     *
     * @param PetId The ID of the pet to retrieve
     * @return The Pet object with the matching ID, or null if not found
     */
    public Pet getPetById(int PetId) {
        for (Pet p : pets) {
            if (p.getId() == PetId) {
                return p;
            }
        }
        return null;
    }

//    public Pet removePet(int index) {
//        Pet remove = pets.remove(index);
//        return remove;
//    }

    //Reporting Methods

    /**
     * Generates a formatted list of all pets in the day care.
     *
     * @return A string containing all pets with their index numbers, or "No Pets" if the list is empty
     */
    public String listAllPets() {
        if (pets.isEmpty()) {
            return "No Pets";
        } else {
            String str = "";
            for (Pet p : pets) {
                str += pets.indexOf(p) + ": " + p.toString() + "\n";
            }
            return str;
        }
    }

    /**
     * Generates a formatted list of all cats in the day care.
     *
     * @return A string containing all cats with their index numbers, or "No Cats" if no cats exist
     */
    public String listAllCats() {
        if (pets.isEmpty()) {
            return "No Pets";
        } else {
            String str = "";
            for (Pet p : pets) {
                if (p instanceof Cat) {
                    str += pets.indexOf(p) + ": " + p.toString() + "\n";
                }
            }
            if (str.isEmpty()) {
                return "No Cats";
            } else {
                return str;
            }
        }
    }

    /**
     * Generates a formatted list of all dogs in the day care.
     *
     * @return A string containing all dogs with their index numbers, or "No Dogs" if no dogs exist
     */
    public String listAllDogs() {
        if (pets.isEmpty()) {
            return "No Pets";
        } else {
            String str = "";
            for (Pet p : pets) {
                if (p instanceof Dog) {
                    str += pets.indexOf(p) + ": " + p.toString() + "\n";
                }
            }
            if (str.isEmpty()) {
                return "No Dogs";
            } else {
                return str;
            }
        }
    }

    /**
     * Generates a formatted list of all dangerous breed dogs in the day care.
     *
     * @return A string containing all dangerous dogs with their index numbers,
     * "No Dogs" if no dogs exist, or "No Dangerous Dogs in the Kennels" if no dangerous dogs exist
     */
    public String listAllDangerousDogs() {
        if (pets.isEmpty()) {
            return "No Pets";
        } else {
            String str = "";
            boolean hasDogs = false; //check if any dogs exist at all
            for (Pet p : pets) {
                if (p instanceof Dog dog && (dog.isDangerousBreed())) { //check if the pet is a Dog first, then access its properties
                    hasDogs = true;
                    str += pets.indexOf(p) + ": " + p.toString() + "\n";
                }
            }
            if (!hasDogs) {
                return "No Dogs";
            } else if (str.isEmpty()) {
                return "No Dangerous Dogs in the Kennels";
            } else {
                return str;
            }
        }
    }

    /**
     * Generates a formatted list of all indoor cats in the day care.
     *
     * @return A string containing all indoor cats with their index numbers, or "No Cats" if no indoor cats exist
     */
    public String listAllIndoorCats() {
        if (pets.isEmpty()) {
            return "No Pets";
        } else {
            String str = "";
            for (Pet p : pets) {
                if (p instanceof Cat cat && (cat.isIndoorCat())) {
                    str += pets.indexOf(p) + ": " + p.toString() + "\n";
                }
            }
            if (str.isEmpty()) {
                return "No Cats";
            } else {
                return str;
            }
        }
    }

    /**
     * Generates a formatted list of all dogs older than the specified age.
     *
     * @param age The age threshold to compare against
     * @return A string containing all dogs older than the specified age with their index numbers,
     * or "No Dogs" if no matching dogs exist
     */
    public String listAllDogsOlderThan(int age) {
        if (pets.isEmpty()) {
            return "No Pets";
        } else {
            String str = "";
            for (Pet p : pets) {
                if (p instanceof Dog dog && dog.getAge() > age) {
                    str += pets.indexOf(p) + ": " + p.toString() + "\n";
                }
            }
            if (str.isEmpty()) {
                return "No Dogs";
            } else {
                return str;
            }
        }
    }

    /**
     * Generates a formatted list of all cats with the specified favourite toy.
     *
     * @param toy The favourite toy to search for
     * @return A string containing all matching cats with their index numbers,
     * or "No cat's favourite toy" if no matches exist
     */
    public String listAllCatsByFavToy(String toy) {
        if (pets.isEmpty()) {
            return "No Pets";
        } else {
            String str = "";
            for (Pet p : pets) {
                if (p instanceof Cat cat && cat.getFavouriteToy().equals(toy)) {
                    str += pets.indexOf(p) + ": " + p.toString() + "\n";
                }
            }
            if (str.isEmpty()) {
                return "No cat's favourite toy";
            } else {
                return str;
            }
        }
    }

    /**
     * Generates a formatted list of all neutered pets in the day care.
     *
     * @return A string containing all neutered pets with their index numbers,
     * or "No Pets are neutered" if no neutered pets exist
     */
    public String listAllNeuteredPets() {
        if (pets.isEmpty()) {
            return "No Pets";
        } else {
            String str = "";
            for (Pet p : pets) {
                if (p.isNeutered()) {
                    str += pets.indexOf(p) + ": " + p.toString() + "\n";
                }
            }
            if (str.isEmpty()) {
                return "No Pets are neutered";
            } else {
                return str;
            }
        }
    }

    /**
     * Generates a formatted list of all pets belonging to a specific owner.
     *
     * @param owner The name of the owner to search for
     * @return A string containing all pets belonging to that owner with their index numbers,
     * or "No Pet with owner [owner name]" if no matches exist
     */
    public String listAllPetsByOwner(String owner) {
        if (pets.isEmpty()) {
            return "No Pets";
        } else {
            String str = "";
            for (Pet p : pets) {
                if (p.getOwner().equals(name)) {
                    str += pets.indexOf(p) + ": " + p.toString() + "\n";
                }
            }
            if (str.isEmpty()) {
                return "No Pet with owner " + name;
            } else {
                return str;
            }
        }
    }

    /**
     * Generates a formatted list of all pets staying longer than the specified number of days.
     *
     * @param numDays The minimum number of days to compare against
     * @return A string containing all pets staying longer than the specified days with their index numbers,
     * or an empty string if no matches exist
     */
    public String listAllPetsThatStayMoreThanDays(int numDays) {
        if (pets.isEmpty()) {
            return "No Pets";
        } else {
            String str = "";
            for (Pet p : pets) {
                if (p.numberOfDaysInKennel() > numDays) {
                    str += pets.indexOf(p) + ": " + p.toString() + "\n";
                }
            }
            return str;
        }
    }

    /**
     * Returns the total number of pets in the day care.
     *
     * @return The count of all Pet objects in the system
     */
    public int numberOfPets() {
        return pets.size();
    }

    /**
     * Returns the total number of cats in the day care.
     *
     * @return The count of all Cat objects in the system
     */
    public int numberOfCats() {
        int number = 0;
        for (Pet p : pets) {
            if (p instanceof Cat) {
                number++;
            }
        }
        return number;
    }

    /**
     * Returns the total number of dogs in the day care.
     *
     * @return The count of all Dog objects in the system
     */
    public int numberOfDogs() {
        int number = 0;
        for (Pet p : pets) {
            if (p instanceof Dog) {
                number++;
            }
        }
        return number;
    }

    /**
     * Returns the total number of dangerous breed dogs in the day care.
     *
     * @return The count of all dangerous Dog objects in the system
     */
    public int numberOfDangerousDogs() {
        int number = 0;

        for (Pet p : pets) {
            if (p instanceof Dog dog && (dog.isDangerousBreed())) {
                number++;
            }
        }
        return number;
    }

    /**
     * Returns the total number of indoor cats in the day care.
     *
     * @return The count of all indoor Cat objects in the system
     */
    public int numberOfIndoorCats() {
        int number = 0;

        for (Pet p : pets) {
            if (p instanceof Cat cat && (cat.isIndoorCat())) {
                number++;
            }
        }
        return number;
    }

    // UPDATE METHODS

    /**
     * Updates an existing pet with new details.
     *
     * @param id             The ID of the pet to update
     * @param updatedDetails A Pet object containing the new details
     * @return true if the pet was successfully updated, false if the pet was not found
     */
    public boolean updatePet(int id, Pet updatedDetails) {
        Pet foundPet = getPetById(id);

        if (foundPet != null) {
            foundPet.setOwner(updatedDetails.getOwner());
            foundPet.setAge(updatedDetails.getAge());
            foundPet.setSex(updatedDetails.getSex());
            foundPet.setDaysAttending(updatedDetails.getDaysAttending());
            foundPet.setId(updatedDetails.getId());
            foundPet.setName(updatedDetails.getName());
            if (foundPet instanceof Dog) {
                updateDog(id, (Dog) updatedDetails); //casting updated details to Dog
            } else if (foundPet instanceof Cat) {
                updateCat(id, (Cat) updatedDetails);
            }
            return true;
            //if the pet was not found, return false, indicating that the update was not successful.
        } else return false;
    }

    /**
     * Updates an existing dog with new dog-specific details.
     *
     * @param id             The ID of the dog to update
     * @param updatedDetails A Dog object containing the new details
     * @return true if the dog was successfully updated, false if the dog was not found
     */
    public boolean updateDog(int id, Dog updatedDetails) {
        //find the dog object by the index number.
        Dog foundDog = (Dog) getPetById(id);

        //if the dog exists, use the details passed in the updateDetails parameter to
        //update the found dog in the ArrayList.
        if (foundDog != null) {
            foundDog.setBreed(updatedDetails.getBreed());
            foundDog.setDangerousBreed(updatedDetails.isDangerousBreed());
            foundDog.setNeutered(updatedDetails.isNeutered());
            return true;
        }
        //if the dog was not found, return false, indicating that the update was not successful.
        return false;
    }

    /**
     * Updates an existing cat with new cat-specific details.
     *
     * @param id             The ID of the cat to update
     * @param updatedDetails A Cat object containing the new details
     * @return true if the cat was successfully updated, false if the cat was not found
     */
    public boolean updateCat(int id, Cat updatedDetails) {
        //find the cat object by the index number.
        Cat foundCat = (Cat) getPetById(id);

        //if the cat exists, use the details passed in the updateDetails parameter to
        //update the found cat in the ArrayList.
        if (foundCat != null) {
            foundCat.setIndoorCat(updatedDetails.isIndoorCat());
            foundCat.setFavouriteToy(updatedDetails.getFavouriteToy());
            return true;
        }
        //if the cat was not found, return false, indicating that the update was not successful.
        return false;
    }

    //VALIDATION METHODS

    /**
     * Checks if the specified ID is available (not already in use).
     *
     * @param id The ID to check
     * @return true if the ID is not in use, false if it already exists
     */
    public boolean isValidId(int id) {
        for (Pet p : pets) {
            if (p.getId() == id) {
                return false;
            }
        }
        return true;
    }

    // OTHER METHODS

    /**
     * Calculates the total weekly income from all pets in the day care.
     *
     * @return The sum of weekly fees for all pets
     */
    public double getWeeklyIncome() {
        double total = 0;
        for (Pet p : pets) {
            total += p.calculateWeeklyFee();
        }
        return (Utilities.toTwoDecimalPlaces(total));
    }

    /**
     * Calculates the average number of days per week that pets stay in the day care.
     *
     * @return The average number of days pets stay in the kennels
     */
    public double getAverageNumDaysPerWeek() {
        double total = 0;
        for (Pet p : pets) {
            total += p.numberOfDaysInKennel();
        }
        return total / numberOfPets();
    }

    /**
     * Finds a dog matching the specified owner name, breed, and age.
     *
     * @param name  The owner's name to search for
     * @param breed The breed to search for
     * @param age   The age to search for
     * @return The matching Dog object, or null if no match is found
     */
    public Pet findDogByOwnerAndBreedAndAge(String name, String breed, int age) {
        for (Pet p : pets) {
            if (p instanceof Dog dog && dog.getName().equals(name) && dog.getBreed().equals(breed) && dog.getAge() == age) {
                return p;
            }
        }
        return null;
    }

//    public String getPetsByOwnersName(String name) {
//        if (pets.isEmpty()) {
//            return "No Pets";
//        } else {
//            String str = "";
//            for (Pet pet : pets) {
//                if (pet.getOwner().equals(name)) {
//                    str += pets.indexOf(pet) + ": " + pet.toString() + "\n";
//                }
//            }
//            if (str.isEmpty()) {
//                return "No Pet with owner" + name;
//            } else {
//                return str;
//            }
//        }
//    }

    // PERSISTENCE METHODS

    /**
     * Loads all Pet objects from the XML file into the system.
     *
     * @throws Exception if there is an error reading from the file
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{Pet.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("pets.xml"));
        pets = (ArrayList<Pet>) is.readObject();
        is.close();
    }

    /**
     * Saves all Pet objects from the system to an XML file.
     *
     * @throws Exception if there is an error writing to the file
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("pets.xml"));
        out.writeObject(pets);
        out.close();
    }

    // ISERIALIZER

    /**
     * Returns the name of the file used for saving and loading pet data.
     *
     * @return The filename "pets.xml"
     */
    public String fileName() {
        return "pets.xml";
    }

}