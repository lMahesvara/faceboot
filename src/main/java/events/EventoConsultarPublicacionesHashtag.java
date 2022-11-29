/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package events;

import java.util.LinkedList;
import java.util.List;
import observers.ObserverConsultarPublicaciones;
import observers.ObserverConsultarPublicacionesHashtag;
import peticiones.AbstractPeticion;
import peticiones.PeticionPublicaciones;

/**
 *
 * @author Vastem
 */
public class EventoConsultarPublicacionesHashtag {
    private static EventoConsultarPublicacionesHashtag instance;
    private List<ObserverConsultarPublicacionesHashtag> observers;

    private EventoConsultarPublicacionesHashtag() {
        this.observers = new LinkedList<>();
    }

    public static EventoConsultarPublicacionesHashtag getInstance() {
        if (instance == null) {
            instance = new EventoConsultarPublicacionesHashtag();
        }
        return instance;
    }

    public void setPeticion(AbstractPeticion peticion) {
        System.out.println("Entro al EVENTO");
        notifyObservers((PeticionPublicaciones) peticion);
    }

    public void notifyObservers(PeticionPublicaciones peticion) {
        observers.forEach(observer -> {
            observer.updatePublicacionesTag(peticion);
        });
    }

    public void addObserver(ObserverConsultarPublicacionesHashtag observer) {
        if (observer == null)
            throw new NullPointerException();
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void deleteObserver(ObserverConsultarPublicacionesHashtag observer) {
        observers.remove(observer);
    }
}
