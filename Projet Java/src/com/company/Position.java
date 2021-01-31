package com.company;

public enum Position {
    position0(400, 180),
    position1(600, 5),
    position2(850, 5),
    position3(1100, 5),
    position4(1300, 180),
    position5(1300, 430),
    position6(1300, 680),
    position7(1100, 860),
    position8(850, 860),
    position9(600,860),
    position10(400,680),
    position11(400, 430);



    public int x;
    public int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
