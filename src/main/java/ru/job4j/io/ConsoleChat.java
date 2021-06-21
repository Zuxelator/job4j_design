package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static Map<Integer, String> map = new HashMap<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.botAnswers))) {
            reader.lines()
                    .map(x -> x.split("="))
                    .forEach(x -> map.put(Integer.parseInt(x[0]), x[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        String userSay = scanner.nextLine();
        boolean nonStop = true;
        StringBuilder sb = new StringBuilder();
        sb.append(userSay + System.lineSeparator());
        while (!userSay.equals(OUT)) {
            if (userSay.equals(STOP)) {
                nonStop = false;
            }
            if (userSay.equals(CONTINUE)) {
                nonStop = true;
            }
            if (nonStop) {
                String answer = map.get((int) (Math.random() * map.size()));
                sb.append(answer + System.lineSeparator());
                System.out.println(answer);
            }
            userSay = scanner.nextLine();
            sb.append(userSay + System.lineSeparator());
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            bw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\encoding\\log.log",
                                    "C:\\projects\\job4j_design\\encoding\\phrases.txt");
        cc.run();
    }
}
