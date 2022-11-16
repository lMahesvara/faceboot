package logica;

import entidades.Publicacion;
import entidades.Usuario;
import helpers.ConvertirPeticion;
import helpers.Peticiones;
import static helpers.Peticiones.*;
import interfaces.IFachadaConexion;
import peticiones.PeticionPublicacion;
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
    
    @Override
    public void iniciarSesion(Usuario usuario){
        String json = ConvertirPeticion.JSONConverter(new PeticionUsuario(INICIAR_SESION, usuario));
        conector.enviarPeticion(json);
    }
    
    public void registrarPublicacion(Publicacion publicacion){
        String json = ConvertirPeticion.JSONConverter(new PeticionPublicacion(Peticiones.REGISTRAR_PUBLICACION, publicacion));
        conector.enviarPeticion(json);
    }

}
