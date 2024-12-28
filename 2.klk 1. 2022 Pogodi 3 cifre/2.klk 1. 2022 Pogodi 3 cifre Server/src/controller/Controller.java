/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forma.ServerskaForma;
import java.util.ArrayList;
import java.util.List;
import model.SkriveniBroj;
import model.User;

/**
 *
 * @author andri
 */
public class Controller {
    //dbb ins ins dbb
//    private DBBroker dbb;
    
    private List<User> admini = new ArrayList<>();
    private List<SkriveniBroj> brojevi = new ArrayList<>();
    
    private User ulogovani = null;
    private ServerskaForma sf;
    private static Controller instance;
    public static Controller getInstance(){
        if (instance == null) {
            instance = new Controller();
        }
    return instance;
    }

    private Controller() {
        User u1 = new User("pera@gmail.com", "123456", "Pera", "Peric");
        User u2 = new User("mara@gmail.com", "654321", "Mara", "Maric");
        
        admini.add(u1);
        admini.add(u2);
        
        
    }

    public User login(String email, String lozinka) {
        for (User u : admini) {
            if (u.getEmail().equals(email) && u.getLozinka().equals(lozinka)) {
                ulogovani = u;//sto ovde nisam mogao da napisem User ulogovani = u;
                return u;
            }
        }
        return null;
        
    }

    public User getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(User ulogovani) {
        this.ulogovani = ulogovani;
    }

    public List<SkriveniBroj> getBrojevi() {
        return brojevi;
    }

    public void setBrojevi(List<SkriveniBroj> brojevi) {
        this.brojevi = brojevi;
    }
    
    

    public void dodajBroj(SkriveniBroj sb) {
        brojevi.add(sb);
        System.out.println(brojevi);
    }

    public boolean postoji(SkriveniBroj sb) {
        for (SkriveniBroj skriveniBroj : brojevi) {
            if (sb.getVrednost() == skriveniBroj.getVrednost()) {
                return true;
            }
        }
        return false;
    }

    public SkriveniBroj pogodiBroj(SkriveniBroj skriveniBroj) {
        
        for (SkriveniBroj sb : brojevi) {
             if (sb.getKolona() == skriveniBroj.getKolona() &&
                     sb.getRed() == skriveniBroj.getRed()) {
                return sb;//to ce da vrati i red i kolonu i vrednost
            }
        }
        return null;
    }

    public void pokreniNovuIgru() {
        brojevi = new ArrayList<>();
            sf.pokreniIgru();
        
    }

    public ServerskaForma getSf() {
        return sf;
    }

    public void setSf(ServerskaForma sf) {
        this.sf = sf;
    }
    
}
