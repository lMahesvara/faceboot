package logica;

import entidades.Comentario;
import entidades.Hashtag;
import entidades.Notificacion;
import entidades.Publicacion;
import entidades.Usuario;
import helpers.ConvertirPeticion;
import static helpers.Peticiones.*;
import interfaces.IFachadaConexion;
import peticiones.PeticionComentario;
import peticiones.PeticionNotificacion;
import peticiones.PeticionNotificaciones;
import peticiones.PeticionPublicacion;
import peticiones.PeticionPublicaciones;
import peticiones.PeticionUsuario;
import server.Conector;

public class FachadaConexion implements IFachadaConexion {

    private Conector conector;

    public FachadaConexion() {
        conector = Conector.getInstance();
    }

    @Override
    public void regitrarUsuario(Usuario usuario) {
        String json = ConvertirPeticion.JSONConverter(new PeticionUsuario(REGISTRAR_USUARIO, usuario));
        conector.enviarPeticion(json);
    }
    
    public void actualizarUsuario(Usuario usuario){
        String json = ConvertirPeticion.JSONConverter(new PeticionUsuario(ACTUALIZAR_USUARIO, usuario));
        conector.enviarPeticion(json);
    }
    
    @Override
    public void iniciarSesion(Usuario usuario){
        String json = ConvertirPeticion.JSONConverter(new PeticionUsuario(INICIAR_SESION, usuario));
        conector.enviarPeticion(json);
    }
    
    public void registrarPublicacion(Publicacion publicacion){
        String json = ConvertirPeticion.JSONConverter(new PeticionPublicacion(REGISTRAR_PUBLICACION, publicacion));
        conector.enviarPeticion(json);
    }
    
    public void consultarPublicaciones(){
        String json = ConvertirPeticion.JSONConverter(new PeticionPublicaciones(CONSULTAR_PUBLICACIONES));
        conector.enviarPeticion(json);
    }
    
    @Override
    public void registrarComentario(Comentario comentario){
        String json = ConvertirPeticion.JSONConverter(new PeticionComentario(AGREGAR_COMENTARIO, comentario));
        conector.enviarPeticion(json);
    }

     @Override
    public void iniciarSesionFB(Usuario usuario){
        String json = ConvertirPeticion.JSONConverter(new PeticionUsuario(INICIAR_SESION_FB, usuario));
        conector.enviarPeticion(json);
    }

    @Override
    public void eliminarPublicacion(Publicacion publicacion) {
        String json = ConvertirPeticion.JSONConverter(new PeticionPublicacion(ELIMINAR_PUBLICACION, publicacion));
        conector.enviarPeticion(json);
    }

    @Override
    public void editarPublicacion(Publicacion publicacion) {
        String json = ConvertirPeticion.JSONConverter(new PeticionPublicacion(ACTUALIZAR_PUBLICACION, publicacion));
        conector.enviarPeticion(json);
    }
    
    @Override
    public void notificarCorreo(Notificacion notificacion){
        String json = ConvertirPeticion.JSONConverter(new PeticionNotificacion(NOTIFICACION_CORREO, notificacion));
        conector.enviarPeticion(json);
    }
    
    @Override
    public void notificarSMS(Notificacion notificacion){
        String json = ConvertirPeticion.JSONConverter(new PeticionNotificacion(NOTIFICACION_SMS, notificacion));
        conector.enviarPeticion(json);
    }
    
    @Override
    public void notificarTodos(Notificacion notificacion){
        String json = ConvertirPeticion.JSONConverter(new PeticionNotificacion(NOTIFICACION_TODOS, notificacion));
        conector.enviarPeticion(json);
    }
    
    @Override
    public void consultarNotificaciones(Usuario usuario){
        String json = ConvertirPeticion.JSONConverter(new PeticionNotificaciones(CONSULTAR_NOTIFICACIONES, usuario));
        conector.enviarPeticion(json);
    }
    
    @Override
    public void consultarPublicacionesHashtag(Hashtag hashtag){
        String json = ConvertirPeticion.JSONConverter(new PeticionPublicaciones(CONSULTAR_PUBLICACIONES_HASHTAG, hashtag));
        conector.enviarPeticion(json);
    }

    @Override
    public void eliminarComentario(Comentario comentario) {
        String json = ConvertirPeticion.JSONConverter(new PeticionComentario(ELIMINAR_COMENTARIO, comentario));
        conector.enviarPeticion(json);
    }
}
