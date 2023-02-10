package com.cloud.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class PathExamples {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("server-files", "images", "routes.png");
        Path absolutePath = path.toAbsolutePath();
//        System.out.println(absolutePath);

        Path newDir = Path.of("server-files", "texts");

        if (!Files.exists(newDir)) {
            Files.createDirectory(newDir);
        }

        Path textFile = newDir.resolve("sample.txt");
//        Files.createFile(textFile);
//        Files.writeString(textFile, "Hello world");
        Files.writeString(textFile, "Hello world\n", StandardOpenOption.APPEND);

    }


}
