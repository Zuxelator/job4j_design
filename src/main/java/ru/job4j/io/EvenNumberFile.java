package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder sb = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                sb.append((char) read);
            }
            String[] arr = sb.toString().split(System.lineSeparator());
            for (String s : arr) {
                boolean rsl = Integer.parseInt(s) % 2 == 0;
                System.out.println(s + " " + rsl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
