/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package events;

import java.util.LinkedList;
import java.util.List;
import observers.ObserverCerrarSesion;
import observers.ObserverIniciarSesion;
import peticiones.AbstractPeticion;
import peticiones.PeticionUsuario;

/**
 *
 * @author Vastem
 */
public class EventoCerrarSesion {
    private static EventoCerrarSesion instance;
    private List<ObserverCerrarSesion> observers;

    private EventoCerrarSesion() {
        this.observers = new LinkedList<>();
    }

    public static EventoCerrarSesion getInstance() {
        if (instance == null) {
            instance = new EventoCerrarSesion();
        }
        return instance;
    }

    public void setPeticion(AbstractPeticion peticion) {
        System.out.println("Entro al EVENTO");
        notifyObservers((PeticionUsuario) peticion);
    }

    public void notifyObservers(PeticionUsuario peticion) {
        observers.forEach(observer -> {
            observer.updateLogout(peticion);
        });
    }

    public void addObserver(ObserverCerrarSesion observer) {
        if (observer == null)
            throw new NullPointerException();
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void deleteObserver(ObserverCerrarSesion observer) {
        observers.remove(observer);
    }

}
