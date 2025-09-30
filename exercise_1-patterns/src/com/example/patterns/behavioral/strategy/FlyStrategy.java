package com.example.patterns.behavioral.strategy;

public class FlyStrategy implements MoveStrategy {
    public String move() { 
        return "Flying using thrusters"; 
    }
}

