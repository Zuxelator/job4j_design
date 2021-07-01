package ru.job4j.ioexam;

import java.io.*;
import java.nio.file.*;

public class Searcher {

    public static void write(Args arg) {
        try (PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(arg.get("o"))))) {
            MyFileVisitor.getList().stream().forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Args arg = Args.of(args);

        if (args.length != 4) {
            throw new IllegalArgumentException("Должно быть 4 аргумента. "
                    + "-d - директория, в которой начинать поиск.\n"
                    + "-n - имя файла или маска\n"
                    + "-t - тип поиска: mask искать по маске, name по полному совпадение имени\n"
                    + "-o - результат записать в файл.");
        }

        for (String a : args) {
            if (!a.contains("=")) {
                throw new IllegalArgumentException("Ошибка в аргументе");
            }
        }

        Path start = Path.of(arg.get("d"));
        try {
            Files.walkFileTree(start, new MyFileVisitor(arg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Searcher.write(arg);
    }
}