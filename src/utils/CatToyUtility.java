package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class contains utility methods used to validate Cat Toys.
 */

public class CatToyUtility {

    // Create a HashMap where the key is the toy name (uppercase)
    // and the value is a description or recommended age

    private static Map<String, String> catToys = new HashMap<>() {{
        put("FEATHER WAND", "Interactive toy for all ages");
        put("LASER POINTER", "Supervised play only");
        put("BALL OF YARN", "Suitable for kittens");
        put("MICE TOY", "Safe for indoor cats");
        put("CATNIP MOUSE", "Encourages active play");
        put("SCRATCHING POST", "Essential for claw maintenance");
        put("TUNNEL", "Fun for hiding and running");
        put("CRINKLE BALL", "Stimulating sounds for hunting instincts");
        put("CAT TREE", "Multi-level climbing and scratching");
        put("SPRING TOY", "Bouncy and unpredictable movement");
        put("PUZZLE FEEDER", "Mental stimulation during mealtime");
        put("TRACK BALL TOY", "Engages batting and chasing behavior");
        put("SISAL ROPE TOY", "Durable for aggressive chewers");
        put("ELECTRONIC MOUSE", "Battery-powered interactive fun");
        put("TREAT DISPENSER", "Rewards problem-solving skills");
        put("FISHING ROD TOY", "Great for bonding playtime");
        put("CARDBOARD SCRATCHER", "Eco-friendly scratching surface");
        put("BELL BALL", "Auditory stimulation toy");
        put("PLUSH KICKER TOY", "Perfect for bunny kicks");
        put("WINDOW PERCH", "Entertainment from bird watching");
        put("WOBBLE TOY", "Self-righting for solo play");
        put("FURRY MICE SET", "Realistic prey simulation");
    }};


    static public String getCatToys() {
        // Display all toys with info
        String info = "Cat Toys and Info:";
        for (Map.Entry<String, String> entry : catToys.entrySet()) {
            info += "\n - " + entry.getKey() + "\n | Info: " + entry.getValue();
        }
        return info;
    }

    // Check for a valid toy
    static public boolean isCatToy(String catToy) {
        return catToys.containsKey(catToy.toUpperCase());
    }


}
