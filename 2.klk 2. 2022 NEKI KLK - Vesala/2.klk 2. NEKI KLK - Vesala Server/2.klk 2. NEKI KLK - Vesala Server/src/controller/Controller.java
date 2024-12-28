/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.Slovo;

/**
 *
 * @author andri
 */
public class Controller {//dbb ins ins dbb
//   private DBBroker dbb;
   private List<String> zadateReci = new ArrayList<>();
   private String odabranaRec;
   private static Controller instance;
   
   private Controller(){
       zadateReci.add("PETAO");
       zadateReci.add("MARKO");
       zadateReci.add("PETAR");
       zadateReci.add("JANKO");
       zadateReci.add("VANJA");

   }
   
   public static Controller getInstance(){
       if (instance == null) {
           instance = new   Controller();
       }
       return instance;
   }

    public List<String> getZadateReci() {
        return zadateReci;
    }

    public void setZadateReci(List<String> zadateReci) {
        this.zadateReci = zadateReci;
    }

    public String getOdabranaRec() {
        return odabranaRec;
    }

    public void setOdabranaRec(String odabranaRec) {
        this.odabranaRec = odabranaRec;
    }

    public Slovo pogadjanje(Slovo slovo) {
        char karakter = slovo.getSlovo();
        for (int i = 0; i < odabranaRec.length(); i++) {
            if (odabranaRec.charAt(i) == slovo.getSlovo()) {
                slovo.setPozicija(i+1);
                return slovo;
            }
        }
        slovo.setPozicija(-1);//ono nisam morao da radim ali da se osiguran
        return slovo;
    }

    
   
   
    
}
