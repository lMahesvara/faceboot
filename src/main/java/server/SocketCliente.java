/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import events.EventHandler;
import helpers.ConvertirPeticion;
import static helpers.Peticiones.CERRAR_SESION;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import peticiones.AbstractPeticion;

/**
 *
 * @author Vastem
 */
public class SocketCliente extends Thread {
   private Socket socket;
    private BufferedReader entrada;
    private PrintStream salida;
    
    private int puerto = 9000;
    private String ip = "127.0.0.1";

    public SocketCliente() {
        try {
            socket = new Socket(ip, puerto);
            salida = new PrintStream(socket.getOutputStream());
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            run();
            recibirPeticiones();
        } catch (IOException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public static SocketCliente getInstance(){
//        if (instance == null) {
//            instance = new SocketCliente();
//        }
//        return instance;
//    }
//    
//    public void setInstanceNull(){
//        this.instance = null;
//    }
    
    @Override
    public void run() {
        String json;
    }
    
    public void sendResponse(String json) {
        try {
            if (!json.equals(" ")) {
              salida.println(json);  
            }
        } catch (Exception ex) {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void recibirPeticiones(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                String json;
                while(socket.isConnected()){
                    try {
                        json = entrada.readLine();
                        System.out.println("2"+ json);
                        AbstractPeticion peticion = ConvertirPeticion.PetitionConverter(json);
                        System.out.println("ea "+peticion);
                        EventHandler.getInstance().manejarEvento(peticion);
                        if(peticion != null && peticion.getRespuesta() != null){
                            if(peticion.getRespuesta().equals(CERRAR_SESION)){
                                closeAll();
                            }
                        } 
                    } catch (IOException ex) {
                        Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }
    
    private void closeAll() {
        try {
            salida.close();
            entrada.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public BufferedReader getEntrada() {
        return entrada;
    }

    public PrintStream getSalida() {
        return salida;
    }
    
}
