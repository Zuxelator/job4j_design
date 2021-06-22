package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static List<String> list = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void readPhrases() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.botAnswers))) {
            reader.lines()
                    .forEach(x -> list.add(x));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLog(StringBuilder sb) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            bw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        readPhrases();
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
                String answer = list.get((int) (Math.random() * list.size()));
                sb.append(answer + System.lineSeparator());
                System.out.println(answer);
            }
            userSay = scanner.nextLine();
            sb.append(userSay + System.lineSeparator());
        }
        writeLog(sb);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\encoding\\log.log",
                                    "C:\\projects\\job4j_design\\encoding\\phrases.txt");
        cc.run();
    }
}
