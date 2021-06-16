package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> log = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source))) {
            String line;
            String start = null;
            String finish;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split(" ");
                if (start == null) {
                    if (Integer.parseInt(arr[0]) == 400 || Integer.parseInt(arr[0]) == 500) {
                        start = arr[1];
                    }
                } else {
                    if (Integer.parseInt(arr[0]) != 400 && Integer.parseInt(arr[0]) != 500) {
                        finish = arr[1];
                        log.add(start + ";" + finish + System.lineSeparator());
                        start = null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        print(target, log);
    }

    public void print(String target, List<String> log) {
        try (PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (String s : log) {
                printWriter.write(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "target.log");
    }
}