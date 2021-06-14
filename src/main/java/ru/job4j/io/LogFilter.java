package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogFilter {
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String str : log) {
                out.printf("%s%n", str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().map(x -> x.split(" "))
                    .filter(x -> Integer.parseInt(x[x.length - 2]) == 404)
                    .map(x -> String.join(" ", x))
                    .forEach(rsl::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}