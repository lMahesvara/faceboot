package interfaces;


import entidades.Comentario;
import entidades.Publicacion;
import entidades.Usuario;

public interface IFachadaConexion {
    public void regitrarUsuario(Usuario usuario);
    public void iniciarSesion(Usuario usuario);
    public void registrarPublicacion(Publicacion publicacion);
    public void consultarPublicaciones();
    public void registrarComentario(Comentario comentario);
}
