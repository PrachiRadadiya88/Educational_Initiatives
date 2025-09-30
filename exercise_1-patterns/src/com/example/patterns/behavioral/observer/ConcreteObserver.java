package com.example.patterns.behavioral.observer;

public class ConcreteObserver implements Observer {
    private final String name;
    public ConcreteObserver(String name){ this.name = name; }
    public void update(String msg){ System.out.println(name + " received: " + msg); }
}
