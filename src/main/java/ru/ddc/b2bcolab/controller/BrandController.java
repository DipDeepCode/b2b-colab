package ru.ddc.b2bcolab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.b2bcolab.controller.payload.CreateBrandRequest;
import ru.ddc.b2bcolab.model.Brand;
import ru.ddc.b2bcolab.service.BrandService;

@Tag(name = "BrandController", description = "Контроллер сохранения и получения брендов (анкет)")
@RestController
@RequestMapping("/api/brand")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
public class BrandController {
    private final BrandService brandService;

    @Operation(summary = "Сохранение бренда")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Бренд успешно сохранен",
                    content = @Content(schema = @Schema(implementation = Brand.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createBrand(@Parameter(description = "Бренд") @RequestBody CreateBrandRequest request) {
        return ResponseEntity.ok(brandService.saveBrand(request));
    }

    @Operation(summary = "Получение бренда по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(schema = @Schema(implementation = Brand.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.getById(id));
    }

}
