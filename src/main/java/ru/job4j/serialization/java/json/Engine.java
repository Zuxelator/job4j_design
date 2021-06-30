package ru.job4j.serialization.java.json;

public class Engine {
    private int power;
    private String volume;

    public Engine(int power, String volume) {
        this.power = power;
        this.volume = volume;
    }

    public int getPower() {
        return power;
    }

    public String getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "power="
                + power
                + ", volume='"
                + volume
                + '\''
                + '}';
    }
}
