package controllers;

import models.Cat;
import models.Dog;
import models.Pet;
import utils.Utilities;

import java.util.ArrayList;

/**
 * DayCare
 * This API class is responsible for storing and managing ALL the Pets in the system.
 * Constructor Documentation
 * Method Documentation
 *
 * @author Rachel Gillespie
 * @version 1.0
 */

public class DayCare {

    private int maxNumberOfPets = 0;
    private ArrayList<Pet> pets;
    private String name = ""; //max 20 chars

    /**
     * DayCare Constructor
     *
     * @param name
     * @param maxNumberOfPets
     */
    public DayCare(String name, int maxNumberOfPets) {
        this.name = name;
        pets = new ArrayList<Pet>();
        this.maxNumberOfPets = maxNumberOfPets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Pet> getPetsArray() {
        return pets;
    }

    public void setPetsArray(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    public int getMaxNumberOfPets() {
        return maxNumberOfPets;
    }

    public void setMaxNumberOfPets(int maxNumberOfPets) {
        this.maxNumberOfPets = maxNumberOfPets;
    }

    //TODO - CRUD Methods

    /**
     * addPet Method
     * This method will add a Pet object (passed as a parameter) to the ArrayList pets.
     *
     * @param pet
     * @return true if the Pet was added
     */
    public boolean addPet(Pet pet) {
        return pets.add(pet);
    }

    public Pet updatePet(int id, Pet updatedDetails) {
        Pet foundPet = getPetById(id);

        if (foundPet != null) {
            foundPet.setOwner(updatedDetails.getOwner());
            foundPet.setAge(updatedDetails.getAge());
            foundPet.setSex(updatedDetails.getSex());
            foundPet.setDaysAttending(updatedDetails.getDaysAttending());
            foundPet.setId(updatedDetails.getId());
            foundPet.setName(updatedDetails.getName());
            if (updatedDetails instanceof Dog) {
                updateDog(id,(Dog) foundPet); //casting updated details to Dog
            }
            else if (updatedDetails instanceof Cat) {
                updateCat(id,(Cat) foundPet);
            }
        }
        return foundPet;
    }

    public boolean updateDog(int id, Dog updatedDetails) {
        //find the dog object by the index number.
        Dog foundDog = (Dog) getPetById(id);

        //if the dog exists, use the details passed in the updateDetails parameter to
        //update the found dog in the ArrayList.
        if (foundDog !=null) {
            foundDog.setBreed(updatedDetails.getBreed());
            foundDog.setDangerousBreed(updatedDetails.isDangerousBreed());
            foundDog.setNeutered(updatedDetails.isNeutered());
            return true;
        }
        //if the dog was not found, return false, indicating that the update was not successful.
        return false;
    }

    public boolean updateCat(int id, Cat updatedDetails) {
        //find the cat object by the index number.
        Cat foundCat = (Cat) getPetById(id);

        //if the cat exists, use the details passed in the updateDetails parameter to
        //update the found cat in the ArrayList.
        if (foundCat !=null) {
            foundCat.setIndoorCat(updatedDetails.isIndoorCat());
            foundCat.setFavouriteToy(updatedDetails.getFavouriteToy());
            return true;
        }
        //if the cat was not found, return false, indicating that the update was not successful.
        return false;
    }

    public Pet removePet(int id) {

    }

    // TODO Reporting Methods

    /**
     * listAllPets method
     *
     * @return a String containing the details of all the pets in 'pets' along with the index number associated with each Pet object.
     * If no pets exist yet, "No Pets" should be returned.
     */
    public String listAllPets() {
        if (pets.isEmpty()) {
            return "No Pets";
        } else {
            String str = "";
            for (Pet pet : pets) {
                str += pets.indexOf(pet) + ": " + pet.toString() + "\n";
            }
            return str;
        }
    }

    /**
     * listAllCats method
     *
     * @return a String containing the details of all the cats in pets along with the index number associated with each Cat object.
     * If no cats exist yet, “No cats” should be returned.
     */
    public String listAllCats() {
        if (pets.isEmpty()) {
            return "No Pets";
        } else {
            String str = "";
            for (Pet pet : pets) {
                if (pet instanceof Cat) {
                    str += pets.indexOf(pet) + ": " + pet.toString() + "\n";
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
     * listAllDogs method
     *
     * @return a String containing the details of all the dogs in pets along with the index number associated with each Dog object.
     * If no dogs exist yet, “No Dogs” should be returned.
     */
    public String listAllDogs() {
        if (pets.isEmpty()) {
            return "No Pets";
        } else {
            String str = "";
            for (Pet pet : pets) {
                if (pet instanceof Dog) {
                    str += pets.indexOf(pet) + ": " + pet.toString() + "\n";
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
     * listAllDangerousDogs method
     * This method will list all the Dangerous dogs in the Day Care.
     *
     * @return a String containing the details of all the dangerous dogs in pets along with the index number associated with each Dog object.
     * If no dogs exist yet, “No Dogs” should be returned.
     * If no dangerous dogs exist, “No Dangerous Dogs in the Kennels” should be returned.
     */
    public String listAllDangerousDogs() {
        if (pets.isEmpty()) {
            return "No Pets";
        } else {
            String str = "";
            boolean hasDogs = false; //check if any dogs exist at all
            for (Pet pet : pets) {
                if (pet instanceof Dog dog && (dog.isDangerousBreed())) { //check if the pet is a Dog first, then access its properties
                    hasDogs = true;
                        str += pets.indexOf(pet) + ": " + pet.toString() + "\n";
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
     * listAllPetsByOwner method
     * This method will list all the pets by the owners.
     *
     * @param owner
     * @return This method should return the list of pets associated with that owner.
     * If no such Pet exist, “No Pet with owner ??” (include owner name) should be returned.
     */
    public String listAllPetsByOwner(String owner) {
        String str = "";
        return "";
    }

    /**
     * listAllPetsThatStayMoreThanDays method
     *
     * @param numDays
     * @return the list of pets with that stay more than the input days.
     * If no such Pet exist, “No Pet stays longer than ??” (include num days) should be returned.
     */
    public String listAllPetsThatStayMoreThanDays(int numDays) {
        return "";
    }

    // TODO number methods

    /**
     *
     * @return
     */
    public int numberOfPets() {
        return pets.size();
    }

    public int numberOfCats() {
        int number = 0;
        for (Pet pet : pets) {
            if (pet instanceof Cat) {
                number++;
            }
        }
        return number;
    }

    public int numberOfDogs() {
        int number = 0;
        for (Pet pet : pets) {
            if (pet instanceof Dog) {
                number++;
            }
        }
        return number;
    }

    public int numberOfDangerousDogs() {
        int number = 0;
        boolean hasDogs = false;

        for (Pet pet : pets) {
            if (pet instanceof Dog dog && (dog.isDangerousBreed())) {
                hasDogs = true;
                number++;
            }
        }
        return number;
    }

    public int numberOfIndoorCats() {
        int number = 0;
        boolean hasCats = false;

        for (Pet pet : pets) {
            if (pet instanceof Cat cat && (cat.isIndoorCat())) {
                hasCats = true;
                number++;
            }
        }
        return number;
    }

    //TODO get Pets methods

    public Pet getPet(String pet) {
        return;
    }

    /**
     * getPetByIndex method
     * This method returns a Pet object at the location PetIndex, which is passed as a parameter.
     *
     * @param PetIndex
     * @return the object at that position, if it does exist.
     * if the passed PetIndex is not valid, return null.
     */
    public Pet getPetByIndex(int PetIndex) {
        if (Utilities.isValidIndex(pets, PetIndex)) {
            return pets.get(PetIndex);
        }
        return null;
    }

    /**
     * getPetById method
     * This method returns a Pet object with that exact id (ignoring case), which is passed as a parameter.
     *
     * @param PetId
     * @return the object with that id, if it does exist.
     * if the passed id is not found, return null.
     */
    public Pet getPetById(int PetId) {
        if (isValidId(PetId)) {
            return pets.get(PetId);
        }
        return null;
    }

    //TODO - delete methods

    /**
     * deletePetByIndex method
     * This method removes a Pet object at the location index, which is passed as a parameter.
     *
     * @param indexToDelete
     * @return the object that was just deleted if it does exist and remove it from the ArrayList.
     * If the passed index is not valid, return null.
     */
    public Pet deletePetByIndex(int indexToDelete) {
        if (isValidPetIndex(indexToDelete)) {
            return pets.remove(indexToDelete);
        }
        return null;
    }

    /**
     * deletePetById method
     * This method removes a Pet object with the id which is passed as a parameter.
     *
     * @param idToDelete
     * @return the object that was just deleted if it does exist and remove the corresponding Pet from the ArrayList.
     * If the passed index is not valid, return null
     */
    public Pet deletePetById(int idToDelete) {
        if (isValidId(idToDelete)) {
            Pet petToDelete = getPetById(idToDelete);
            pets.remove(petToDelete);
            return petToDelete;
        }
        return null;
    }

    public boolean isValidId(int id) {
        for (Pet p : pets) {
            if (p.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidPetIndex(int index) {
        return Utilities.isValidIndex(pets, index);
    }

    public double getWeeklyIncome() {
        return 0.0;
    }

    public double getAverageNumDaysPerWeek() {
        return 0.0;
    }

    public Pet findDogByOwnerAndBreedAndAge(String name, String breed, int age) {
        return "";
    }

    public String getPetsByOwnersName(String name) {
        return "";
    }

    public void initName(String) {

    }

    // TODO Persistence methods

    public void load() {

    }

    public void save() {

    }


}