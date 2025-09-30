package com.example.patterns.structural.decorator;

public class WithMilkDecorator implements Coffee {
    private final Coffee inner;

    public WithMilkDecorator(Coffee c) {
        this.inner = c;
    }

    public double cost() {
        return inner.cost() + 0.5;
    }

    public String desc() {
        return inner.desc() + ", +Milk";
    }
}