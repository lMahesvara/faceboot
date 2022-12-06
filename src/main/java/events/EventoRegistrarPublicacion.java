package events;

import java.util.LinkedList;
import java.util.List;
import observers.ObserverRegistrarPublicacion;
import peticiones.AbstractPeticion;
import peticiones.PeticionPublicacion;

public class EventoRegistrarPublicacion{
    private static EventoRegistrarPublicacion instance;
    private List<ObserverRegistrarPublicacion> observers;

    /**
     * Constructor
     */
    private EventoRegistrarPublicacion() {
        this.observers = new LinkedList<>();
    }

    /**
     * Obtiene la instancia de la clase
     * @return instancia de la clase
     */
    public static EventoRegistrarPublicacion getInstance() {
        if (instance == null) {
            instance = new EventoRegistrarPublicacion();
        }
        return instance;
    }

    /**
     * Obtiene la peticion
     */
    public void setPeticion(AbstractPeticion peticion) {
        System.out.println("Entro al EVENTO");
        notifyObservers((PeticionPublicacion) peticion);
    }

    /**
     * Notifica a los observers
     */
    public void notifyObservers(PeticionPublicacion peticion) {
        observers.forEach(observer -> {
            observer.update(peticion);
        });
    }

     /**
     * A;ade un nuevo observer
     */
    public void addObserver(ObserverRegistrarPublicacion observer) {
        if (observer == null)
            throw new NullPointerException();
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Elimina un observer
     */
    public void deleteObserver(ObserverRegistrarPublicacion observer) {
        observers.remove(observer);
    }
}
