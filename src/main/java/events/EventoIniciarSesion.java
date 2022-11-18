package events;

import java.util.List;
import java.util.LinkedList;
import observers.ObserverIniciarSesion;
import peticiones.AbstractPeticion;
import peticiones.PeticionUsuario;

public class EventoIniciarSesion {

    private static EventoIniciarSesion instance;
    private List<ObserverIniciarSesion> observers;

    private EventoIniciarSesion() {
        this.observers = new LinkedList<>();
    }

    public static EventoIniciarSesion getInstance() {
        if (instance == null) {
            instance = new EventoIniciarSesion();
        }
        return instance;
    }

    public void setPeticion(AbstractPeticion peticion) {
        System.out.println("Entro al EVENTO");
        notifyObservers((PeticionUsuario) peticion);
    }

    public void notifyObservers(PeticionUsuario peticion) {
        observers.forEach(observer -> {
            observer.update(peticion);
        });
    }

    public void addObserver(ObserverIniciarSesion observer) {
        if (observer == null)
            throw new NullPointerException();
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void deleteObserver(ObserverIniciarSesion observer) {
        observers.remove(observer);
    }

}
