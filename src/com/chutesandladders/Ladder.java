package com.chutesandladders;

//defines ladder based on the start and end position so the character can jump as necessary
public class Ladder extends Jump {
    public Ladder(int start, int end) {
        super(start, end);
    }

    @Override
    public String getType() {
        return "Ladder";
    }
}
