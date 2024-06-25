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
        public ResponseEntity<?> createBrand(@Parameter(description = "Бренд") @RequestBody Brand brand) {
            return ResponseEntity.ok(brandService.save(brand));
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
            return ResponseEntity.ok(brandService.findById(id));
        }

        @Operation(summary = "Получение всех брендов")
        @ApiResponses({
                @ApiResponse(
                        responseCode = "200",
                        description = "Успешный запрос",
                        content = @Content(array = @ArraySchema(schema = @Schema(implementation = Brand.class)))),
                @ApiResponse(
                        responseCode = "403",
                        description = "Доступ к запрошенному ресурсу запрещен",
                        content = @Content)
        })
        @GetMapping
        public ResponseEntity<?> getAllBrands() {
            return ResponseEntity.ok(brandService.findAll());
        }

        @Operation(summary = "Обновление бренда")
        @ApiResponses({
                @ApiResponse(
                        responseCode = "204",
                        description = "Бренд успешно обновлен",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "Доступ к запрошенному ресурсу запрещен",
                        content = @Content)
        })
        @PutMapping("/{id}")
        public ResponseEntity<Void> updateBrand(@PathVariable Long id, @RequestBody Brand updatedBrand) {
            updatedBrand.setId(id); // Убедимся, что id совпадает с тем, который передан в URL
            brandService.update(updatedBrand);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @Operation(summary = "Удаление бренда")
        @ApiResponses({
                @ApiResponse(
                        responseCode = "204",
                        description = "Бренд успешно удален",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "Доступ к запрошенному ресурсу запрещен",
                        content = @Content)
        })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
            brandService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }