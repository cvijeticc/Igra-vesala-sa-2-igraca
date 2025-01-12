/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import forme.KlijentskaForma;
import model.Slovo;

/**
 *
 * @author andri
 */
public class Kontroler {
    //pravimo ga isto da bude singleton
    private static Kontroler instace;

    private KlijentskaForma kf;
    
    private Kontroler() {
    }

    public static Kontroler getInstace() {
        if (instace == null) {
            instace = new Kontroler();
        }
        return instace;
    }

    public KlijentskaForma getKf() {
        return kf;
    }

    public void setKf(KlijentskaForma kf) {
        this.kf = kf;
    }

    public void pocelaIgra() {
        kf.pocelaIgra();
        
    }

    public void postaviSlovoPokusaj(Slovo slovo) {
        kf.postaviSlovoPokusaj(slovo);
            
    }

    

    
    
    
    
}
