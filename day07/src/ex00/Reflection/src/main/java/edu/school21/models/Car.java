package edu.school21.models;

import java.util.StringJoiner;

public class Car {

    private final String model;
    private Integer currentFuelLevel;

    public Car() {
        this.model = "def. model";
        this.currentFuelLevel = 0;
    }

    public Car(String model, Integer currentFuelLevel) {
        this.model = model;
        this.currentFuelLevel = currentFuelLevel;
    }

    public Integer refuelCar(Integer value) {
     return currentFuelLevel += value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("model= '" + model + "'")
                .add("currentFuelLevel= " + currentFuelLevel + "")
                .toString();
    }
}
