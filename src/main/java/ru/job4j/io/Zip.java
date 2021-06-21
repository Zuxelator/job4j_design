package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(target))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Path> search(String[] args) {
        ArgsName arg = ArgsName.of(args);
        String root = arg.get("d");
        SearchFiles sf = new SearchFiles(x -> !x.toString().endsWith(arg.get("e")));
        try {
            Files.walkFileTree(Paths.get(root), sf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sf.getPaths();
    }

    public static void main(String[] args) {
        ArgsName arg = ArgsName.of(args);
        new Zip().packFiles(search(args), new File(arg.get("o")));
    }
}