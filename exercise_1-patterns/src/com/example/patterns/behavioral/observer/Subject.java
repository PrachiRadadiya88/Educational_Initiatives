package com.example.patterns.behavioral.observer;

public interface Subject {
    void register(Observer o);
    void unregister(Observer o);
    void notifyObservers(String message);
}
interface Observer { void update(String msg); }
