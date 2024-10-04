package com.example.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.example.exeptions.BlogException;

public class FileUtils {

    public void writeToFile(String path, String contents) {
        try {
            Files.write(Paths.get(path), contents.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new BlogException(e.getMessage(), e);
        }
    }

    public String readFromFile(String path) {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            throw new BlogException(e.getMessage(), e);
        }
    }
}
