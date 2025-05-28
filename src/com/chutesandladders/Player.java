package com.chutesandladders;
//defines players
public class Player {
    private String name;
    private int position;
    private boolean isHuman;

    public Player(String name, boolean isHuman) {
        this.name = name;
        this.position = 1;
        this.isHuman = isHuman;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int pos) {
        this.position = pos;
    }

    public void move(int spaces) {
    	if(position+spaces<=100)
    		position += spaces;
    }

    public boolean isHuman() {
        return isHuman;
    }
}