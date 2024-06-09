package ru.ddc.b2bcolab.upload.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ddc.b2bcolab.upload.controller.payload.StoreFileResponse;
import ru.ddc.b2bcolab.upload.service.StorageService;

@Tag(name = "FileController", description = "Контроллер сохранения и получения файлов")
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {
    private final StorageService storageService;

    @Operation(summary = "Сохранение файла")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл успешно сохранен"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен")
    })
    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        String store = storageService.store(file);
        StoreFileResponse storeFileResponse = StoreFileResponse.of(store);
        return ResponseEntity.ok(storeFileResponse);
    }

    @Operation(summary = "Получение файла")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен")
    })
    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getFile(@RequestParam("filename") String filename) {
        return ResponseEntity.ok(storageService.load(filename));
    }
}
