/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package events;

import java.util.LinkedList;
import java.util.List;
import observers.ObserverRegistrarNotificacion;
import peticiones.AbstractPeticion;
import peticiones.PeticionNotificacion;

/**
 *
 * @author Vastem
 */
public class EventoRegistrarNotificacion {
    private static EventoRegistrarNotificacion instance;
    private List<ObserverRegistrarNotificacion> observers;

    private EventoRegistrarNotificacion() {
        this.observers = new LinkedList<>();
    }

    public static EventoRegistrarNotificacion getInstance() {
        if (instance == null) {
            instance = new EventoRegistrarNotificacion();
        }
        return instance;
    }

    public void setPeticion(AbstractPeticion peticion) {
        System.out.println("Entro al EVENTO");
        notifyObservers((PeticionNotificacion) peticion);
    }

    public void notifyObservers(PeticionNotificacion peticion) {
        observers.forEach(observer -> {
            observer.update(peticion);
        });
    }

    public void addObserver(ObserverRegistrarNotificacion observer) {
        if (observer == null)
            throw new NullPointerException();
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void deleteObserver(ObserverRegistrarNotificacion observer) {
        observers.remove(observer);
    }
}
