package com.chutesandladders;

public abstract class Jump implements Jumpable {
    protected int start;
    protected int end;

    public Jump(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStartPosition() {
        return start;
    }

    public int getEndPosition() {
        return end;
    }

    @Override
    public int getTargetPosition() {
        return end;
    }

    public abstract String getType();
}
