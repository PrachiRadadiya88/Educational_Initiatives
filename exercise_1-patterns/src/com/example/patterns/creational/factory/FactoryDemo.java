package com.example.patterns.creational.factory;

public class FactoryDemo {
    public static Product create(String type){
        switch(type.toUpperCase()){
            case "A": return new ConcreteA();
            case "B": return new ConcreteB();
            default: throw new IllegalArgumentException("Unknown");
        }
    }
    public static void main(String[] args){
        Product p = create("A");
        System.out.println("Created product: " + p.getName());
    }
}
