package com.example.patterns.behavioral.observer;

public class ObserverDemo {
    public static void main(String[] args){
        ConcreteSubject subject = new ConcreteSubject();
        subject.register(new ConcreteObserver("ControlRoom"));
        subject.register(new ConcreteObserver("Telemetry"));
        subject.changeState("ALERT: temperature spike");
    }
}
