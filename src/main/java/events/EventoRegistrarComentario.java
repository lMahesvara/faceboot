package events;

import java.util.LinkedList;
import java.util.List;
import observers.ObserverRegistrarComentario;
import peticiones.AbstractPeticion;
import peticiones.PeticionComentario;

public class EventoRegistrarComentario {
    private static EventoRegistrarComentario instance;
    private List<ObserverRegistrarComentario> observers;

    private EventoRegistrarComentario() {
        this.observers = new LinkedList<>();
    }

    public static EventoRegistrarComentario getInstance() {
        if (instance == null) {
            instance = new EventoRegistrarComentario();
        }
        return instance;
    }

    public void setPeticion(AbstractPeticion peticion) {
        System.out.println("Entro al EVENTO");
        notifyObservers((PeticionComentario) peticion);
    }

    public void notifyObservers(PeticionComentario peticion) {
        observers.forEach(observer -> {
            observer.update(peticion);
        });
    }

    public void addObserver(ObserverRegistrarComentario observer) {
        if (observer == null)
            throw new NullPointerException();
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void deleteObserver(ObserverRegistrarComentario observer) {
        observers.remove(observer);
    }
}
