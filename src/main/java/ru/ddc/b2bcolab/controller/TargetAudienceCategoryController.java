package ru.ddc.b2bcolab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.b2bcolab.model.TargetAudienceCategory;
import ru.ddc.b2bcolab.service.TargetAudienceCategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/target-audience-categories")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
@Tag(name = "TargetAudienceCategoryController", description = "Контроллер сохранения и получения категорий целевой аудитории")
public class TargetAudienceCategoryController {
    private final TargetAudienceCategoryService targetAudienceCategoryService;

    @Operation(summary = "Сохранение категории целевой аудитории")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Категория целевой аудитории успешно сохранена",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TargetAudienceCategory.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @io.swagger.v3.oas.annotations.media.Content)
    })
    @PostMapping
    public ResponseEntity<TargetAudienceCategory> createTargetAudienceCategory(@Parameter(description = "Категория целевой аудитории") @RequestBody TargetAudienceCategory targetAudienceCategory) {
        TargetAudienceCategory createdTargetAudienceCategory = targetAudienceCategoryService.save(targetAudienceCategory);
        return new ResponseEntity<>(createdTargetAudienceCategory, HttpStatus.CREATED);
    }

    @Operation(summary = "Получение всех категорий целевой аудитории")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TargetAudienceCategory.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @io.swagger.v3.oas.annotations.media.Content)
    })
    @GetMapping
    public ResponseEntity<List<TargetAudienceCategory>> getAllTargetAudienceCategories() {
        List<TargetAudienceCategory> targetAudienceCategories = targetAudienceCategoryService.findAll();
        return new ResponseEntity<>(targetAudienceCategories, HttpStatus.OK);
    }

    @Operation(summary = "Получение категории целевой аудитории по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TargetAudienceCategory.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @io.swagger.v3.oas.annotations.media.Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TargetAudienceCategory> getTargetAudienceCategoryById(@PathVariable Long id) {
        TargetAudienceCategory targetAudienceCategory = targetAudienceCategoryService.findById(id);
        return new ResponseEntity<>(targetAudienceCategory, HttpStatus.OK);
    }

    @Operation(summary = "Удаление категории целевой аудитории по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Категория целевой аудитории успешно удалена",
                    content = @io.swagger.v3.oas.annotations.media.Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @io.swagger.v3.oas.annotations.media.Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTargetAudienceCategory(@PathVariable Long id) {
        targetAudienceCategoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}