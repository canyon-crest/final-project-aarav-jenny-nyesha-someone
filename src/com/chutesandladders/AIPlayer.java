package com.chutesandladders;

import java.util.Random;
//defines AI player compared to human player and randomizes turns
public class AIPlayer extends Player {
    public AIPlayer(String name) {
        super(name, false);
    }

    public int takeTurn() {
        return new Random().nextInt(6) + 1;
    }
}