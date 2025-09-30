package com.example.patterns.structural.adapter;

public class SensorAdapter implements TemperatureSensor {
    private final LegacyTemperatureSensor legacy;

    public SensorAdapter(LegacyTemperatureSensor s) {
        this.legacy = s;
    }

    public double getCelsius() {
        return (legacy.getFahrenheit() - 32) * 5 / 9;
    }
}
