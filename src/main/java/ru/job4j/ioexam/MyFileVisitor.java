package ru.job4j.ioexam;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MyFileVisitor extends SimpleFileVisitor<Path> {
    private static List<Path> array = new ArrayList<>();
    private Args args;

    public MyFileVisitor(Args args) {
        this.args = args;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (getPredicate(args).test(file.toString())) {
            array.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    public static List<Path> getList() {
        return array;
    }

    public static Predicate<String> getPredicate(Args args) {
        Predicate<String> rsl = null;
        if (args.get("t").equals("mask")) {
            rsl = x -> x.endsWith(args.get("n").substring(1));
        } else if (args.get("t").equals("name")) {
            rsl = x -> x.contains(args.get("n"));
        }
        return rsl;
    }
}
