
package logica;

import entidades.BlackBoardObject;
import entidades.Usuario;
import static helpers.Peticiones.REGISTRAR_USUARIO;
import interfaces.IFachadaConexion;
import server.Conector;
import utils.ConvertirPeticion;

public class FachadaConexion implements IFachadaConexion{

    @Override
    public void regitrarUsuario(Usuario usuario) {
        Conector conector = new Conector();
        String json = ConvertirPeticion.JSONConverter(new BlackBoardObject(REGISTRAR_USUARIO, usuario));
        conector.iniciar(json);
    }
    
}
