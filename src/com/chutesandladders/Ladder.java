package com.chutesandladders;


public class Ladder extends Jump {
    public Ladder(int start, int end) {
        super(start, end);
    }

    @Override
    public String getType() {
        return "Ladder";
    }
}
