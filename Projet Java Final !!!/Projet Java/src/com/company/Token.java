package com.company;

public enum Token {
    tokenAction1,
    tokenAction2,
    tokenAction3,
    tokenAction4;

    //Le jeton est pile
    public boolean isHead = true;

    public boolean isHead() {
        return isHead;
    }

    boolean setIsHead(boolean isHead) {
        this.isHead = isHead;
        return isHead;
    }


    //Le jeton est à l'enquêteur
    public boolean isToInvestigator;

    public boolean isToInvestigator() {
        return isToInvestigator;
    }

    public void setIsToInvestigator(boolean isToInvestigator) {
        this.isToInvestigator = isToInvestigator;
    }


    @Override
    public String toString() {
        return this.name() + "(" + isHead + ")";
    }
}

