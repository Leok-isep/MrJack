package com.company;

public enum Alibi {
    inspecteurLestrade ("./IG/InspLestrade.png", "./IG/InspLestrade-alibi.png"),
    missStealthy ("./IG/MissStealthy.png","./IG/MissStealthy-alibi.png"),
    jeremyBert ("./IG/JeremyBert.png","./IG/JeremyBert-alibi.png"),
    johnPizer ("./IG/JohnPizer.png","./IG/JohnPizer-alibi.png"),
    johnSmith ("./IG/JohnSmith.png","./IG/JohnSmith-alibi.png"),
    josephLane ("./IG/JosephLane.png","./IG/JosephLane-alibi.png" ),
    madame ( "./IG/Madame.png","./IG/Madame-alibi.png"),
    sgtGoodley ("./IG/SgtGoodley.png","./IG/SgtGoodley-alibi.png"),
    williamGull ("./IG/WilliamGull.png","./IG/WilliamGull-alibi.png");

    public int numberSandGlass;
    public String urlDistrict;
    public String urlIcon;

     public void sandGlass(Alibi card){
         if (card == inspecteurLestrade){
             numberSandGlass = 0;
         }
         if (card == missStealthy){
             numberSandGlass = 1;
         }
         if (card == jeremyBert){
             numberSandGlass = 1;
         }
         if (card == johnPizer){
             numberSandGlass = 1;
         }
         if (card == johnSmith){
             numberSandGlass = 1;
         }
         if (card == josephLane){
             numberSandGlass = 1;
         }
         if (card == madame){
             numberSandGlass = 2;
         }
         if (card == sgtGoodley){
             numberSandGlass = 0;
         }
         if (card == williamGull){
             numberSandGlass = 1;
         }
     }

    Alibi( String urlDistrict, String urlIcon) {
        this.urlDistrict = urlDistrict;
        this.urlIcon = urlIcon;
    }
    public int getNumberSandGlass() {
        return numberSandGlass;
    }



}
