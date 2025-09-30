package com.example.patterns.structural.decorator;

public class DecoratorDemo {
    public static void main(String[] args){
        Coffee c = new WithMilkDecorator(new BasicCoffee());
        System.out.println(c.desc() + " costs $" + c.cost());
    }
}
