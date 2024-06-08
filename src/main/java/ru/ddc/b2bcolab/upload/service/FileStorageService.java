package ru.ddc.b2bcolab.upload.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ddc.b2bcolab.upload.utils.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;

@Service
public class FileStorageService implements StorageService {
    private final Path root = Paths.get("/upload");

    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String store(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Path destinationPath = FileUtils.calculatePath(root).normalize().toAbsolutePath();
            Files.createDirectories(destinationPath);
            Path destinationFile = FileUtils.calculateFilename(destinationPath, file).normalize().toAbsolutePath();
            Files.copy(inputStream, destinationFile);
            return FileUtils.encodePathToBase64(root.relativize(destinationFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resource load(String base64path) {
        try {
            return new UrlResource(root.resolve(FileUtils.decodeBase64ToPath(base64path)).toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
