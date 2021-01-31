package com.company;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Stack;

public class GameBoard2 extends JFrame implements ActionListener {
    // Initialisation d'une partie des variables
    Board board;
    public JButton[][] district = new JButton[3][3];
    public JButton[] tokenB = new JButton[4];
    public JButton[] miniAlibiCard = new JButton[10];
    public JButton[] buttonPosition = new JButton[12];
    public boolean rotateBoolean = true;
    public boolean sherlockBoolean = true;
    public boolean watsonBoolean = true;
    public boolean tobyBoolean = true;
    public boolean alibiBoolean1 = true;
    public boolean alibiBoolean2 = true;
    public boolean allDetectives = true;
    public JPanel pane2 = new JPanel();
    JButton sherlockToken = new JButton();
    JButton watsonToken = new JButton();
    JButton tobyToken = new JButton();
    public ArrayList<Alibi> deck;
    public Stack<Alibi> stack = new Stack<>();
    public int i = 0;


    public GameBoard2(Board board) {

        //Création de la fenêtre de jeu
        super("MrJackPocket");  //titre de la fenêtre
        this.board = board;
        this.setSize(new Dimension(1800, 1000));    //dimension de la fenêtre
        this.setLocationRelativeTo(null);
        ImageIcon sherlockIcon = new ImageIcon("./IG/SherlockIcon.png");
        this.setIconImage(sherlockIcon.getImage());     //icon de la fenêtre


        pane2.setBackground(new Color(144, 123, 97, 255));      //couleur du background

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        pane2.setLayout(null);      //Layout du panel


        deck = new ArrayList<>(Arrays.asList(Alibi.values()));
        //On mélange le deck de cartes :
        Collections.shuffle(deck);
        //On met le deck dans une pile :
        for (Alibi card : deck) {
            stack.push(card);
        }

        //Création de la carte alibi (position, action listenner)
        final ImageIcon alibi = new ImageIcon("./IG/alibi-card.png");
        JButton alibicard = new JButton(alibi);
        alibicard.setBounds(10, 580, 100, 167);
        alibicard.addActionListener(e -> {
            if (!alibiBoolean1) {
                if (i < 9) {
                    miniAlibiCard[i].setVisible(true);
                    i++;
                    alibiBoolean2 = !alibiBoolean2;
                    alibiBoolean1 = !alibiBoolean1;
                } else
                    System.out.println("Plus de carte alibi");
            }

        });
        pane2.add(alibicard);
        this.add(pane2);


        this.setVisible(true);      //permet de voir la fenêtre

        initDetective();
        districtManagement(pane2);
        turnManagement(pane2);
        buttonPos();
        setMiniAlibiCard();

    }

    public void initDetective() { // Méthode pour donner une position initiale aux detectives + ajout sur le panel + bouton sans fond

        final ImageIcon sherlockTokenImage = new ImageIcon("./IG/Sherlock.png");
        sherlockToken = new JButton(sherlockTokenImage);
        sherlockToken.setBounds(moveDetective(Detective.Holmes.getPosition()).get(0), moveDetective(Detective.Holmes.getPosition()).get(1), 100, 100);
        final ImageIcon watsonTokenImage = new ImageIcon("./IG/Watson.png");
        watsonToken = new JButton(watsonTokenImage);
        watsonToken.setBounds(moveDetective(Detective.Watson.getPosition()).get(0), moveDetective(Detective.Watson.getPosition()).get(1), 100, 100);
        final ImageIcon tobyTokenImage = new ImageIcon("./IG/Tobi.png");
        tobyToken = new JButton(tobyTokenImage);
        tobyToken.setBounds(moveDetective(Detective.Toby.getPosition()).get(0), moveDetective(Detective.Toby.getPosition()).get(1), 100, 100);
        pane2.add(sherlockToken);
        pane2.add(watsonToken);
        pane2.add(tobyToken);
        cleanButton(sherlockToken);
        cleanButton(watsonToken);
        cleanButton(tobyToken);

    }
    // ArrayList qui va, a l'aide d'un switch, attribuer aux positions des coordonnées x et y (pixels) pour les detectives
    public ArrayList<Integer> moveDetective(int pos) {
        ArrayList<Integer> listPos = new ArrayList<>();
        switch (pos) {
            case 0:
                listPos.add(Position.position0.getX());
                listPos.add(Position.position0.getY());
                break;
            case 1:
                listPos.add(Position.position1.getX());
                listPos.add(Position.position1.getY());
                break;
            case 2:
                listPos.add(Position.position2.getX());
                listPos.add(Position.position2.getY());
                break;
            case 3:
                listPos.add(Position.position3.getX());
                listPos.add(Position.position3.getY());
                break;
            case 4:
                listPos.add(Position.position4.getX());
                listPos.add(Position.position4.getY());
                break;
            case 5:
                listPos.add(Position.position5.getX());
                listPos.add(Position.position5.getY());
                break;
            case 6:
                listPos.add(Position.position6.getX());
                listPos.add(Position.position6.getY());
                break;
            case 7:
                listPos.add(Position.position7.getX());
                listPos.add(Position.position7.getY());
                break;
            case 8:
                listPos.add(Position.position8.getX());
                listPos.add(Position.position8.getY());
                break;
            case 9:
                listPos.add(Position.position9.getX());
                listPos.add(Position.position9.getY());
                break;
            case 10:
                listPos.add(Position.position10.getX());
                listPos.add(Position.position10.getY());
                break;
            case 11:
                listPos.add(Position.position11.getX());
                listPos.add(Position.position11.getY());
                break;

        }
        return listPos;
    }
    //Méthode pour avoir des boutons sans fond
    public static void cleanButton(JButton button) {
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(null);
    }

    //Création des districts (le plateau) avec en plus les images
    public void districtManagement(JPanel pane2) {

        ArrayList<Alibi> urlDeck;
        urlDeck = new ArrayList<>(Arrays.asList(Alibi.values()));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                district[i][j] = new JButton();
                district[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
                            rotateBoolean = !rotateBoolean;
                        }
                    };

                });
                district[i][j].addActionListener(f -> {
                    if (!rotateBoolean ) {
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                if (f.getSource() == district[k][l]) {
                                    district[k][l].setIcon(ImageMod.rotate(district[k][l].getIcon(), 90.));
                                }

                            }
                        }
                    }


                });
                switch (board.tableauDistrict.get(i).get(j).getCard()) {
                    case inspecteurLestrade:
                        district[i][j].setIcon(new ImageIcon(urlDeck.get(0).urlDistrict));

                        break;

                    case missStealthy:
                        district[i][j].setIcon(new ImageIcon(urlDeck.get(1).urlDistrict));
                        break;

                    case jeremyBert:
                        district[i][j].setIcon(new ImageIcon(urlDeck.get(2).urlDistrict));
                        break;

                    case johnPizer:
                        district[i][j].setIcon(new ImageIcon(urlDeck.get(3).urlDistrict));
                        break;

                    case johnSmith:
                        district[i][j].setIcon(new ImageIcon(urlDeck.get(4).urlDistrict));
                        break;

                    case josephLane:
                        district[i][j].setIcon(new ImageIcon(urlDeck.get(5).urlDistrict));
                        break;

                    case madame:
                        district[i][j].setIcon(new ImageIcon(urlDeck.get(6).urlDistrict));
                        break;

                    case sgtGoodley:
                        district[i][j].setIcon(new ImageIcon(urlDeck.get(7).urlDistrict));
                        break;

                    case williamGull:
                        district[i][j].setIcon(new ImageIcon(urlDeck.get(8).urlDistrict));
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + board.tableauDistrict.get(i).get(j).getCard());
                }

                switch (board.tableauDistrict.get(i).get(j).getDirection()) {
                    case South:
                        break;
                    case West:
                        district[i][j].setIcon(ImageMod.rotate(district[i][j].getIcon(), 90.));
                        break;
                    case North:
                        district[i][j].setIcon(ImageMod.rotate(district[i][j].getIcon(), 180.));
                        break;
                    case East:
                        district[i][j].setIcon(ImageMod.rotate(district[i][j].getIcon(), 270.));
                        break;

                }
                pane2.add(district[i][j]);
            }
        }
        district[0][0].setBounds(525, 110, 250, 250);
        district[0][1].setBounds(775, 110, 250, 250);
        district[0][2].setBounds(1025, 110, 250, 250);
        district[1][0].setBounds(525, 360, 250, 250);
        district[1][1].setBounds(775, 360, 250, 250);
        district[1][2].setBounds(1025, 360, 250, 250);
        district[2][0].setBounds(525, 610, 250, 250);
        district[2][1].setBounds(775, 610, 250, 250);
        district[2][2].setBounds(1025, 610, 250, 250);

    }

    public void turnManagement(JPanel pane2) {

        board.tokensTurnOdd();

        tokenManagement(pane2);


    }
    //Méthode pour mettre des boutons tout autour du plateau (invisible) afin de choisir le nombre de case de déplacement
    public void buttonPos() {
        for (int i = 0; i < 12; i++) {
            buttonPosition[i] = new JButton();
            buttonPosition[i].setVisible(false);
            buttonPosition[i].setIcon(new ImageIcon("./IG/PosButton.png"));
            cleanButton(buttonPosition[i]);
            pane2.add(buttonPosition[i]);
            buttonPosition[i].addActionListener(g -> {
                if (!sherlockBoolean) {
                    for (int j = 0; j < 12; j++) {
                        if (g.getSource() == buttonPosition[j]) {
                            Detective.Holmes.moveDetective(j - Detective.Holmes.getPosition());
                            sherlockToken.setVisible(false);
                            watsonToken.setVisible(false);
                            tobyToken.setVisible(false);
                            initDetective();
                            buttonPosition[j].setVisible(false);
                            buttonPosition[j + 1].setVisible(false);
                            buttonPosition[j - 1].setVisible(false);
                            sherlockBoolean = !sherlockBoolean;
                        }
                    }
                } else if (!watsonBoolean) {
                    for (int j = 0; j < 12; j++) {
                        if (g.getSource() == buttonPosition[j]) {
                            Detective.Watson.moveDetective(j - Detective.Watson.getPosition());
                            watsonToken.setVisible(false);
                            sherlockToken.setVisible(false);
                            tobyToken.setVisible(false);
                            initDetective();
                            buttonPosition[j].setVisible(false);
                            buttonPosition[j + 1].setVisible(false);
                            buttonPosition[j - 1].setVisible(false);
                            watsonBoolean = !watsonBoolean;
                        }
                    }
                } else if (!tobyBoolean) {
                    for (int j = 0; j < 12; j++) {
                        if (g.getSource() == buttonPosition[j]) {
                            Detective.Toby.moveDetective(j - Detective.Toby.getPosition());
                            tobyToken.setVisible(false);
                            watsonToken.setVisible(false);
                            sherlockToken.setVisible(false);
                            initDetective();
                            buttonPosition[j].setVisible(false);
                            buttonPosition[j + 1].setVisible(false);
                            buttonPosition[j - 1].setVisible(false);
                            tobyBoolean = !tobyBoolean;
                        }
                    }
                } else if (!allDetectives) {
                    for (int j = 0; j < 12; j++) {
                        if (g.getSource() == buttonPosition[j]) {
                            if (j - 1 == Detective.Holmes.getPosition()) {
                                Detective.Holmes.moveDetective(j - Detective.Holmes.getPosition());
                                tobyToken.setVisible(false);
                                watsonToken.setVisible(false);
                                sherlockToken.setVisible(false);
                                initDetective();
                                buttonPosition[j].setVisible(false);
                                buttonPosition[Detective.Toby.getPosition()+1].setVisible(false);
                                buttonPosition[Detective.Holmes.getPosition()+1].setVisible(false);
                                buttonPosition[Detective.Watson.getPosition()+1].setVisible(false);
                                allDetectives = !allDetectives;
                            }else if (j - 1 == Detective.Watson.getPosition()){
                                Detective.Watson.moveDetective(j - Detective.Watson.getPosition());
                                tobyToken.setVisible(false);
                                watsonToken.setVisible(false);
                                sherlockToken.setVisible(false);
                                initDetective();
                                buttonPosition[j].setVisible(false);
                                buttonPosition[Detective.Toby.getPosition()+1].setVisible(false);
                                buttonPosition[Detective.Holmes.getPosition()+1].setVisible(false);
                                buttonPosition[Detective.Watson.getPosition()+1].setVisible(false);
                                allDetectives = !allDetectives;

                            }else if (j - 1 == Detective.Toby.getPosition()) {
                                Detective.Toby.moveDetective(j - Detective.Toby.getPosition());
                                tobyToken.setVisible(false);
                                watsonToken.setVisible(false);
                                sherlockToken.setVisible(false);
                                initDetective();
                                buttonPosition[j].setVisible(false);
                                buttonPosition[Detective.Toby.getPosition()+1].setVisible(false);
                                buttonPosition[Detective.Holmes.getPosition()+1].setVisible(false);
                                buttonPosition[Detective.Watson.getPosition()+1].setVisible(false);
                                allDetectives = !allDetectives;
                            }
                        }
                    }
                }


            });


        }

        buttonPosition[0].setBounds(Position.position0.getX(), Position.position0.getY(), 100, 100);
        buttonPosition[1].setBounds(Position.position1.getX(), Position.position1.getY(), 100, 100);
        buttonPosition[2].setBounds(Position.position2.getX(), Position.position2.getY(), 100, 100);
        buttonPosition[3].setBounds(Position.position3.getX(), Position.position3.getY(), 100, 100);
        buttonPosition[4].setBounds(Position.position4.getX(), Position.position4.getY(), 100, 100);
        buttonPosition[5].setBounds(Position.position5.getX(), Position.position5.getY(), 100, 100);
        buttonPosition[6].setBounds(Position.position6.getX(), Position.position6.getY(), 100, 100);
        buttonPosition[7].setBounds(Position.position7.getX(), Position.position7.getY(), 100, 100);
        buttonPosition[8].setBounds(Position.position8.getX(), Position.position8.getY(), 100, 100);
        buttonPosition[9].setBounds(Position.position9.getX(), Position.position9.getY(), 100, 100);
        buttonPosition[10].setBounds(Position.position10.getX(), Position.position10.getY(), 100, 100);
        buttonPosition[11].setBounds(Position.position11.getX(), Position.position11.getY(), 100, 100);

    }
    //gestion des jetons + attibution de l'image qui leur correspond
    public void tokenManagement(JPanel panel) {

        for (int i = 0; i < 4; i++) {
            tokenB[i] = new JButton();
            tokenB[i].addActionListener(this);
            //board.tokenList.get(i).getIsHead()?:;
            switch (i) {
                case 0:
                    if (board.tokenList.get(i).getIsHead()) {
                        tokenB[i].setIcon(new ImageIcon("./IG/AlibiToken.png"));

                    } else {
                        tokenB[i].setIcon(new ImageIcon("./IG/SherlockToken.png"));
                    }
                    break;
                case 1:
                    if (board.tokenList.get(i).getIsHead()) {
                        tokenB[i].setIcon(new ImageIcon("./IG/TobyToken.png"));

                    } else {
                        tokenB[i].setIcon(new ImageIcon("./IG/WatsonToken.png"));
                    }
                    break;
                case 2:
                    if (board.tokenList.get(i).getIsHead()) {
                        tokenB[i].setIcon(new ImageIcon("C./IG/RotateToken.png"));


                    } else {
                        tokenB[i].setIcon(new ImageIcon("./IG/SwitchToken.png"));

                    }
                    break;
                case 3:
                    if (board.tokenList.get(i).getIsHead()) {
                        tokenB[i].setIcon(new ImageIcon("./IG/RotateToken.png"));
                    } else {
                        tokenB[i].setIcon(new ImageIcon("./IG/InspectorsToken.png"));
                    }
                    break;

            }

            cleanButton(tokenB[i]);
            panel.add(tokenB[i]);

        }
        tokenB[0].setBounds(10, 875, 80, 80);
        tokenB[1].setBounds(100, 875, 80, 80);
        tokenB[2].setBounds(190, 875, 80, 80);
        tokenB[3].setBounds(280, 875, 80, 80);

    }

    //Gestion de l'interraction entre l'utilisateur et les boutons (action effctuée par un utilisateur
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    if (e.getSource() == tokenB[0] & board.tokenList.get(0).getIsHead()) {
                        alibiBoolean1 = !alibiBoolean1;
                        tokenB[0].setVisible(false);
                        break;
                    } else if (e.getSource() == tokenB[0] & !board.tokenList.get(0).getIsHead()) {
                        sherlockBoolean = !sherlockBoolean;
                        int a = Detective.Holmes.getPosition() + 1;
                        int b = Detective.Holmes.getPosition() + 2;
                        buttonPosition[a].setVisible(true);
                        buttonPosition[b].setVisible(true);
                        tokenB[0].setVisible(false);
                        break;

                    }
                    break;

                case 1:
                    if (e.getSource() == tokenB[1] & board.tokenList.get(1).getIsHead()) {
                        tobyBoolean = !tobyBoolean;
                        int a = Detective.Toby.getPosition() + 1;
                        int b = Detective.Toby.getPosition() + 2;
                        buttonPosition[a].setVisible(true);
                        buttonPosition[b].setVisible(true);
                        tokenB[1].setVisible(false);
                        break;
                    } else if (e.getSource() == tokenB[1] & !board.tokenList.get(1).getIsHead()) {
                        watsonBoolean = !watsonBoolean;
                        int a = Detective.Watson.getPosition() + 1;
                        int b = Detective.Watson.getPosition() + 2;
                        buttonPosition[a].setVisible(true);
                        buttonPosition[b].setVisible(true);
                        tokenB[1].setVisible(false);

                        break;
                    }

                    break;
                case 2:
                    if (e.getSource() == tokenB[2] & board.tokenList.get(2).getIsHead()) {
                        rotateBoolean = !rotateBoolean;
                        tokenB[2].setVisible(false);
                        break;
                    } else if (e.getSource() == tokenB[2] & !board.tokenList.get(2).getIsHead()) {
                        break;
                    }
                    break;
                case 3:
                    if (e.getSource() == tokenB[3] & board.tokenList.get(3).getIsHead()) {
                        rotateBoolean = !rotateBoolean;
                        tokenB[3].setVisible(false);
                    } else if (e.getSource() == tokenB[3] & !board.tokenList.get(3).getIsHead()) {
                        allDetectives = !allDetectives;
                        int a = Detective.Watson.getPosition() + 1;
                        int b = Detective.Holmes.getPosition() + 1;
                        int c = Detective.Toby.getPosition() + 1;
                        buttonPosition[a].setVisible(true);
                        buttonPosition[b].setVisible(true);
                        buttonPosition[c].setVisible(true);
                        tokenB[3].setVisible(false);

                        break;

                    }

            }
        }
    }
    //Permet de placer les différentes carte alibi après avoir été retournées
    public void setMiniAlibiCard() {
        for (int i = 0; i < 10; i++) {
            miniAlibiCard[i] = new JButton();
            miniAlibiCard[i].setVisible(false);

            pane2.add(miniAlibiCard[i]);

        }
        miniAlibiCard[0].setBounds(10, 767, 48, 80);
        miniAlibiCard[0].setIcon(new ImageIcon(stack.get(8).urlIcon));
        miniAlibiCard[1].setBounds(63, 767, 48, 80);
        miniAlibiCard[1].setIcon(new ImageIcon(stack.get(1).urlIcon));
        miniAlibiCard[2].setBounds(116, 767, 48, 80);
        miniAlibiCard[2].setIcon(new ImageIcon(stack.get(6).urlIcon));
        miniAlibiCard[3].setBounds(169, 767, 48, 80);
        miniAlibiCard[3].setIcon(new ImageIcon(stack.get(5).urlIcon));
        miniAlibiCard[4].setBounds(222, 767, 48, 80);
        miniAlibiCard[4].setIcon(new ImageIcon(stack.get(4).urlIcon));
        miniAlibiCard[5].setBounds(275, 767, 48, 80);
        miniAlibiCard[5].setIcon(new ImageIcon(stack.get(3).urlIcon));
        miniAlibiCard[6].setBounds(328, 767, 48, 80);
        miniAlibiCard[6].setIcon(new ImageIcon(stack.get(2).urlIcon));
        miniAlibiCard[7].setBounds(381, 767, 48, 80);
        miniAlibiCard[7].setIcon(new ImageIcon(stack.get(1).urlIcon));
        miniAlibiCard[8].setBounds(434, 767, 48, 80);
        miniAlibiCard[8].setIcon(new ImageIcon(stack.get(0).urlIcon));

    }


}









