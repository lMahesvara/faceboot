package server;

import events.EventHandler;
import helpers.ConvertirPeticion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import peticiones.AbstractPeticion;

public class Conector {
    private Socket cliente;
    private Thread recibirPeticiones;
    private int puerto = 9000;
    private String ip = "127.0.0.1";
    private PrintStream salida;
    private BufferedReader entrada;
    private static Conector instance;

    private Conector() {
        try {
            cliente = new Socket(ip, puerto);
            salida = new PrintStream(cliente.getOutputStream());
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            recibirPeticiones();
        } catch (IOException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Conector getInstance() {
        if (instance == null) {
            instance = new Conector();
        }
        return instance;
    }

    public void enviarPeticion(String json) {
        try {
            //String json = mappear();
            if (!json.equals(" ")) {
                salida.println(json);
            }
//            String respuesta = entrada.readLine();
//            System.out.println("JSON: " + respuesta);

        } catch (Exception e) {
            closeAll();
        }
    }
    
    public void recibirPeticiones(){
        recibirPeticiones = new Thread(new Runnable(){
            @Override
            public void run() {
                String json;
                while(cliente.isConnected()){
                    try {
                        json = entrada.readLine();
                        System.out.println("2"+ json);
                        AbstractPeticion peticion = ConvertirPeticion.PetitionConverter(json);
                        System.out.println("ea "+peticion);
                        EventHandler.getInstance().manejarEvento(peticion);
                        //Mandar Peticion -> EventHandler
                        //EventHandler -> Se va a encargar de determinar a que evento corresponde la peticion y va ejecutar el notificarTodos()
                    } catch (IOException ex) {
                        Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        recibirPeticiones.start();
    }
    
    public void detenerHilo(){
        recibirPeticiones.interrupt();
    }

    private void closeAll() {
        try {
            salida.close();
            entrada.close();
            cliente.close();
        } catch (IOException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
