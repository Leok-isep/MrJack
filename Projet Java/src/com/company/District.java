package com.company;

public class District {
    public Alibi card;
    public Direction direction;

    //Chaque carte possède un alibi et une direction
    public District(Alibi card, Direction direction, boolean isSuspect) {
        this.card = card;
        this.direction = direction;
        this.isSuspect = isSuspect;
    }

    public Alibi getCard() {
        return card;
    }

    public void setCard(Alibi card) {
        this.card = card;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isSuspect;

    //Face suspect de la carte
    public boolean isSuspect(){
        return isSuspect;
    }

    //Si l'alibi est innocent isSuspect est faux
    public void setIsSuspect(boolean isSuspect) {
        this.isSuspect = isSuspect;
    }

    public void rotation(){
        System.out.println("Entrez le nombre de quart de tour que vous souhaitez effectuer");
        int quarterTurn = Game.scanner.nextInt();
        if(quarterTurn == 1){
            this.direction = Direction.values() [(this.direction.ordinal() + quarterTurn) % 4];
        }else if ( quarterTurn == 2){
            this.direction = Direction.values() [(this.direction.ordinal() + quarterTurn) % 4];
        }else if ( quarterTurn == 3){
            this.direction = Direction.values() [(this.direction.ordinal() + quarterTurn) % 4];
        }else{
            System.out.println("Vous ne pouvez chosir une rotation que d'un quart de tour ou d'un demi tour");
        }
    }

    @Override
    //On affiche sur le plateau de jeu les murs, la face suspect ou non et le nom de l'Alibi
    public String toString() {
        switch (this.direction.ordinal()) {
            case 0:
                return "╦" + " " + card + "(" + isSuspect() + ")" ;  //north
            case 1:
                return "╣" + " " + card + "(" + isSuspect() + ")" ;  //east
            case 2:
                return "╩" + " " + card + "(" + isSuspect() + ")" ;  //south
            default:
                return "╠" + " " + card + "(" + isSuspect() + ")" ;  //west

        }
    }
}
