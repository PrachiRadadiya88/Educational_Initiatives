package com.example.patterns.behavioral.strategy;

public class WalkStrategy implements MoveStrategy {
    public String move() { 
        return "Walking on surface"; 
    }
}
