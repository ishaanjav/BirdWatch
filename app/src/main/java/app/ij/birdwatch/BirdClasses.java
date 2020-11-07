package app.ij.birdwatch;

import java.util.ArrayList;
import java.util.LinkedHashMap;

class BirdClasses {
    public static ArrayList<String> classes;
    public static LinkedHashMap<String, String> threat;

    void init() {
        classes = new ArrayList<>();
        threat = new LinkedHashMap<>();
    }
}
