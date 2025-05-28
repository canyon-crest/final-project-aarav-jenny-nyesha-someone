package com.chutesandladders;
//defines players
public class Player {
    private String name;
    private int position;
    private boolean isHuman;
//@param 
    public Player(String name, boolean isHuman) {
        this.name = name;
        this.position = 1;
        this.isHuman = isHuman;
    }
    //@return String name of player
    public String getName() {
        return name;
    }
  //@return int position of player
    public int getPosition() {
        return position;
    }
//@param pos - new position
    public void setPosition(int pos) {
        this.position = pos;
    }

    public void move(int spaces) {
    	if(position+spaces<=100)
    		position += spaces;
    }
  //@return boolean state of player
    public boolean isHuman() {
        return isHuman;
    }
}