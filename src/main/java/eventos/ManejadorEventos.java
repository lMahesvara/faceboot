package eventos;

import static helpers.Peticiones.*;
import java.util.Observer;
import peticiones.AbstractPeticion;

public class ManejadorEventos {
    private EventoIniciarSesion evIniciarSesion;
    private EventoRegistrarPublicacion evRegistrarPublicacion;
    private static ManejadorEventos instance;

    private ManejadorEventos() {
        //TODO: Create factory
        evIniciarSesion = new EventoIniciarSesion();
        evRegistrarPublicacion = new EventoRegistrarPublicacion();
    }
    
    public static ManejadorEventos getInstance(){
        if(instance == null) instance = new ManejadorEventos();
        return instance;
    }
    
    public void manejarEvento(AbstractPeticion peticion){
        if(peticion==null) return;
        if(peticion.getPeticionRespuesta().equals(INICIAR_SESION)){
            System.out.println("Entro al manejador");
            evIniciarSesion.setPeticion(peticion);
        }else if(peticion.getPeticionRespuesta().equals(REGISTRAR_PUBLICACION)){
            evRegistrarPublicacion.setPeticion(peticion);
        }
    }
    
    public void suscribirIniciarSesion(Observer observer){
        evIniciarSesion.addObserver(observer);
    }
    
    public void suscribirRegistrarPublicacion(Observer observer){
        evRegistrarPublicacion.addObserver(observer);
    }
    
    
    
}
