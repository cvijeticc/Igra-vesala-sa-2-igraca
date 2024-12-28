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
public class Slovo implements Serializable {
    private int pozicija;
    private char slovo;

    public Slovo() {
    }

    public Slovo(int pozicija, char slovo) {
        this.pozicija = pozicija;
        this.slovo = slovo;
    }

    public int getPozicija() {
        return pozicija;
    }

    public void setPozicija(int pozicija) {
        this.pozicija = pozicija;
    }

    public char getSlovo() {
        return slovo;
    }

    public void setSlovo(char slovo) {
        this.slovo = slovo;
    }

    @Override
    public String toString() {
        return "Slovo{" + "pozicija=" + pozicija + ", slovo=" + slovo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Slovo other = (Slovo) obj;
        if (this.pozicija != other.pozicija) {
            return false;
        }
        return this.slovo == other.slovo;
    }
    
    
}
