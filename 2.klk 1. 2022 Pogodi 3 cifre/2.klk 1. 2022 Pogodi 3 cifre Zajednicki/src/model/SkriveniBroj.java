/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author andri
 */
public class SkriveniBroj implements Serializable {
    private int kolona;
    private int red;
    private int vrednost;

    public SkriveniBroj() {
    }

    public SkriveniBroj(int kolona, int red, int vrednost) {
        this.kolona = kolona;
        this.red = red;
        this.vrednost = vrednost;
    }

    public int getKolona() {
        return kolona;
    }

    public void setKolona(int kolona) {
        this.kolona = kolona;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getVrednost() {
        return vrednost;
    }

    public void setVrednost(int vrednost) {
        this.vrednost = vrednost;
    }

    @Override
    public String toString() {
        return "SkriveniBroj{" + "kolona=" + kolona + ", red=" + red + ", vrednost=" + vrednost + '}';
    }
    
    
}
