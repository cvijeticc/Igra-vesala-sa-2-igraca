/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andri
 */
public class PokreniServer extends Thread {
    private boolean kraj = false;
    private ServerSocket serverskiSocket;
   
    
    @Override
    public void run() {
        
        try {
            serverskiSocket = new ServerSocket(9000);
            System.out.println("Socket otvoren");
            while (!kraj) {                
                Socket s = serverskiSocket.accept();
                System.out.println("Klijent povezan");
                
                ObradaKlijentskihZahteva nit = new ObradaKlijentskihZahteva(s);
                Controller.getInstance().getKlijenti().add(nit);
                nit.start();
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void zaustaviServer(){
        System.out.println("Socket zatvoren");
        kraj = true;
        try {
            serverskiSocket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    
    
}
