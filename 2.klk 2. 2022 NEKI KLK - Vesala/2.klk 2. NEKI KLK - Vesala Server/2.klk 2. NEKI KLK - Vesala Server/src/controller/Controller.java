/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.ServerskaForma;
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
    private int brojPogodaka = 0;
    private static Controller instance;
    private List<ObradaKlijentskihZahteva> klijenti = new ArrayList<>();
    private ServerskaForma sf;

    private Controller() {
        zadateReci.add("PETAO");
        zadateReci.add("MARKO");
        zadateReci.add("PETAR");
        zadateReci.add("JANKO");
        zadateReci.add("VANJA");

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
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

    public int brojPogodjenih(Slovo slovo) {
        List<Slovo> slova = new ArrayList<>();
        int brojac = 0;
        char karakter = slovo.getSlovo();
        for (int i = 0; i < odabranaRec.length(); i++) {
            if (odabranaRec.charAt(i) == karakter) {
                Slovo novoSlovo = new Slovo(i + 1, karakter);
                slova.add(novoSlovo);

                brojPogodaka++;
                brojac++;
                //slovo.setPozicija(i+1);
            }
        }
        if (slova.isEmpty()) {
            slova.add(slovo);//pozicija = -1

        }

        for (ObradaKlijentskihZahteva k : klijenti) {
            k.obavestiKlijenta(Operacije.POGADJAJ, slova);
        }

        return brojac;
    }

    public void proveraKrajaIgre() {
//        char karakter = slovo.getSlovo();
//        for (int i = 0; i < odabranaRec.length(); i++) {
//            if (odabranaRec.charAt(i) == karakter) {
//                brojPogodaka++;
//                slovo.setPozicija(i+1);
//                break;
//            }
//        }
//        
//        for (ObradaKlijentskihZahteva k : klijenti) {
//            k.obavestiKlijenta(Operacije.POGADJAJ, slovo);
//        }

        String poruka = "";
        if (brojPogodaka == 5) {
            if (klijenti.get(0).getBrojPogodjenih() > klijenti.get(1).getBrojPogodjenih()) {
                poruka = "Pobedio igrac 1";
            } else {
                poruka = "Pobedio igrac 2";
            }
        } else {
            if (klijenti.get(0).getBrojPokusaja() >= 10 && klijenti.get(1).getBrojPokusaja() >= 10) {

                poruka = "Kompljuter je pobedio";
            }
        }
        if (!poruka.isEmpty() || !poruka.equals("")) {

            klijenti.get(0).obavestiKlijenta(Operacije.KRAJ_IGRE, poruka);
            klijenti.get(1).obavestiKlijenta(Operacije.KRAJ_IGRE, poruka);
            sf.prikaziPorukuKrajIgre(poruka);
        }
    }

    public List<ObradaKlijentskihZahteva> getKlijenti() {
        return klijenti;
    }

    public void setKlijenti(List<ObradaKlijentskihZahteva> klijenti) {
        this.klijenti = klijenti;
    }

    public ServerskaForma getSf() {
        return sf;
    }

    public void setSf(ServerskaForma sf) {
        this.sf = sf;
    }

    public int getBrojPogodaka() {
        return brojPogodaka;
    }

    public void setBrojPogodaka(int brojPogodaka) {
        this.brojPogodaka = brojPogodaka;
    }

    public void osveziLabeleNaFormi() {
        sf.osveziLabeleNaFormi();
    }

}
