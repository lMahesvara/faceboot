package eventos;

import java.util.Observable;
import peticiones.AbstractPeticion;

public class EventoIniciarSesion extends Observable{
    private AbstractPeticion peticion;
    
    public void setPeticion(AbstractPeticion peticion){
        System.out.println("Entro al EVENTO");
        this.peticion = peticion;
        setChanged();
        notifyObservers(peticion);
    }
    
    
}
