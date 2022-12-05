package events;

import java.util.LinkedList;
import java.util.List;
import observers.ObserverRegistrarComentario;
import peticiones.AbstractPeticion;
import peticiones.PeticionComentario;

public class EventoRegistrarComentario {
    private static EventoRegistrarComentario instance;
    private List<ObserverRegistrarComentario> observers;

    /**
     * Constructor
     */
    private EventoRegistrarComentario() {
        this.observers = new LinkedList<>();
    }

    /**
     * Obtiene la instancia de la clase
     * @return instancia de la clase
     */
    public static EventoRegistrarComentario getInstance() {
        if (instance == null) {
            instance = new EventoRegistrarComentario();
        }
        return instance;
    }

    /**
     * Obtiene la peticion
     */
    public void setPeticion(AbstractPeticion peticion) {
        System.out.println("Entro al EVENTO");
        notifyObservers((PeticionComentario) peticion);
    }
    
    /**
     * Notifica a los observers
     */
    public void notifyObservers(PeticionComentario peticion) {
        observers.forEach(observer -> {
            observer.update(peticion);
        });
    }
    
    /**
     * A;ade un nuevo observer
     */
    public void addObserver(ObserverRegistrarComentario observer) {
        if (observer == null)
            throw new NullPointerException();
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Elimina un observer
     */
    public void deleteObserver(ObserverRegistrarComentario observer) {
        observers.remove(observer);
    }
}
