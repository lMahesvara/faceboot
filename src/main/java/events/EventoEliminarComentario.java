package events;

import java.util.LinkedList;
import java.util.List;
import observers.ObserverEliminarComentario;
import peticiones.AbstractPeticion;
import peticiones.PeticionComentario;

public class EventoEliminarComentario{
    private static EventoEliminarComentario instance;
    private List<ObserverEliminarComentario> observers;

    private EventoEliminarComentario() {
        this.observers = new LinkedList<>();
    }

    public static EventoEliminarComentario getInstance() {
        if (instance == null) {
            instance = new EventoEliminarComentario();
        }
        return instance;
    }

    public void setPeticion(AbstractPeticion peticion) {
        System.out.println("Entro al EVENTO");
        notifyObservers((PeticionComentario) peticion);
    }

    public void notifyObservers(PeticionComentario peticion) {
        observers.forEach(observer -> {
            observer.updateEliminarComentario(peticion);
        });
    }

    public void addObserver(ObserverEliminarComentario observer) {
        if (observer == null)
            throw new NullPointerException();
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void deleteObserver(ObserverEliminarComentario observer) {
        observers.remove(observer);
    }
}
