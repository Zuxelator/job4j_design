package ru.job4j.inputotput;

import java.io.FileOutputStream;

public class MultipleTable {
    public static void main(String[] args) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("table.txt")) {
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    fileOutputStream.write((i * j + " ").getBytes());
                }
                fileOutputStream.write("\n".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}