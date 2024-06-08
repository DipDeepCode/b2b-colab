package ru.ddc.b2bcolab.upload.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ddc.b2bcolab.upload.controller.payload.StoreFileResponse;
import ru.ddc.b2bcolab.upload.service.StorageService;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {
    private final StorageService storageService;

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        String store = storageService.store(file);
        StoreFileResponse storeFileResponse = StoreFileResponse.of(store);
        return ResponseEntity.ok(storeFileResponse);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getFile(@RequestParam("filename") String filename) {
        return ResponseEntity.ok(storageService.load(filename));
    }
}
