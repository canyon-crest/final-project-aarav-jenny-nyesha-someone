package com.chutesandladders;

import java.util.Random;
//defines AI player compared to human player and randomizes turns
public class AIPlayer extends Player {
	//@param name of computer
	//sets player status to false for being human
    public AIPlayer(String name) {
        super(name, false);
    }
//@return random number between 1 and 6 inclusive
    public int takeTurn() {
        return new Random().nextInt(6) + 1;
    }
}