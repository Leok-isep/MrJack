package com.company;

public enum Time {
    tokenTime;

    public boolean isTurn;

    public boolean isTurn(){
        return isTurn;
    }

    public Time setIsTurn(boolean isTurn) {
        this.isTurn = isTurn;
        return null;
    }

    public boolean isSandGlass;

    public boolean isSandGlass() {
        return isSandGlass;
    }

    public Time setIsSandGlass(boolean isSandGlass) {
        this.isSandGlass = isSandGlass;
        return null;
    }
}
