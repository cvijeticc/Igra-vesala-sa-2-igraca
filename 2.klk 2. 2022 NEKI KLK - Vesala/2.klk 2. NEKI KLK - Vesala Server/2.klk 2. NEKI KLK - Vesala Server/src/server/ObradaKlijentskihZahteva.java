/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Slovo;
import operacije.Operacije;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author andri
 */
public class ObradaKlijentskihZahteva extends Thread{
    private Socket s;
    private int brojIgraca; // prvi ili drugi
    private int brojPokusaja = 0;
    private int brojPogodjenih = 0;
    
    public ObradaKlijentskihZahteva(Socket s, int brojIgraca) {
        this.s = s;
        this.brojIgraca = brojIgraca;
    }

    @Override
    public void run() {
        while (true) {            
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            switch (kz.getOperacija()) {
                case Operacije.POGADJAJ:
                    int brojSlova = Controller.getInstance().brojPogodjenih((Slovo)kz.getParam())   ;
                    brojPogodjenih += brojSlova;
                    brojPokusaja++;
                    Controller.getInstance().proveraKrajaIgre();
                    
                    Controller.getInstance().osveziLabeleNaFormi();
                    
                    break;
                
                default:
                    System.out.println("Greska");
            }
//            so.setOperacija(kz.getOperacija());
//            posaljiOdgovor(so);
        }
        
    }

    private KlijentskiZahtev primiZahtev() {
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void posaljiOdgovor(ServerskiOdgovor so){
        try { 
            
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    
    
    
    }

    public void obavestiKlijenta(int operacija, Object odgovor) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        so.setOperacija(operacija);
        so.setOdgovor(odgovor);
        
        posaljiOdgovor(so);
    }

    public int getBrojIgraca() {
        return brojIgraca;
    }

    public void setBrojIgraca(int brojIgraca) {
        this.brojIgraca = brojIgraca;
    }

    public int getBrojPokusaja() {
        return brojPokusaja;
    }

    public void setBrojPokusaja(int brojPokusaja) {
        this.brojPokusaja = brojPokusaja;
    }

    public int getBrojPogodjenih() {
        return brojPogodjenih;
    }

    public void setBrojPogodjenih(int brojPogodjenih) {
        this.brojPogodjenih = brojPogodjenih;
    }

    
    
    
    
}
