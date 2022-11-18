package events;

import java.util.LinkedList;
import java.util.List;
import observers.ObserverRegistrarPublicacion;
import peticiones.AbstractPeticion;
import peticiones.PeticionPublicacion;

public class EventoRegistrarPublicacion{
    private static EventoRegistrarPublicacion instance;
    private List<ObserverRegistrarPublicacion> observers;

    private EventoRegistrarPublicacion() {
        this.observers = new LinkedList<>();
    }

    public static EventoRegistrarPublicacion getInstance() {
        if (instance == null) {
            instance = new EventoRegistrarPublicacion();
        }
        return instance;
    }

    public void setPeticion(AbstractPeticion peticion) {
        System.out.println("Entro al EVENTO");
        notifyObservers((PeticionPublicacion) peticion);
    }

    public void notifyObservers(PeticionPublicacion peticion) {
        observers.forEach(observer -> {
            observer.update(peticion);
        });
    }

    public void addObserver(ObserverRegistrarPublicacion observer) {
        if (observer == null)
            throw new NullPointerException();
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void deleteObserver(ObserverRegistrarPublicacion observer) {
        observers.remove(observer);
    }
}
