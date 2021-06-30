package ru.job4j.ioexam;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class MyFileVisitor extends SimpleFileVisitor<Path> {
    private String mask;
    private String typeOfSearch;
    private static List<Path> array = new ArrayList<>();

    public MyFileVisitor(String mask, String typeOfSearch) {
        this.mask = mask;
        this.typeOfSearch = typeOfSearch;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (typeOfSearch.equals("mask")) {
            if (file.toString().endsWith(mask.substring(1))) {
                array.add(file);
            }
        } else if (typeOfSearch.equals("name")) {
            if (file.getFileName().toString().equals(mask)) {
                array.add(file);
            }
        }
        return FileVisitResult.CONTINUE;
    }

    public static List<Path> getList() {
        return array;
    }
}
