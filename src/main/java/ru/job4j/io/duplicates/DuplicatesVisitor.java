package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, Integer> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(new File(String.valueOf(file)).length(), file.getFileName().toString());
        Integer value = map.get(fileProperty) == null ? 1 : map.get(fileProperty) + 1;
        map.put(fileProperty, value);
        return super.visitFile(file, attrs);
    }

    public List<FileProperty> getDuplicates() {
        List<FileProperty> rsl = new ArrayList<>();
        for (Map.Entry<FileProperty, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 1) {
                rsl.add(entry.getKey());
            }
        }
        return rsl;
    }
}