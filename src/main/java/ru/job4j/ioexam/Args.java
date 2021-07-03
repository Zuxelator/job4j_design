package ru.job4j.ioexam;

import java.util.HashMap;
import java.util.Map;

public class Args {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String rsl = values.get(key);
        if (rsl == null) {
            throw new IllegalArgumentException();
        }
        return rsl;
    }

    private void parse(String[] args) {
        for (String str : args) {
            String[] arr = str.substring(1).split("=");
            if (arr.length != 2) {
                throw new IllegalArgumentException();
            }
            values.put(arr[0], arr[1]);
        }
    }

    public static Args of(String[] args) {
        Args names = new Args();
        names.parse(args);
        return names;
    }
}
