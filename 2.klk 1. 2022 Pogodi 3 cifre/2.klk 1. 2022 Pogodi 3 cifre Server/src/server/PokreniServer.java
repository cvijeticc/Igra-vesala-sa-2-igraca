/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andri
 */
public class PokreniServer extends Thread {
    
    ObradaKlijentskihZahteva nit;
    
    @Override
    public void run() {
        
        try {
            try {
            ServerSocket serverskiSoket = new ServerSocket(9000);
            while (true) {               
                Socket s = serverskiSoket.accept();// ceka da se klijent poveze
                System.out.println("Klijent je povezan");
                
                nit = new ObradaKlijentskihZahteva(s);
                nit.start();
            }
                } catch (SocketException e) {
                    nit.interrupt(); //ta nit se prekida
                    System.out.println("Klijent nije vise povezan");
                }
        } catch (SocketException e) {
                    System.out.println("Klijent nije vise povezan");
                   
            
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    
}
