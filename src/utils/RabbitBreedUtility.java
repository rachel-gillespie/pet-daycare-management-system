package utils;

import java.util.HashSet;
import java.util.Set;

/**
 * This class contains utility methods used to validate Rabbit Breeds
 */

public class RabbitBreedUtility {

    private static Set<String> rabbitBreeds = new HashSet<>();

    public static boolean checkBreed(String breed) {
        // Add breeds
        rabbitBreeds.add("NETHERLAND DWARF");
        rabbitBreeds.add("HOLLAND LOP");
        rabbitBreeds.add("MINI LOP");
        rabbitBreeds.add("LIONHEAD");
        rabbitBreeds.add("FLEMISH GIANT");
        rabbitBreeds.add("ENGLISH LOP");
        rabbitBreeds.add("DUTCH");
        rabbitBreeds.add("REX");
        rabbitBreeds.add("MINI REX");
        rabbitBreeds.add("CALIFORNIAN");
        rabbitBreeds.add("NEW ZEALAND");
        rabbitBreeds.add("ENGLISH ANGORA");
        rabbitBreeds.add("FRENCH ANGORA");
        rabbitBreeds.add("HIMALAYAN");
        rabbitBreeds.add("POLISH");
        rabbitBreeds.add("JERSEY WOOLY");
        rabbitBreeds.add("AMERICAN FUZZY LOP");
        rabbitBreeds.add("HARLEQUIN");
        rabbitBreeds.add("ENGLISH SPOT");
        rabbitBreeds.add("SILVER MARTEN");
        rabbitBreeds.add("CHECKERED GIANT");
        rabbitBreeds.add("BRITANNIA PETITE");

        return rabbitBreeds.contains(breed.toUpperCase());
    }

}
