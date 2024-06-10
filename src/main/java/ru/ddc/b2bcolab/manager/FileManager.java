package ru.ddc.b2bcolab.manager;

import org.springframework.context.annotation.Configuration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Configuration

public class FileManager {
    public void upload(byte[] resource, String keyName) throws IOException {
        String adFile = "C:\\file.jpg";
        Path path = Paths.get(adFile, keyName);
        Path file = Files.createFile(path);
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file.toString());
            stream.write(resource);
        } finally {
            stream.close();
        }
    }
}