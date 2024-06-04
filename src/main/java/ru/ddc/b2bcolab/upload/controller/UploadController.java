package ru.ddc.b2bcolab.upload.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.imaging.ImageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ddc.b2bcolab.upload.service.StorageService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {
    private final StorageService storageService;

    @PostMapping()
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
//            InputStream fileInputStream = file.getInputStream();
//            String md5Hex = DigestUtils.md5Hex(fileInputStream);
//            System.out.println(md5Hex);
//
//            String originalFilename = file.getOriginalFilename();
//            System.out.println(originalFilename);
//
//            String substring = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
//            System.out.println(substring);
//
//            String newFilename = md5Hex + "." +substring;
//            System.out.println(newFilename);
//
//            File newFile = new File("D:\\upload\\"+newFilename);
//            file.transferTo(newFile);

//            Files.write(Paths.get(newFilename), file.getBytes());

        byte[] imageBytes;
        try {
            imageBytes = file.getBytes();
        } catch (IOException ex) {
            throw new IllegalStateException("Unable to retrieve image data from upload file.", ex);
        }

        final ImageInfo imageInfo = MetadataExtractor.loadImageInfo(imageBytes);

        storageService.save(file);
        return ResponseEntity.ok().build();
    }
}
