package utils;

import java.util.HashSet;
import java.util.Set;

/**
 * This class contains utility methods used to validate Dog Breeds
 */

public class DogBreedUtility {

    private static Set<String> dogBreeds = new HashSet<>();

    public static boolean checkBreed(String breed) {
        // Add breeds
        dogBreeds.add("LABRADOR RETRIEVER");
        dogBreeds.add("GERMAN SHEPHERD");
        dogBreeds.add("GOLDEN RETRIEVER");
        dogBreeds.add("BULLDOG");
        dogBreeds.add("BEAGLE");
        dogBreeds.add("ROTTWEILER");
        dogBreeds.add("PIT BULL");
        dogBreeds.add("POODLE");
        dogBreeds.add("YORKSHIRE TERRIER");
        dogBreeds.add("BOXER");
        dogBreeds.add("DACHSHUND");
        dogBreeds.add("SIBERIAN HUSKY");
        dogBreeds.add("GREAT DANE");
        dogBreeds.add("DOBERMAN PINSCHER");
        dogBreeds.add("AUSTRALIAN SHEPHERD");
        dogBreeds.add("MINIATURE SCHNAUZER");
        dogBreeds.add("CAVALIER KING CHARLES SPANIEL");
        dogBreeds.add("SHIH TZU");
        dogBreeds.add("BOSTON TERRIER");
        dogBreeds.add("POMERANIAN");
        dogBreeds.add("BORDER COLLIE");
        dogBreeds.add("COCKER SPANIEL");

        return dogBreeds.contains(breed.toUpperCase());
    }

}
