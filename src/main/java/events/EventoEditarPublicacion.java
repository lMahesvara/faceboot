package events;

import java.util.LinkedList;
import java.util.List;
import observers.ObserverEditarPublicacion;
import peticiones.AbstractPeticion;
import peticiones.PeticionPublicacion;

public class EventoEditarPublicacion{
    private static EventoEditarPublicacion instance;
    private List<ObserverEditarPublicacion> observers;

    private EventoEditarPublicacion() {
        this.observers = new LinkedList<>();
    }

    public static EventoEditarPublicacion getInstance() {
        if (instance == null) {
            instance = new EventoEditarPublicacion();
        }
        return instance;
    }

    public void setPeticion(AbstractPeticion peticion) {
        System.out.println("Entro al EVENTO");
        notifyObservers((PeticionPublicacion) peticion);
    }

    public void notifyObservers(PeticionPublicacion peticion) {
        observers.forEach(observer -> {
            observer.updateEditarPublicacion(peticion);
        });
    }

    public void addObserver(ObserverEditarPublicacion observer) {
        if (observer == null)
            throw new NullPointerException();
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void deleteObserver(ObserverEditarPublicacion observer) {
        observers.remove(observer);
    }
}
