package com.company;

public enum Detective {
    Holmes(0),
    Watson(4),
    Toby(8);

    public int position;

    Detective(int position) {
        this.position = position;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    //Méthode qui fait avancer 1 ou 2 cases pour Watson, Toby et Holmes dans le sens des aiguilles
    //d'une montre. Pour le joker, si c'est l'enquêteur = 1 case, MrJack = 1 case ou rien.
    public void moveDetective(int numberCase) {
        this.position = (position + numberCase) % 12;
    }

    @Override
    public String toString() {
        return this.name() + "("+position+")";
    }
}
