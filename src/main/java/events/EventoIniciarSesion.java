package events;

import java.util.List;
import java.util.LinkedList;
import observers.ObserverIniciarSesion;
import peticiones.AbstractPeticion;
import peticiones.PeticionUsuario;

public class EventoIniciarSesion {

    private static EventoIniciarSesion instance;
    private List<ObserverIniciarSesion> observers;

    /**
     * Constructor
     */
    private EventoIniciarSesion() {
        this.observers = new LinkedList<>();
    }

    /**
     * Obtiene la instancia de la clase
     * @return instancia de la clase
     */
    public static EventoIniciarSesion getInstance() {
        if (instance == null) {
            instance = new EventoIniciarSesion();
        }
        return instance;
    }

    /**
     * Obtiene la peticion
     */
    public void setPeticion(AbstractPeticion peticion) {
        System.out.println("Entro al EVENTO");
        notifyObservers((PeticionUsuario) peticion);
    }

    /**
     * Notifica a los observers
     */
    public void notifyObservers(PeticionUsuario peticion) {
        observers.forEach(observer -> {
            observer.update(peticion);
        });
    }

    /**
     * A;ade un nuevo observer
     */
    public void addObserver(ObserverIniciarSesion observer) {
        if (observer == null)
            throw new NullPointerException();
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }
    
    /**
     * Elimina un observer
     */
    public void deleteObserver(ObserverIniciarSesion observer) {
        observers.remove(observer);
    }

}
