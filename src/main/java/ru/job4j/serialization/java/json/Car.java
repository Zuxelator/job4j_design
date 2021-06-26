package ru.job4j.serialization.java.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Car {
    private boolean isRacing;
    private int seats;
    private String model;
    private Engine engine;
    private int[] nums;

    public Car(boolean isRacing, int seats, String model, Engine engine, int[] nums) {
        this.isRacing = isRacing;
        this.seats = seats;
        this.model = model;
        this.engine = engine;
        this.nums = nums;
    }

    public boolean isRacing() {
        return isRacing;
    }

    public int getSeats() {
        return seats;
    }

    public String getModel() {
        return model;
    }

    public Engine getEngine() {
        return engine;
    }

    public int[] getNums() {
        return nums;
    }

    @Override
    public String toString() {
        return "Car{"
                + "isRacing="
                + isRacing
                + ", seats="
                + seats
                + ", model='"
                + model
                + '\''
                + ", engine="
                + engine
                + ", nums="
                + Arrays.toString(nums)
                + '}';
    }

    public static void main(String[] args) {
        final Car car = new Car(true, 2, "911", new Engine(475, "3996"), new int[] {2, 3});
        final Gson gson = new GsonBuilder().create();
        String str = gson.toJson(car);
        System.out.println(str);
        Car fromJSONtoCar = gson.fromJson(str, Car.class);
        System.out.println(fromJSONtoCar);

    }
}
