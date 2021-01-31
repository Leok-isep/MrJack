package com.company;

import java.util.*;

public class Board {
    //////On créé l'ensemble des variables qui compose notre board//////

    //Entier qui correspond au numéro du tour qui se joue :
    public int gameTurn;
    //Alibi qui est Mr Jack :
    public static Alibi mrJack;
    //Liste d'une liste qui sera le plateau :
    public ArrayList<ArrayList<District>> tableauDistrict = new ArrayList<>(3);
    //Liste qui contient les trois détectives :

    public ArrayList<Detective> detectives = new ArrayList<>(3);
    //Liste qui contient les jetons :
    public ArrayList<Token> tokenList;
    //
    public ArrayList<Alibi> deck;
    //Pile qui sera la pile de carte alibi mélangée:
    public Stack<Alibi> stack = new Stack<>();

    public ArrayList<Time> sandGlass = new ArrayList<>(8);
    public ArrayList<Time> turn = new ArrayList<>(8);

    public Board() {
        this.init();
    }

    public void init() {

        //On initie le tour de jeu à 1 :
        gameTurn = 1;

        //On créé une liste avec les noms des alibis dans l'ordre :
        deck = new ArrayList<>(Arrays.asList(Alibi.values()));
        //On mélange le deck de cartes :
        Collections.shuffle(deck);
        //On met le deck dans une pile :
        for (Alibi card : deck) {
            stack.push(card);
        }

        //On créé une liste qui contient les jetons actions :
        tokenList = new ArrayList<>(Arrays.asList(Token.values()));

        //On pioche la première carte qui sera MrJack et on la retire de la pile de carte :
        mrJack = stack.peek();
        stack.remove(stack.size() - 1);

        //On créé le plateau de jeu :
        //On créé une première boucle avec une liste des Districts qui représente les 3 lignes :
        for (int i = 0; i < 3; i++) {
            ArrayList<District> row = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Direction direction = Direction.values()[(int) (Math.random() * 3)];
                row.add(new District(deck.get(3 * i + j), direction, true));
            }
            tableauDistrict.add(row);
        }
        
        //Au début du jeu, les détectives doivent être face à un mur :
        //Holmes doit être face à un mur
        tableauDistrict.get(0).get(0).setDirection(Direction.West);
        //Watson doit être face à un mur
        tableauDistrict.get(0).get(2).setDirection(Direction.East);
        //Toby doit être face à un mur
        tableauDistrict.get(2).get(1).setDirection(Direction.South);

        //On ajoute à notre liste les 3 détectives :
        detectives.add(Detective.Holmes);
        detectives.add(Detective.Watson);
        detectives.add(Detective.Toby);
    }

    //Booléen s'il s'agit de l'enquêteur qui joue :
    public boolean isInvestigatorTurn(){
        return gameTurn % 2 == 1;
    }

    //Action jeton Alibi :
    public void removeStack(){
        System.out.println("Vous avez pioché " + stack.peek());
        stack.remove(stack.size() - 1);
        if (stack.peek() == Alibi.madame){
            sandGlass.add(Time.tokenTime);
            sandGlass.add(Time.tokenTime);
        }
        else if (stack.peek() == Alibi.jeremyBert){
            sandGlass.add(Time.tokenTime);
        }
        else if (stack.peek() == Alibi.williamGull){
            sandGlass.add(Time.tokenTime);
        }
        else if (stack.peek() == Alibi.missStealthy){
            sandGlass.add(Time.tokenTime);
        }
        else if (stack.peek() == Alibi.johnSmith){
            sandGlass.add(Time.tokenTime);
        }
        else if (stack.peek() == Alibi.johnPizer){
            sandGlass.add(Time.tokenTime);
        }
        else if (stack.peek() == Alibi.josephLane){
            sandGlass.add(Time.tokenTime);
        }
        if (sandGlass.size() >= 6){
            System.out.println("Mr. Jack a gagné la partie en obtenant au moins six sabliers. L'Enquêteur a perdu trop de temps.");
        }
    }

    //Action Holmes, Watson ou Toby, Joker où l'on choisit le détective que l'on souhaite déplacer d'une ou deux cases :
    public void moveDetective(Detective detective) {
        System.out.println("Vous devez faire avancer d'une ou deux cases " + detective + ".");
        System.out.println("Tapez 1 pour faire avancer un détective d'une case.\n " +
                "Tapez 2 pour faire avancer un détective de deux cases");
        int numberCase = Game.scanner.nextInt();
        detective.moveDetective(numberCase);
    }

    //Action jeton Rotation :
    public void rotate(){
        System.out.println("Sélectionner un quartier");
        System.out.println("Entrez la ligne du quartier :");
        int quartier1Line = Game.scanner.nextInt();
        System.out.println("Entrez la colonne du quartier :");
        int quartier1Column = Game.scanner.nextInt();
        District district;
        district = tableauDistrict.get(quartier1Line).get(quartier1Column);
        district.rotation();
    }
    //Action jeton Echange :
    public void exchange(){
        System.out.println("Sélectionner un quartier");
        System.out.println("Entrez la ligne du quartier :");
        int quartier1Line = Game.scanner.nextInt();
        System.out.println("Entrez la colonne du quartier :");
        int quartier1Column = Game.scanner.nextInt();
        System.out.println("Sélectionner un second quartier");
        System.out.println("Entrez la ligne du quartier :");
        int quartier2Line = Game.scanner.nextInt();
        System.out.println("Entrez la colonne du quartier :");
        int quartier2Column = Game.scanner.nextInt();
        District permut;
        permut = tableauDistrict.get(quartier1Line).get(quartier1Column);
        tableauDistrict.get(quartier1Line).set(quartier1Column, tableauDistrict.get(quartier2Line).get(quartier2Column));
        tableauDistrict.get(quartier2Line).set(quartier2Column, permut);
        System.out.println("Les quartiers sélectionnés ont été échangés.");
    }

    //Méthode actions joué lors de la Traque :
    public void actions(Token token) {
        //Si le jeton est pile (donc true)
        if (token.isHead()) {
            switch (token.ordinal()) {
                //Jeton Alibi
                case 0:
                    removeStack();
                    break;
                //Jeton Toby
                case 1:
                    //Toby a été choisit et doit avancer d'une ou deux cases
                    moveDetective(detectives.get(2));
                    break;
                //Jetons rotate (il y en a deux)
                default:
                    rotate();
            }
        }
        //Si le jeton est face (donc false) :
        else {
            switch (token.ordinal()) {
                //Jeton Holmes
                case 0:
                    //Holmes a été choisit et doit avancer d'une ou deux cases
                    moveDetective(detectives.get(0));
                    break;
                //Jeton Watson
                case 1:
                    //Watson a été choisit et doit avancer d'une ou deux cases
                    moveDetective(detectives.get(1));
                    break;
                //Jeton Echange
                case 2:
                    exchange();
                    break;
                //Jeton Joker
                default:
                    System.out.println("Le jeton Joker vous permet de choisir un des trois détectives :\n" +
                            "Tapez 0 pour Holmes \n" +
                            "Tapez 1 pour Watson \n" +
                            "Tapez 2 pour Toby");
                    Detective detective = detectives.get(Game.scanner.nextInt());
                    detective.moveDetective(1);
            }
        }
    }

    public void endGameTurn(){
        gameTurn++;
    }

    public ArrayList<Token> getTokens(){
        return tokenList;
    }

    public void tokensTurnOdd(){
        tokenList.get(0).setIsHead((Math.random() < 0.5));
        tokenList.get(1).setIsHead((Math.random() < 0.5));
        tokenList.get(2).setIsHead((Math.random() < 0.5));
        tokenList.get(3).setIsHead((Math.random() < 0.5));
    }

    public void tokensTurnPair(){
        for (int i = 0;i<tokenList.size();i++){
            if(tokenList.get(i).isHead()){
                tokenList.get(i).setIsHead(false);
            }else{
                tokenList.get(i).setIsHead(true);
            }
        }
    }

    public void endGame(){
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Si oui entrez 1, sinon entrez 0 :");
        int isMrJackInFrontOf = scanner.nextInt();
        if (isMrJackInFrontOf == 0) {
            System.out.println("Entrez le nombre de quartiers avec une face suspect qui sont dans la ligne de vue des détectives : ");
            int numberDistrictsView = scanner.nextInt();
            for(int i = 0; i<numberDistrictsView; i++){
                System.out.println("Entrez la ligne du quartier : ");
                int j = scanner.nextInt();
                System.out.println("Entrez la colonne du quartier : ");
                int k = scanner.nextInt();
                tableauDistrict.get(j).get(k).setIsSuspect(false);
            }
            System.out.println("Les quartiers sélectionnés avec une face suspect qui sont dans la ligne de vue des détectives sont retournés sur leur face vide.");
            System.out.println("Mr. Jack prend le jeton Temps du tour et le place devant lui face Sablier apparente");
            sandGlass.add(Time.tokenTime);
            if (sandGlass.size() >= 6){
                System.out.println("Mr. Jack a gagné la partie en obtenant au moins six sabliers. L'Enquêteur a perdu trop de temps.");
            }
        }else{
            System.out.println("Entrez le nombre de quartiers avec une face suspect qui ne sont pas dans la ligne de vue des détectives : ");
            int numberDistrictsNoView = scanner.nextInt();
            for(int i = 0; i<numberDistrictsNoView; i++){
                System.out.println("Entrez la ligne du quartier : ");
                int j = scanner.nextInt();
                System.out.println("Entrez la colonne du quartier : ");
                int k = scanner.nextInt();
                tableauDistrict.get(j).get(k).setIsSuspect(false);
            }
            System.out.println("Les quartiers sélectionnés avec une face suspect qui sont dans la ligne de vue des détectives sont retournés sur leur face vide.");
            System.out.println("L’Enquêteur prend ensuite le jeton Temps du tour qu’il place devant lui, privant ainsi Mr. Jack de Sablier");
            turn.add(Time.tokenTime);
        }
    }

    public String gameBoard() {
        StringBuilder gameBoard = new StringBuilder("\n");
        for (ArrayList<District> row : tableauDistrict) {
            for (int i=0; i<5; i++) {
                if(i>0 && i<4) {
                    gameBoard.append(" ").append(row.get(i - 1));
                }
            }
            gameBoard.append("\n");
        }
        return "Voici en temps réel le plateau de jeu :" + gameBoard
                + "\nVous avez les détectives avec leurs positions respectives : " + detectives
                + "\nVoici les quatre jetons pour le tour " + gameTurn +" : " + tokenList
                + "\nLes jetons Sabliers de Mr.Jack sont : " + sandGlass
                + "\nLes jetons Temps du tour de l'Enquêteur sont  : " + turn;
    }
}
