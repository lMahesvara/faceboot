/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package events;

import java.util.LinkedList;
import java.util.List;
import observers.ObserverIniciarSesion;
import observers.ObserverIniciarSesionFb;
import peticiones.AbstractPeticion;
import peticiones.PeticionUsuario;

/**
 *
 * @author germa
 */
public class EventoIniciarSesionFb {
    private static EventoIniciarSesionFb instance;
    private List<ObserverIniciarSesionFb> observers;

    private EventoIniciarSesionFb (){
        this.observers = new LinkedList<>();
    }

    public static EventoIniciarSesionFb getInstance() {
        if (instance == null) {
            instance = new EventoIniciarSesionFb();
        }
        return instance;
    }

    public void setPeticion(AbstractPeticion peticion) {
        System.out.println("Entro al EVENTO");
        notifyObservers((PeticionUsuario) peticion);
    }

    public void notifyObservers(PeticionUsuario peticion) {
        observers.forEach(observer -> {
            observer.updateIniciarSesionFb(peticion);
        });
    }

    public void addObserver(ObserverIniciarSesionFb observer) {
        if (observer == null)
            throw new NullPointerException();
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void deleteObserver(ObserverIniciarSesionFb observer) {
        observers.remove(observer);
    }

}
