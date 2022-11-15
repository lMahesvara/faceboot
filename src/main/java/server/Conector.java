package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entidades.BlackBoardObject;
import entidades.Sexo;
import entidades.Usuario;
import helpers.Peticiones;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conector {

    private Socket cliente;
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
        new Thread(new Runnable(){
            @Override
            public void run() {
                String peticion;
                while(cliente.isConnected()){
                    try {
                        peticion = entrada.readLine();
                        System.out.println("2"+ peticion);
                    } catch (IOException ex) {
                        Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }
    
    public void suscribir(){
        
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

//    public String mappear() {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            Usuario usuario = new Usuario("kura", "1234", "asd@asd.com", "1231231231", Calendar.getInstance(), Sexo.HOMBRE, null);
//            BlackBoardObject bbo = new BlackBoardObject(Peticiones.REGISTRAR_USUARIO, usuario);
//            
//            String peticion = objectMapper.writeValueAsString(bbo);
//            return peticion;
//        } catch (JsonProcessingException ex) {
//            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
}
