package com.chutesandladders;


public class Chute extends Jump {
    public Chute(int start, int end) {
        super(start, end);
    }

    @Override
    public String getType() {
        return "Chute";
    }
}
