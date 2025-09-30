package com.example.patterns.structural.decorator;

public class BasicCoffee implements Coffee {
    public double cost() {
        return 2.0;
    }

    public String desc() {
        return "Plain Coffee";
    }
}
