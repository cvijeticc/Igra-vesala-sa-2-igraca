/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
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
    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {            
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            switch (kz.getOperacija()) {
                case Operacije.POGADJAJ:
                    Controller.getInstance().pogadjanje((Slovo)kz.getParam());
                    //sam ovde dopisujes castovanje
                   
                    //so.setOdgovor(s);//ovde je bila greska
                    
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
    
    
    
}
