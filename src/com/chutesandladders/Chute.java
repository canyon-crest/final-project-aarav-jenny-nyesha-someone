package com.chutesandladders;


public class Chute extends Jump {
	//@param start and end positions for jump
    public Chute(int start, int end) {
        super(start, end);
    }

    @Override
    //@return the type of jump which is "chute"
    public String getType() {
        return "Chute";
    }
}
