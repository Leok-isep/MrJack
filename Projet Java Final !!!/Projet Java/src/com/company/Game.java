package com.company;
import java.util.Scanner;

public class Game {
    public static Scanner scanner = new Scanner(System.in);
    public Board board;

    public Game(){
        board = new Board();
    }

    public void play() {
        System.out.println("Rentrez le prénom du premier joueur : ");
        String name1 = scanner.next();
        System.out.println("Rentrez le prénom du second joueur : ");
        String name2 = scanner.next();
        System.out.println("---Une nouvelle partie commence---");
        System.out.println("\nVoici quelques explications qui pourront vous être utiles au cours d'une partie jouée dans la console." +"\nSi vous devez sélectionner la ligne d'un quartier, 0 sera la première ligne, 1 la seconde et 2 la troisième."
        +"\nSi vous devez sélectionner la colonne d'un quartier, 0 sera la première cononne, 1 la seconde et 2 la troisième."
        +"\nSur le tableau 'true' signifie que la carte est face Suspect et 'false' que la carte est face Vide."
        +"\nSi vous devez sélectionner un jeton, 0 sera le premier jeton, 1 le second, 2 le troisième et 3 le quatrième. Attention veuillez sélectionner un jeton une seule fois par tour."
        +"\nIl est interdit de pivoter un quartier déjà pivoté dans le même tour de jeu. ");
        System.out.println("\n" + name1 + ", vous jouez l'Enquêteur.");
        System.out.println(name2 + ", vous jouez Mr.Jack.");
        System.out.println("\nL'Enquêteur doit fermer les yeux le temps que Mr.Jack découvre sous quel personnage il se cache. S'il ferme les yeux tapez 1.");
        int blind = scanner.nextInt();
        if (blind == 1){
            System.out.println(name2 + ",vous vous cachez sous le personnage : " + Board.mrJack +"." + "\n"+ "\n"+ "\n" + "\n"+ "\n"+ "\n" +"\n"+ "\n"+ "\n" +"\n"+ "\n"+ "\n" +"\n"+ "\n"+ "\n");
        }
        System.out.println("Vous jouez le tour " + board.gameTurn + ".");
        for (board.gameTurn = 1; board.gameTurn < 9; board.gameTurn++) {
            //Le tour est impair, c'est à l'Enquêteur de commencer à jouer
            if (board.isInvestigatorTurn()) {
                board.tokensTurnOdd();
                System.out.println(board.gameBoard());

                System.out.println("Le tour commence par la traque.");
                System.out.println("C'est à " + (board.isInvestigatorTurn() ? name1 : name2) + " de jouer.");
                System.out.println(name1 + ",tapez un numéro entre 0 et 3 compris pour sélectionner le jeton de votre choix : ");
                int choice1 = scanner.nextInt();
                board.getTokens().get(choice1).setIsToInvestigator(true);
                board.actions(board.getTokens().get(choice1));
                System.out.println(board.gameBoard());

                System.out.println(name2 + ",tapez un numéro entre 0 et 3 compris pour sélectionner le jeton de votre choix. Vous ne pouvez pas choisir un jeton qui a déjà été pris :");
                int choice2 = scanner.nextInt();
                board.getTokens().get(choice2).setIsToInvestigator(false);
                board.actions(board.getTokens().get(choice2));
                System.out.println(board.gameBoard());
                System.out.println(name2 + ",tapez un numéro entre 0 et 3 compris pour sélectionner le jeton de votre choix. Vous ne pouvez pas choisir un jeton qui a déjà été pris :");
                int choice3 = scanner.nextInt();
                board.getTokens().get(choice3).setIsToInvestigator(false);
                board.actions(board.getTokens().get(choice3));
                System.out.println(board.gameBoard());

                System.out.println(name1 + ",tapez un numéro entre 0 et 3 compris pour sélectionner le jeton de votre choix : ");
                int choice4 = scanner.nextInt();
                board.getTokens().get(choice4).setIsToInvestigator(true);
                board.actions(board.getTokens().get(choice4));

            }
            //Le tour est pair, c'est à Mr.Jack de commencer à jouer
            else {
                System.out.println("Vous jouez le tour " + board.gameTurn);
                System.out.println("Le tour commence par la traque.");
                System.out.println("Les quatre jetons action du tour précédent sont retournés :");
                board.tokensTurnPair();
                System.out.println(board.gameBoard());

                System.out.println("Le tour commence par la traque.");
                System.out.println(name2 + ",tapez un numéro entre 0 et 3 compris pour sélectionner le jeton de votre choix : ");
                int choice1 = scanner.nextInt();
                board.getTokens().get(choice1).setIsToInvestigator(false);
                board.actions(board.getTokens().get(choice1));
                System.out.println(board.gameBoard());

                System.out.println(name1 + ",tapez un numéro entre 0 et 3 compris pour sélectionner le jeton de votre choix. Vous ne pouvez pas choisir un jeton qui a déjà été pris :");
                int choice2 = scanner.nextInt();
                board.getTokens().get(choice2).setIsToInvestigator(true);
                board.actions(board.getTokens().get(choice2));
                System.out.println(board.gameBoard());
                System.out.println(name1 + ",tapez un numéro entre 0 et 3 compris pour sélectionner le jeton de votre choix. Vous ne pouvez pas choisir un jeton qui a déjà été pris :");
                int choice3 = scanner.nextInt();
                board.getTokens().get(choice3).setIsToInvestigator(true);
                board.actions(board.getTokens().get(choice3));
                System.out.println(board.gameBoard());

                System.out.println(name2 + ",tapez un numéro entre 0 et 3 compris pour sélectionner le jeton de votre choix : ");
                int choice4 = scanner.nextInt();
                board.getTokens().get(choice4).setIsToInvestigator(false);
                board.actions(board.getTokens().get(choice4));

            }
            System.out.println(board.gameBoard());
            System.out.println("Le tour se termine par l'Appel à Témoin : ");
            System.out.println(name2 + ",vous devez indiquer à " + name1 + " si le personnage sous lequel vous vous cachez est visible par un détective ou non c'est-à-dire lorsqu'il se trouve sur la même ligne de vue qu'un Détective, sans qu'un mur ne l'interrompe. Si oui entrez 1, sinon entrez 0.");
            board.endGame();
            System.out.println("S'il ne subsiste qu'un seul Suspect, il est donc le coupable et l'Enquêteur a gagné.");
            System.out.println(board.gameBoard());
        }
    }
}
