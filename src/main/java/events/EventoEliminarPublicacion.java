package events;

import java.util.LinkedList;
import java.util.List;
import observers.ObserverEliminarPublicacion;
import peticiones.AbstractPeticion;
import peticiones.PeticionPublicacion;

public class EventoEliminarPublicacion{
    private static EventoEliminarPublicacion instance;
    private List<ObserverEliminarPublicacion> observers;

    private EventoEliminarPublicacion() {
        this.observers = new LinkedList<>();
    }

    public static EventoEliminarPublicacion getInstance() {
        if (instance == null) {
            instance = new EventoEliminarPublicacion();
        }
        return instance;
    }

    public void setPeticion(AbstractPeticion peticion) {
        System.out.println("Entro al EVENTO");
        notifyObservers((PeticionPublicacion) peticion);
    }

    public void notifyObservers(PeticionPublicacion peticion) {
        observers.forEach(observer -> {
            observer.updateEliminarPublicacion(peticion);
        });
    }

    public void addObserver(ObserverEliminarPublicacion observer) {
        if (observer == null)
            throw new NullPointerException();
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void deleteObserver(ObserverEliminarPublicacion observer) {
        observers.remove(observer);
    }
}
