package interfaces;


import entidades.Publicacion;
import entidades.Usuario;

public interface IFachadaConexion {
    public void regitrarUsuario(Usuario usuario);
    public void iniciarSesion(Usuario usuario);
    public void registrarPublicacion(Publicacion publicacion);
}
