package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.Cat;
import models.Dog;
import models.Pet;
import utils.Utilities;

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

    // TODO Reporting Methods

    /**
     * listAllPets method
     * @return a String containing the details of all the pets in 'pets' along with the index number associated with each Pet object.
     * If no pets exist yet, "No Pets" should be returned.
     */
    public String listAllPets() {
        String str = "";

        for (Pet pet : pets) {
            str += pets.indexOf(pet) + ": " + pet.toString() + "\n";
        }

        if (str.isEmpty()) {
            return "No Pets";
        } else {
            return str;
        }
    }

    /**
     *
     * @return a String containing the details of all the cats in pets along with the index number associated with each Cat object.
     * If no cats exist yet, “No cats” should be returned.
     */
    public String listAllCats() {
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

    /**
     *
     * @return a String containing the details of all the dogs in pets along with the index number associated with each Dog object.
     * If no dogs exist yet, “No Dogs” should be returned.
     */
    public String listAllDogs() {
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

    /**
     *
     * @return a String containing the details of all the dangerous dogs in pets along with the index number associated with each Dog object.
     * If no dogs exist yet, “No Dogs” should be returned.
     * If no dangerous dogs exist, “No Dangerous Dogs in the Kennels” should be returned.
     */
    public String listAllDangerousDogs() {
        String str = "";

        for (Pet pet : pets) {
            if (pet instanceof Cat) {
                str += pets.indexOf(pet) + ": " + pet.toString() + "\n";
            }
        }

        if (str.isEmpty()) {
            return "No Dogs";
        } else {
            return str;
        }

    }

    /**
     *
     * @param owner
     * @return This method should return the list of pets associated with that owner.
     * If no such Pet exist, “No Pet with owner ??” (include owner name) should be returned.
     */
    public String listAllPetsByOwner(String owner) {
        String str = "";

        for (Pet pet : pets) {
            if (pet instanceof Pet) {
                str += pets.indexOf(pet) + ": " + pet.toString() + "\n";
            }
        }

        if (str.isEmpty()) {
            return "No Cats";
        } else {
            return str;
        }

    }

    /**
     *
     * @param numDays
     * @return the list of pets with that stay more than the input days.
     * If no such Pet exist, “No Pet stays longer than ??” (include num days) should be returned.
     */
    public String listAllPetsThatStayMoreThanDays(int numDays) {
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

    // TODO number methods

    public int numberOfPets() {

    }

    public int numberOfCats() {

    }

    public int numberOfDogs() {

    }

    public int numberOfDangerousDogs() {

    }

    public int numberOfIndoorCats() {

    }

    //TODO get Pets methods

    /**
     * getPetByIndex method
     * This method returns a Pet object at the location index, which is passed as a parameter.
     *
     * @param index
     * @return the object at that position, if it does exist.
     * if the passed index is not valid, return null.
     */
    public Pet getPetByIndex(int index) {
        if (Utilities.isValidIndex(pets, index)) {
            return pets.get(index);
        }
        return null;
    }

    /**
     * getPetById method
     * This method returns a Pet object with that exact id (ignoring case), which is passed as a parameter.
     *
     * @param id
     * @return the object with that id, if it does exist.
     * if the passed id is not found, return null.
     */
    public Pet getPetById(int id) {
        if (Utilities.isValidId(pets, id)) {
            return pets.get(id);
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
        if (Utilities.isValidId(pets, idToDelete)) {
            return pets.remove(idToDelete);
        }
        return null;
    }


    // TODO Persistence methods
    /**
     * The load method uses the XStream component to read all the models.MessagePost objects from the posts.xml
     * file stored on the hard disk.  The read objects are loaded into the posts ArrayList
     *
     * @throws Exception An exception is thrown if an error occurred during the load e.g. a missing file.
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{DayCare.class, Pet.class, Cat.class, Dog.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader("Pets.xml"));
        pets = (ArrayList<Pet>) in.readObject();
        in.close();
    }

    /**
     * The save method uses the XStream component to write all the objects in the posts ArrayList
     * to the posts.xml file stored on the hard disk.
     *
     * @throws Exception An exception is thrown if an error occurred during the save e.g. drive is full.
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("Pets.xml"));
        out.writeObject(pets);
        out.close();

    }
}



