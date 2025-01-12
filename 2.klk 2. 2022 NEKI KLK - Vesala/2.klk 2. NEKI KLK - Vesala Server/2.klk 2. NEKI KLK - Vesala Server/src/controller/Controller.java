/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.Slovo;
import operacije.Operacije;
import server.ObradaKlijentskihZahteva;

/**
 *
 * @author andri
 */
public class Controller {//dbb ins ins dbb
//   private DBBroker dbb;
   private List<String> zadateReci = new ArrayList<>();
   private String odabranaRec;
   private static Controller instance;
   private List<ObradaKlijentskihZahteva> klijenti = new ArrayList<>();
   
   
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

    public void pogadjanje(Slovo slovo) {
        char karakter = slovo.getSlovo();
        for (int i = 0; i < odabranaRec.length(); i++) {
            if (odabranaRec.charAt(i) == karakter) {
                slovo.setPozicija(i+1);
                break;
            }
        }
        
        for (ObradaKlijentskihZahteva k : klijenti) {
            k.obavestiKlijenta(Operacije.POGADJAJ, slovo);
        }
    }

    public List<ObradaKlijentskihZahteva> getKlijenti() {
        return klijenti;
    }

    public void setKlijenti(List<ObradaKlijentskihZahteva> klijenti) {
        this.klijenti = klijenti;
    }

    
   
   
    
}
