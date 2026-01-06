package models;

import java.io.Serializable;

public class Baggage implements Serializable {
    private static final long serialVersionUID = 1L;

    private double weight;

    public Baggage(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Baggage: " + weight + " kg";
    }
}
