package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entidades.BlackBoardObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConvertirPeticion {

    public static BlackBoardObject BBOConverter(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            BlackBoardObject bbo = objectMapper.readValue(json, BlackBoardObject.class);
            System.out.println(bbo);
            return bbo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String JSONConverter(BlackBoardObject bbo) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String peticion = objectMapper.writeValueAsString(bbo);
            return peticion;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ConvertirPeticion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
