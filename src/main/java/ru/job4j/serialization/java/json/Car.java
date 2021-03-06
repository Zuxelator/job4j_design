package ru.job4j.serialization.java.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public boolean getIsRacing() {
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

        JSONObject jsonObject = new JSONObject();
        JSONObject engine = new JSONObject();
        engine.put("power", 475);
        engine.put("volume", "3996");
        JSONArray jsonArray = new JSONArray(Arrays.asList(2, 3));
        
        jsonObject.put("isRacing", car.getIsRacing());
        jsonObject.put("seats", car.getSeats());
        jsonObject.put("model", car.getModel());
        jsonObject.put("engine", engine);
        jsonObject.put("nums", jsonArray);

        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(car).toString());
    }
}
