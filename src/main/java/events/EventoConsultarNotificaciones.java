/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package events;

import java.util.LinkedList;
import java.util.List;
import observers.ObserverConsultarNotificaciones;
import observers.ObserverConsultarPublicaciones;
import peticiones.AbstractPeticion;
import peticiones.PeticionNotificaciones;
import peticiones.PeticionPublicaciones;

/**
 *
 * @author Vastem
 */
public class EventoConsultarNotificaciones {
    private static EventoConsultarNotificaciones instance;
    private List<ObserverConsultarNotificaciones> observers;

    EventoConsultarNotificaciones() {
        this.observers = new LinkedList<>();
    }

    public static EventoConsultarNotificaciones getInstance() {
        if (instance == null) {
            instance = new EventoConsultarNotificaciones();
        }
        return instance;
    }

    public void setPeticion(AbstractPeticion peticion) {
        System.out.println("Entro al EVENTO");
        notifyObservers((PeticionNotificaciones) peticion);
    }

    public void notifyObservers(PeticionNotificaciones peticion) {
        observers.forEach(observer -> {
            observer.update(peticion);
        });
    }

    public void addObserver(ObserverConsultarNotificaciones observer) {
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
