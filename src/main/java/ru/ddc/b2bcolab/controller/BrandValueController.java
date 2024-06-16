package ru.ddc.b2bcolab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.b2bcolab.model.BrandValue;
import ru.ddc.b2bcolab.service.BrandValueService;

@Tag(name = "BrandValueController", description = "Контроллер для работы с ключевыми ценностями бренда")
@RestController
@RequestMapping("/api/brand-values")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
public class BrandValueController {
    private final BrandValueService brandValueService;

    @Operation(summary = "Сохранение ключевой ценности бренда")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ключевая ценность бренда успешно сохранена",
                    content = @Content(schema = @Schema(implementation = BrandValue.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createBrandValue(@Parameter(description = "Ключевая ценность бренда") @RequestBody BrandValue brandValue) {
        return ResponseEntity.ok(brandValueService.save(brandValue));
    }

    @Operation(summary = "Получение всех ключевых ценностей бренда")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BrandValue.class)))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<?> getAllBrandValues() {
        return ResponseEntity.ok(brandValueService.findAll());
    }

    @Operation(summary = "Получение ключевой ценности бренда по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(schema = @Schema(implementation = BrandValue.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getBrandValueById(@PathVariable Long id) {
        return ResponseEntity.ok(brandValueService.findById(id));
    }

    @Operation(summary = "Обновление ключевой ценности бренда")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Ключевая ценность бренда успешно обновлена",
                    content = @Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBrandValue(@PathVariable Long id, @RequestBody BrandValue updatedBrandValue) {
        updatedBrandValue.setId(id); // Убедимся, что id совпадает с тем, который передан в URL
        brandValueService.update(updatedBrandValue);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Удаление ключевой ценности бренда")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Ключевая ценность бренда успешно удалена",
                    content = @Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrandValue(@PathVariable Long id) {
        brandValueService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}