package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entidades.BlackBoardObject;
import entidades.Sexo;
import entidades.Usuario;
import helpers.Peticiones;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conector {

    Socket cliente;
    int puerto = 9000;
    String ip = "127.0.0.1";
    PrintStream salida;

    public void iniciar(String json ) {
        try {
            //String json = mappear();
            
            cliente = new Socket(ip, puerto);
            salida = new PrintStream(cliente.getOutputStream());
            salida.println(json);

            salida.close();
            cliente.close();
        } catch (Exception e) {
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
