package org.der.weg.zum.java.profi.CollectionFrameWork;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class HashMapTesting {
    private static final Map<String, Color> colorMap = new HashMap<>();

    public static void initMap(final Map<String,Color> map){
        map.put("Black",Color.BLACK);
        map.put("White",Color.WHITE);
        map.put("Red",Color.RED);
        map.put("Green",Color.GREEN);
        map.put("Blue",Color.BLUE);
    }

    private static Color mapToColor(final String colorName){
        if(!colorMap.containsKey(colorName)){
            throw new IllegalArgumentException("Color not found: " + colorName);
        }
        return colorMap.get(colorName);
    }
    public static void main(String[] args) {

    }
}
