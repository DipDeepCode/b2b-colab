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
import ru.ddc.b2bcolab.model.BrandValue;
import ru.ddc.b2bcolab.service.BrandValueService;
import java.util.List;

@RestController
@RequestMapping("/api/brand-values")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
@Tag(name = "BrandValueController", description = "Контроллер сохранения и получения ключевых ценностей бренда")
public class BrandValueController {
    private final BrandValueService brandValueService;

    @Operation(summary = "Сохранение ключевой ценности бренда")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Ключевая ценность бренда успешно сохранена",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = BrandValue.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @io.swagger.v3.oas.annotations.media.Content)
    })
    @PostMapping
    public ResponseEntity<BrandValue> createBrandValue(@Parameter(description = "Ключевая ценность бренда") @RequestBody BrandValue brandValue) {
        BrandValue createdBrandValue = brandValueService.save(brandValue);
        return new ResponseEntity<>(createdBrandValue, HttpStatus.CREATED);
    }

    @Operation(summary = "Получение всех ключевых ценностей бренда")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = BrandValue.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @io.swagger.v3.oas.annotations.media.Content)
    })
    @GetMapping
    public ResponseEntity<List<BrandValue>> getAllBrandValues() {
        List<BrandValue> brandValues = brandValueService.findAll();
        return new ResponseEntity<>(brandValues, HttpStatus.OK);
    }

    @Operation(summary = "Получение ключевой ценности бренда по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = BrandValue.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @io.swagger.v3.oas.annotations.media.Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<BrandValue> getBrandValueById(@PathVariable Long id) {
        BrandValue brandValue = brandValueService.findById(id);
        return new ResponseEntity<>(brandValue, HttpStatus.OK);
    }

    @Operation(summary = "Удаление ключевой ценности бренда по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Ключевая ценность бренда успешно удалена",
                    content = @io.swagger.v3.oas.annotations.media.Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @io.swagger.v3.oas.annotations.media.Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrandValue(@PathVariable Long id) {
        brandValueService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}