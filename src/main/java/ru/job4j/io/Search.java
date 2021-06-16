package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Should be 2 args. args: " + args.length);
        }
        if (!Files.exists(Path.of(args[0]))) {
            throw new IllegalArgumentException("No such directory. Check args");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("args[1] should starts with \".\" Check args");
        }
        Path start = Path.of(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}