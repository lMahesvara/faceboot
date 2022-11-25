package events;

import static helpers.Peticiones.*;
import peticiones.AbstractPeticion;

public class EventHandler {
    private EventoIniciarSesion evIniciarSesion;
    private EventoRegistrarPublicacion evRegistrarPublicacion;
    private EventoConsultarPublicaciones evConsultarPublicaciones;
    private EventoRegistrarComentario evRegistrarComentario;
    private EventoActualizarUsuario evActualizarUsuario;
    private EventoIniciarSesionFb evIniciarSesionFb;
    private static EventHandler instance;

    private EventHandler() {
        //TODO: Create factory
        evIniciarSesion = EventoIniciarSesion.getInstance();
        evRegistrarPublicacion = EventoRegistrarPublicacion.getInstance();
        evConsultarPublicaciones = EventoConsultarPublicaciones.getInstance();
        evRegistrarComentario = EventoRegistrarComentario.getInstance();
        evActualizarUsuario = EventoActualizarUsuario.getInstance();
        evIniciarSesionFb = EventoIniciarSesionFb.getInstance();
    }
    
    public static EventHandler getInstance(){
        if(instance == null) instance = new EventHandler();
        return instance;
    }
    
    public void manejarEvento(AbstractPeticion peticion){
        if(peticion==null) return;
        if(peticion.getPeticionRespuesta().equals(INICIAR_SESION)){
            System.out.println("Entro al manejador");
            evIniciarSesion.setPeticion(peticion);
        }else if(peticion.getPeticionRespuesta().equals(REGISTRAR_PUBLICACION)){
            evRegistrarPublicacion.setPeticion(peticion);
        }else if(peticion.getPeticionRespuesta().equals(CONSULTAR_PUBLICACIONES)){
            evConsultarPublicaciones.setPeticion(peticion);
        }else if(peticion.getPeticionRespuesta().equals(AGREGAR_COMENTARIO)){
            evRegistrarComentario.setPeticion(peticion);
        }else if(peticion.getPeticionRespuesta().equals(ACTUALIZAR_USUARIO)){
            evActualizarUsuario.setPeticion(peticion);
        }else if(peticion.getPeticionRespuesta().equals(INICIAR_SESION_FB)){
            evIniciarSesionFb.setPeticion(peticion);
        }
    }    
}
