package ru.ddc.b2bcolab.upload.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ddc.b2bcolab.upload.utils.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.stream.Stream;

@Service
public class FileStorageService implements StorageService {
    private final Path root = Paths.get("C:\\Users\\svkovalev\\tmp\\upload");

    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file) {
        if (file.isEmpty() || file.getOriginalFilename() == null) {
            throw new RuntimeException("Failed to store empty file.");
        }

        try (InputStream inputStream = file.getInputStream()) {
            Path destinationPath = FileUtils.calculatePath(root).normalize().toAbsolutePath();
            Files.createDirectories(destinationPath);
            Path destinationFile = FileUtils.calculateFilename(destinationPath, file).normalize().toAbsolutePath();
            Files.copy(inputStream, destinationFile);
            System.out.println(destinationFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void delete(String filename) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Stream<Path> loadAll() {
        return Stream.empty();
    }
}
