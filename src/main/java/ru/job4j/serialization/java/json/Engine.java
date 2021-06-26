package ru.job4j.serialization.java.json;

public class Engine {
    private int power;
    private String volume;

    public Engine(int power, String type) {
        this.power = power;
        this.volume = type;
    }

    public int getPower() {
        return power;
    }

    public String getType() {
        return volume;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "power="
                + power
                + ", type='"
                + volume
                + '\''
                + '}';
    }
}
