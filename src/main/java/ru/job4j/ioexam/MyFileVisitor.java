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
    private Predicate<Path> predicate;

    public MyFileVisitor(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (predicate.test(file)) {
            array.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    public static List<Path> getList() {
        return array;
    }
}
