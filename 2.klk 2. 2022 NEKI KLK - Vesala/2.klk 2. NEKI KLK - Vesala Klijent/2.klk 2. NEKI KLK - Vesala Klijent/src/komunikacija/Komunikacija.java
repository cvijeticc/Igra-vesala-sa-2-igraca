/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kontroler.Kontroler;
import model.Slovo;
import operacije.Operacije;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author andri
 */
public class Komunikacija extends Thread{
    private Socket s;
    private static Komunikacija instance;

    private Komunikacija() {
        try {
            s = new Socket("localhost", 9000);
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Komunikacija getInstance(){
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    @Override
    public void run() {
        while (true) {            
            ServerskiOdgovor so = primiOdgovor();
            switch (so.getOperacija()) {
                case Operacije.POCELA_IGRA:
                    Kontroler.getInstace().pocelaIgra();
                    
                    break;
                case Operacije.POGADJAJ:
                    List<Slovo> slova = (List<Slovo>) so.getOdgovor();
                    //slova.stream().forEach(slovo -> System.err.println(slovo));
                    Kontroler.getInstace().postaviSlovoPokusaj(slova);
                    break;
                case Operacije.KRAJ_IGRE:
                    String poruka = (String) so.getOdgovor();
                    
                    Kontroler.getInstace().krajIgre(poruka);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    
    
    
    public ServerskiOdgovor primiOdgovor(){
        
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return (ServerskiOdgovor) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    
    }
    
    public void posaljiZahtev(KlijentskiZahtev kz){
       
        
        ObjectOutputStream oos; //to je izvuceno ispred na snimku
        try {
            oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(kz);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Socket getS() {
        return s;
    }

    
}
