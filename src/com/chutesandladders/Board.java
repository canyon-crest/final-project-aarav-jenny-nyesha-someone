package com.chutesandladders;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private static final int SIZE = 100;
    private Map<Integer, Jump> jumps;

    public Board() {
        jumps = new HashMap<>();
        initializeJumps();
    }

    private void initializeJumps() {
        // Chutes
        jumps.put(16, new Chute(16, 6));
        jumps.put(47, new Chute(47, 26));
        jumps.put(49, new Chute(49, 11));
        jumps.put(56, new Chute(56, 53));
        jumps.put(62, new Chute(62, 19));
        jumps.put(64, new Chute(64, 60));
        jumps.put(87, new Chute(87, 24));
        jumps.put(93, new Chute(93, 73));
        jumps.put(95, new Chute(95, 75));
        jumps.put(98, new Chute(98, 78));

        // Ladders
        jumps.put(1, new Ladder(1, 38));
        jumps.put(4, new Ladder(4, 14));
        jumps.put(9, new Ladder(9, 31));
        jumps.put(21, new Ladder(21, 42));
        jumps.put(28, new Ladder(28, 84));
        jumps.put(36, new Ladder(36, 44));
        jumps.put(51, new Ladder(51, 67));
        jumps.put(71, new Ladder(71, 91));
        jumps.put(80, new Ladder(80, 100));
    }

    public int getNewPosition(int currentPos) {
        if (jumps.containsKey(currentPos)) {
            return jumps.get(currentPos).getTargetPosition();
        }
        return Math.min(currentPos, SIZE);
    }

    public boolean isChute(int position) {
        return jumps.containsKey(position) && jumps.get(position) instanceof Chute;
    }

    public boolean isLadder(int position) {
        return jumps.containsKey(position) && jumps.get(position) instanceof Ladder;
    }
}
