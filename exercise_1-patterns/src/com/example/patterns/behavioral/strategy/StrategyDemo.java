package com.example.patterns.behavioral.strategy;

public class StrategyDemo {
    static class Rover {
        private MoveStrategy strategy;
        Rover(MoveStrategy s){ this.strategy = s; }
        void setStrategy(MoveStrategy s){ this.strategy = s; }
        void performMove(){ System.out.println(strategy.move()); }
    }

    public static void main(String[] args) {
        Rover rover = new Rover(new WalkStrategy());
        System.out.print("Initial: "); rover.performMove();
        rover.setStrategy(new FlyStrategy());
        System.out.print("After upgrade: "); rover.performMove();
    }
}
