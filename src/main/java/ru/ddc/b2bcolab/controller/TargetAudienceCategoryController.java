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
import ru.ddc.b2bcolab.model.TargetAudienceCategory;
import ru.ddc.b2bcolab.service.TargetAudienceCategoryService;

@Tag(name = "TargetAudienceCategoryController", description = "Контроллер для работы с категориями целевой аудитории")
@RestController
@RequestMapping("/api/target-audience-categories")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
public class TargetAudienceCategoryController {
    private final TargetAudienceCategoryService targetAudienceCategoryService;

    @Operation(summary = "Сохранение категории целевой аудитории")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Категория целевой аудитории успешно сохранена",
                    content = @Content(schema = @Schema(implementation = TargetAudienceCategory.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createTargetAudienceCategory(@Parameter(description = "Категория целевой аудитории") @RequestBody TargetAudienceCategory targetAudienceCategory) {
        return ResponseEntity.ok(targetAudienceCategoryService.save(targetAudienceCategory));
    }

    @Operation(summary = "Получение всех категорий целевой аудитории")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TargetAudienceCategory.class)))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<?> getAllTargetAudienceCategories() {
        return ResponseEntity.ok(targetAudienceCategoryService.findAll());
    }

    @Operation(summary = "Получение категории целевой аудитории по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(schema = @Schema(implementation = TargetAudienceCategory.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getTargetAudienceCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(targetAudienceCategoryService.findById(id));
    }

    @Operation(summary = "Обновление категории целевой аудитории")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Категория целевой аудитории успешно обновлена",
                    content = @Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTargetAudienceCategory(@PathVariable Long id, @RequestBody TargetAudienceCategory updatedTargetAudienceCategory) {
        updatedTargetAudienceCategory.setId(id); // Убедимся, что id совпадает с тем, который передан в URL
        targetAudienceCategoryService.update(updatedTargetAudienceCategory);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Удаление категории целевой аудитории")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Категория целевой аудитории успешно удалена",
                    content = @Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTargetAudienceCategory(@PathVariable Long id) {
        targetAudienceCategoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}