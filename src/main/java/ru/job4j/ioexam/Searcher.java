package ru.job4j.ioexam;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Searcher {
    public static void main(String[] args) throws IOException {
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
            Files.walkFileTree(start, new MyFileVisitor(arg.get("n"), arg.get("t")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(arg.get("o"))))) {
            MyFileVisitor.getList().stream().forEach(pw::println);
        }
    }
}