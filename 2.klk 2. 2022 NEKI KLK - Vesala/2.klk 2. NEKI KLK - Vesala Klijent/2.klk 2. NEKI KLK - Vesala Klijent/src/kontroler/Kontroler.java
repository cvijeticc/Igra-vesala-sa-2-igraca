/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import forme.KlijentskaForma;
import java.util.List;
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

    public void postaviSlovoPokusaj(List<Slovo> slova) {
        if (slova.get(0).getPozicija() == -1) {
            kf.nijePogodjeno();
        }else{
            kf.jestePogodjeno();
        
        }
        
        for (Slovo s : slova) {
        kf.postaviSlovoPokusaj(s);
        }
            
    }

    public void krajIgre(String poruka) {
        kf.krajIgre(poruka);
    }

    

    
    
    
    
}
