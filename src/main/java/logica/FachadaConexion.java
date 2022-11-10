
package logica;

import entidades.Usuario;
import helpers.ConvertirPeticion;
import static helpers.Peticiones.REGISTRAR_USUARIO;
import interfaces.IFachadaConexion;
import peticiones.PeticionUsuario;
import server.Conector;

public class FachadaConexion implements IFachadaConexion{

    @Override
    public void regitrarUsuario(Usuario usuario) {
        Conector conector = new Conector();
        String json = ConvertirPeticion.JSONConverter(new PeticionUsuario(REGISTRAR_USUARIO, usuario));
        conector.iniciar(json);
    }
    
}
