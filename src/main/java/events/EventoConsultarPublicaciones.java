package events;

import java.util.LinkedList;
import java.util.List;
import observers.ObserverConsultarPublicaciones;
import peticiones.AbstractPeticion;
import peticiones.PeticionPublicaciones;

public class EventoConsultarPublicaciones {
    private static EventoConsultarPublicaciones instance;
    private List<ObserverConsultarPublicaciones> observers;

    private EventoConsultarPublicaciones() {
        this.observers = new LinkedList<>();
    }

    public static EventoConsultarPublicaciones getInstance() {
        if (instance == null) {
            instance = new EventoConsultarPublicaciones();
        }
        return instance;
    }

    public void setPeticion(AbstractPeticion peticion) {
        System.out.println("Entro al EVENTO");
        notifyObservers((PeticionPublicaciones) peticion);
    }

    public void notifyObservers(PeticionPublicaciones peticion) {
        observers.forEach(observer -> {
            observer.update(peticion);
        });
    }

    public void addObserver(ObserverConsultarPublicaciones observer) {
        if (observer == null)
            throw new NullPointerException();
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void deleteObserver(ObserverConsultarPublicaciones observer) {
        observers.remove(observer);
    }
}
