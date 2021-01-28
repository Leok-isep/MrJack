package com.company;

public enum Alibi {
    inspecteurLestrade,
    missStealthy,
    jeremyBert,
    johnPizer,
    johnSmith,
    josephLane,
    madame,
    sgtGoodley,
    williamGull;

    public int numberSandGlass;

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

    public int getNumberSandGlass() {
        return numberSandGlass;
    }



}
