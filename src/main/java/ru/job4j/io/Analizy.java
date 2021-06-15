package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(source));
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
                        sb.append(start + ";" + finish + System.lineSeparator());
                        start = null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            printWriter.write(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "target.log");
    }
}