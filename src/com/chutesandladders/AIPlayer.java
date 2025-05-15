package com.chutesandladders;

import java.util.Random;

public class AIPlayer extends Player {
    public AIPlayer(String name) {
        super(name, false);
    }

    public int takeTurn() {
        return new Random().nextInt(6) + 1;
    }
}