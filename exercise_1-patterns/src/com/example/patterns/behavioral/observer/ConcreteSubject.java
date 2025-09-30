package com.example.patterns.behavioral.observer;

import java.util.*;
public class ConcreteSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();
    public void register(Observer o){ observers.add(o); }
    public void unregister(Observer o){ observers.remove(o); }
    public void notifyObservers(String message){
        for(Observer o: observers) o.update(message);
    }
    public void changeState(String m){ notifyObservers(m); }
}
