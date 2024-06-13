package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ddc.b2bcolab.model.Image;
import ru.ddc.b2bcolab.model.ImageType;
import ru.ddc.b2bcolab.repository.ImageRepository;
import ru.ddc.b2bcolab.utils.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final Path root = Paths.get("/upload");

    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Image createImage(Long brandId, ImageType imageType, MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Path destinationPath = FileUtils.calculatePath(root).normalize().toAbsolutePath();
            Files.createDirectories(destinationPath);
            Path destinationFile = FileUtils.calculateFilename(destinationPath, file).normalize().toAbsolutePath();
            Files.copy(inputStream, destinationFile);
            String filename = FileUtils.encodePathToBase64(root.relativize(destinationFile));

            Image image = Image.builder()
                    .brandId(brandId)
                    .imageType(imageType)
                    .filename(filename)
                    .build();

            System.out.println(image);

            return imageRepository.save(image);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Resource getImageById(Long id) {
        Image image = imageRepository.findById(id).orElseThrow();
        try {
            return new UrlResource(root.resolve(FileUtils.decodeBase64ToPath(image.getFilename())).toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }
}
