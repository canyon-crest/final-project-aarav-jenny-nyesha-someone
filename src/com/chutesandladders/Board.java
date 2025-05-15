package com.chutesandladders;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private static final int SIZE = 100;
    private Map<Integer, Integer> chutes;
    private Map<Integer, Integer> ladders;

    public Board() {
        initializeChutesAndLadders();
    }

    private void initializeChutesAndLadders() {
        chutes = new HashMap<>();
        ladders = new HashMap<>();
        
        // Chutes
        chutes.put(16, 6);
        chutes.put(47, 26);
        chutes.put(49, 11);
        chutes.put(56, 53);
        chutes.put(62, 19);
        chutes.put(64, 60);
        chutes.put(87, 24);
        chutes.put(93, 73);
        chutes.put(95, 75);
        chutes.put(98, 78);
        
        // Ladders
        ladders.put(1, 38);
        ladders.put(4, 14);
        ladders.put(9, 31);
        ladders.put(21, 42);
        ladders.put(28, 84);
        ladders.put(36, 44);
        ladders.put(51, 67);
        ladders.put(71, 91);
        ladders.put(80, 100);
    }

    public int getNewPosition(int currentPos) {
        if (chutes.containsKey(currentPos)) {
            return chutes.get(currentPos);
        }
        if (ladders.containsKey(currentPos)) {
            return ladders.get(currentPos);
        }
        return Math.min(currentPos, SIZE);
    }

    public boolean isChute(int position) {
        return chutes.containsKey(position);
    }

    public boolean isLadder(int position) {
        return ladders.containsKey(position);
    }
}