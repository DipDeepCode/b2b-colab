package ru.ddc.b2bcolab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ddc.b2bcolab.model.Image;
import ru.ddc.b2bcolab.model.ImageType;
import ru.ddc.b2bcolab.service.ImageService;

@Tag(name = "ImageController", description = "Контроллер сохранения и получения изображений")
@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
public class ImageController {
    private final ImageService imageService;

    @Operation(summary = "Сохранение изображения")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Изображение успешно сохранено",
                    content = @Content(schema = @Schema(implementation = Image.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createImage(
            @Parameter(description = "Id бренда") @RequestParam("brandId") Long brandId,
            @Parameter(description = "Тип изображения") @RequestParam("imageType") ImageType imageType,
            @Parameter(description = "Файл изображения") @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(imageService.createImage(brandId, imageType, file));
    }

    @Operation(summary = "Получение изображения по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(mediaType = MediaType.IMAGE_PNG_VALUE)),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getImage(@PathVariable("id") Long id) {
        Resource resource = imageService.getImageById(id);
        MediaType mimeType = MediaTypeFactory.getMediaType(resource.getFilename()).orElse(MediaType.IMAGE_PNG);
        return ResponseEntity.ok().contentType(mimeType).body(resource);
    }
}
