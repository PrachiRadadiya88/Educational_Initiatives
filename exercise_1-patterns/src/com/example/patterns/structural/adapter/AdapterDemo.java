package com.example.patterns.structural.adapter;

public class AdapterDemo {
    public static void main(String[] args){
        LegacyTemperatureSensor legacy = new LegacyTemperatureSensor();
        TemperatureSensor sensor = new SensorAdapter(legacy);
        System.out.printf("Temperature: %.1f°C\n", sensor.getCelsius());
    }
}
