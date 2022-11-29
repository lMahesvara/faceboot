package interfaces;


import entidades.Comentario;
import entidades.Hashtag;
import entidades.Notificacion;
import entidades.Publicacion;
import entidades.Usuario;

public interface IFachadaConexion {
    public void regitrarUsuario(Usuario usuario);
    public void actualizarUsuario(Usuario usuario);
    public void iniciarSesion(Usuario usuario);
    public void registrarPublicacion(Publicacion publicacion);
    public void consultarPublicaciones();
    public void registrarComentario(Comentario comentario);
    public void iniciarSesionFB(Usuario usuario);
    public void notificarCorreo(Notificacion notificacion);
    public void notificarSMS(Notificacion notificacion);
    public void notificarTodos(Notificacion notificacion);
    public void consultarNotificaciones(Usuario usuario);
    public void consultarPublicacionesHashtag(Hashtag hashtag);
}
