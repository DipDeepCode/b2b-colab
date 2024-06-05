package ru.ddc.b2bcolab.upload.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    void init();
    void save(MultipartFile file);
    Resource loadAsResource(String filename);
    void delete(String filename);
    void deleteAll();
    Stream<Path> loadAll();
}
