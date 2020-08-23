package com.yingglish.io.listfiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListFiles {
    public static final int DEPTH = 1;

    public Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                // 使用map方法获取 数据中的name
                .map(File::getName)
                // 使用collect方法将处理好的stream对象转换为set对象
                .collect(Collectors.toSet());
    }

    public Set<String> listFilesUsingFileWalk(String dir, int depth) throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get(dir), depth)) {
            return stream.filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }

    public Set<String> listFilesUsingFileWalkAndVisitor(String dir) throws IOException {
        Set<String> fileList = new HashSet<>();
        Files.walkFileTree(Paths.get(dir), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (!Files.isDirectory(file)) {
                    fileList.add(file.getFileName()
                            .toString());
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return fileList;
    }

    public Set<String> listFilesUsingDirectoryStream(String dir) throws IOException {
        Set<String> fileList = new HashSet<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
            for (Path path : stream) {
                if (!Files.isDirectory(path)) {
                    fileList.add(path.getFileName()
                            .toString());
                }
            }
        }
        return fileList;
    }

    public static void main(String[] args) throws IOException {
        ListFiles listFiles = new ListFiles();

        System.out.println(listFiles.listFilesUsingJavaIO("./"));
        System.out.println(listFiles.listFilesUsingDirectoryStream("./"));
    }
}
