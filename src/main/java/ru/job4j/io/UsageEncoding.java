package ru.job4j.io;

import java.io.*;

public class UsageEncoding {
    public String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.lines().forEach(builder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public void writeDataInFile(String path, String data) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            pw.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
