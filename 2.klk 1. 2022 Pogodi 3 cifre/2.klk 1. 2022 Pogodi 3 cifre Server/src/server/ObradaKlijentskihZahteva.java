/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SkriveniBroj;
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
        while (s!= null && !s.isClosed()) {
            System.out.println("OKZ" + s + " " + !s.isClosed() ); 
            
            try {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            
            switch (kz.getOperacija()) {
                case Operacije.POGODI_BROJ:
                    SkriveniBroj sb = Controller.getInstance().pogodiBroj((SkriveniBroj)kz.getParam());
                    so.setOdgovor(sb);
                    break;
                case Operacije.NOVA_IGRA:
                    Controller.getInstance().pokreniNovuIgru();
                    break;
//                case val:
//                    
//                    break;
//                default:
//                    throw new AssertionError();
            }
                System.err.println(so);
            posaljiOdgovor(so);
        } catch (IOException ex){
            
        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
     
        }
        
    }
    }

    private KlijentskiZahtev primiZahtev() throws IOException{
        try {
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            //znaci to je input za server
            return (KlijentskiZahtev) ois.readObject();//dodajes ovde jos jedan catch
            
        } catch(EOFException ex){
            System.out.println("Klijent se odvezao");
            s.close();
            s = null;
            
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);//ovde sam napisao oos
            oos.flush();
            
            
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    
    
}
