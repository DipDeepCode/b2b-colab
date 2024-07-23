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
import ru.ddc.b2bcolab.model.BrandProfile;
import ru.ddc.b2bcolab.service.BrandProfileService;

import java.util.List;
import java.util.Optional;

@Tag(name = "BrandProfileController", description = "Контроллер для управления профилями брендов")
@RestController
@RequestMapping("/api/brand-profiles")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
public class BrandProfileController {
    private final BrandProfileService brandProfileService;

    @Operation(summary = "Сохранение профиля бренда")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Профиль бренда успешно сохранен",
                    content = @Content(schema = @Schema(implementation = BrandProfile.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createBrandProfile(@Parameter(description = "Профиль бренда") @RequestBody BrandProfile brandProfile) {
        return ResponseEntity.ok(brandProfileService.save(brandProfile));
    }

    @Operation(summary = "Получение профиля бренда по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(schema = @Schema(implementation = BrandProfile.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getBrandProfileById(@PathVariable Long id) {
        Optional<BrandProfile> brandProfile = brandProfileService.findById(id);
        return brandProfile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Получение всех профилей брендов")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BrandProfile.class)))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<BrandProfile>> getAllBrandProfiles() {
        List<BrandProfile> brandProfiles = brandProfileService.findAll();
        return ResponseEntity.ok(brandProfiles);
    }

    @Operation(summary = "Обновление профиля бренда")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Профиль бренда успешно обновлен",
                    content = @Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBrandProfile(@PathVariable Long id, @RequestBody BrandProfile updatedBrandProfile) {
        updatedBrandProfile.setId(id); // Убедимся, что id совпадает с тем, который передан в URL
        brandProfileService.update(updatedBrandProfile);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Удаление профиля бренда")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Профиль бренда успешно удален",
                    content = @Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrandProfile(@PathVariable Long id) {
        brandProfileService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}