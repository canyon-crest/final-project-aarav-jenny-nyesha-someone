package com.chutesandladders;
//defines jump
//to be used with chutes and ladders
public abstract class Jump implements Jumpable {
    protected int start;
    protected int end;

    public Jump(int start, int end) {
        this.start = start;
        this.end = end;
    }
//@return start position
    public int getStartPosition() {
        return start;
    }
//@return end position
    public int getEndPosition() {
        return end;
    }

    @Override
    //@return end position
    public int getTargetPosition() {
        return end;
    }

    public abstract String getType();
}
