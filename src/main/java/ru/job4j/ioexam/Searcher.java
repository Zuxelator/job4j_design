package ru.job4j.ioexam;

import java.io.*;
import java.nio.file.*;
import java.util.function.Predicate;

public class Searcher {

    public static void write(Args arg) {
        try (PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(arg.get("o"))))) {
            MyFileVisitor.getList().stream().forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Predicate<Path> getPredicate(Args args) {
        Predicate<Path> rsl = null;
        if (args.get("t").equals("mask")) {
            rsl = x -> x.toString().endsWith(args.get("n").substring(1));
        } else if (args.get("t").equals("name")) {
            rsl = x -> x.toString().contains(args.get("n"));
        }
        return rsl;
    }

    public static void search(Args args) {
        Path start = Path.of(args.get("d"));
        try {
            Files.walkFileTree(start, new MyFileVisitor(getPredicate(args)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Searcher.write(args);
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
        search(arg);
    }
}