
package logica;

import entidades.Usuario;
import helpers.ConvertirPeticion;
import static helpers.Peticiones.REGISTRAR_USUARIO;
import interfaces.IFachadaConexion;
import peticiones.PeticionUsuario;
import server.Conector;

public class FachadaConexion implements IFachadaConexion{

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
    public void suscribir(){
        conector.suscribir();
    }
    
}
