package edu.school21.models;

import java.util.StringJoiner;

public class Car {

    private String model;
    private Integer currentFuelLevel;

    public Car() {
        this.model = "def. model";
        this.currentFuelLevel = 0;
    }

    public Car(String model, Integer currentFuelLevel) {
        this.model = model;
        this.currentFuelLevel = currentFuelLevel;
    }

    public int refuelCar(int value) {
        System.out.println("refuelCar worked");
     return currentFuelLevel += value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("model= " + model + "")
                .add("height= " + currentFuelLevel + "")
                .toString();
    }
}
