/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package events;

import java.util.LinkedList;
import java.util.List;
import observers.ObserverActualizarUsuario;
import observers.ObserverIniciarSesion;
import peticiones.AbstractPeticion;
import peticiones.PeticionUsuario;

/**
 *
 * @author Vastem
 */
public class EventoActualizarUsuario {
    private static EventoActualizarUsuario instance;
    private List<ObserverActualizarUsuario> observers;

    public EventoActualizarUsuario() {
        this.observers = new LinkedList<>();
    }
    
    public static EventoActualizarUsuario getInstance() {
        if (instance == null) {
            instance = new EventoActualizarUsuario();
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

    public void addObserver(ObserverActualizarUsuario observer) {
        if (observer == null)
            throw new NullPointerException();
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void deleteObserver(ObserverActualizarUsuario observer) {
        observers.remove(observer);
    }
    
}
