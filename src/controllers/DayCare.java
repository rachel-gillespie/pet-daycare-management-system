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
 * This API class is responsible for storing and managing ALL the Pets in the system.
 * Constructor Documentation
 * Method Documentation
 *
 * @author Rachel Gillespie
 * @version 1.0
 */

public class DayCare implements ISerializer {

    private int maxNumberOfPets = 0;
    private List<Pet> pets;
    private String name = ""; //max 20 chars

    /**
     * DayCare Constructor
     *
     * @param name
     * @param maxNumberOfPets
     */
    public DayCare(String name, int maxNumberOfPets) {
        this.name = name;
        pets = new ArrayList<>();
        this.maxNumberOfPets = maxNumberOfPets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
            if (foundPet instanceof Dog) {
                updateDog(id,(Dog) updatedDetails); //casting updated details to Dog
            }
            else if (foundPet instanceof Cat) {
                updateCat(id,(Cat) updatedDetails);
            }
        }
        return foundPet;
    }

    public Dog updateDog(int id, Dog updatedDetails) {
        //find the dog object by the index number.
        Dog foundDog = (Dog) getPetById(id);

        //if the dog exists, use the details passed in the updateDetails parameter to
        //update the found dog in the ArrayList.
        if (foundDog !=null) {
            foundDog.setBreed(updatedDetails.getBreed());
            foundDog.setDangerousBreed(updatedDetails.isDangerousBreed());
            foundDog.setNeutered(updatedDetails.isNeutered());
        }
        //if the dog was not found, return false, indicating that the update was not successful.
        return foundDog;
    }

    public Pet updateCat(int id, Cat updatedDetails) {
        //find the cat object by the index number.
        Cat foundCat = (Cat) getPetById(id);

        //if the cat exists, use the details passed in the updateDetails parameter to
        //update the found cat in the ArrayList.
        if (foundCat !=null) {
            foundCat.setIndoorCat(updatedDetails.isIndoorCat());
            foundCat.setFavouriteToy(updatedDetails.getFavouriteToy());
        }
        //if the cat was not found, return false, indicating that the update was not successful.
        return foundCat;
    }

    // help
    public Pet removePet(int index) {
        Pet remove = pets.remove(index);
        return remove;
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
            for (Pet p : pets) {
                str += pets.indexOf(p) + ": " + p.toString() + "\n";
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
     * listAllIndoorCats method
     *
     * @return a String containing the details of all the cats in pets along with the index number associated with each Cat object.
     * If no cats exist yet, “No cats” should be returned.
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
     * listAllDogsOlderThan method
     *
     * @return a String containing the details of all the dogs in pets along with the index number associated with each Dog object.
     * If no dogs exist yet, “No Dogs” should be returned.
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
     * listAllCatsByFavToy method
     *
     * @return a String containing the details of all the cats in pets along with the index number associated with each Cat object.
     * If no cats exist yet, “No cats” should be returned.
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
     * listAllNeuteredPets method
     *
     * @return a String containing the details of all the pets in 'pets' along with the index number associated with each Pet object.
     * If no pets exist yet, "No Pets" should be returned.
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
     * listAllPetsByOwner method
     * This method will list all the pets by the owners.
     *
     * @param owner
     * @return This method should return the list of pets associated with that owner.
     * If no such Pet exist, “No Pet with owner ??” (include owner name) should be returned.
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
     * listAllPetsThatStayMoreThanDays method
     *
     * @param numDays
     * @return the list of pets that stay more than the input days.
     * If no such Pet exist, “No Pet stays longer than ??” (include num days) should be returned.
     */
    public String listAllPetsThatStayMoreThanDays(int numDays) {
        if (pets.isEmpty()) {
            return "No Pets";
        } else {
            String str = "";
            for (Pet p : pets) {
                if(p.numberOfDaysInKennel() > numDays){
                    str += pets.indexOf(p) + ": " + p.toString() + "\n";
                }
            }
            return str;
        }
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
        for (Pet p : pets) {
            if (p instanceof Cat) {
                number++;
            }
        }
        return number;
    }

    public int numberOfDogs() {
        int number = 0;
        for (Pet p : pets) {
            if (p instanceof Dog) {
                number++;
            }
        }
        return number;
    }

    public int numberOfDangerousDogs() {
        int number = 0;

        for (Pet p : pets) {
            if (p instanceof Dog dog && (dog.isDangerousBreed())) {
                number++;
            }
        }
        return number;
    }

    public int numberOfIndoorCats() {
        int number = 0;

        for (Pet p : pets) {
            if (p instanceof Cat cat && (cat.isIndoorCat())) {
                number++;
            }
        }
        return number;
    }

    //TODO get Pets methods

    /**
     * getPet method
     * @param name
     * @return
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
       for(Pet p : pets){
           if(p.getId() == PetId){
               return p;
           }
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
        if (Utilities.isValidIndex(pets, indexToDelete)) {
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
        Pet petToDelete = getPetById(idToDelete);
        if(petToDelete != null){
            pets.remove(petToDelete);
            return petToDelete;
        }else{
            return null;
        }


    }

    public boolean isValidId(int id) {
        for (Pet p : pets) {
            if (p.getId() == id) {
                return false;
            }
        }
        return true;
    }

    public double getWeeklyIncome() {
        double total = 0;
        for( Pet p : pets){
            total += p.calculateWeeklyFee();
        }
        return total;
    }

    public double getAverageNumDaysPerWeek() {
        double total = 0;
        for( Pet p : pets){
            total += p.numberOfDaysInKennel();
        }
        return total/numberOfPets();
    }

    public Pet findDogByOwnerAndBreedAndAge(String name, String breed, int age) {
        for (Pet p : pets) {
            if(p instanceof Dog dog && dog.getName().equals(name) && dog.getBreed().equals(breed) && dog.getAge() == age) {
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

    public void initName(String name) {

    }

    // TODO Persistence methods

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[] { Pet.class };

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("pets.xml"));
        pets = (ArrayList<Pet>) is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("pets.xml"));
        out.writeObject(pets);
        out.close();
    }

    public String fileName(){
        return "pets.xml";
    }

}