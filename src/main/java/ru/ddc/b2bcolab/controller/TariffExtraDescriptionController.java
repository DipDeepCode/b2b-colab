package ru.ddc.b2bcolab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.b2bcolab.model.TariffExtraDescription;
import ru.ddc.b2bcolab.service.TariffExtraDescriptionService;

@Tag(name = "TariffExtraDescriptionController", description = "Контроллер для дополнительных характеристик тарифа")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
public class TariffExtraDescriptionController {
    private final TariffExtraDescriptionService tariffExtraDescriptionService;

    @Operation(summary = "Сохранить характеристику")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Характеристика успешно сохранена",
                    content = @Content(schema = @Schema(implementation = TariffExtraDescription.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PostMapping("/tariffExtraDescription")
    public ResponseEntity<?> createTariffExtraDescription(@RequestBody TariffExtraDescription tariffExtraDescription) {
        return ResponseEntity.ok(tariffExtraDescriptionService.createTariffExtraDescription(tariffExtraDescription));
    }

    @Operation(summary = "Получить все характеристики")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TariffExtraDescription.class)))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping("/tariffExtraDescription")
    public ResponseEntity<?> getAllTariffExtraDescriptions() {
        return ResponseEntity.ok(tariffExtraDescriptionService.getAllTariffExtraDescriptions());
    }

    @Operation(summary = "Получить характеристику по ее id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(schema = @Schema(implementation = TariffExtraDescription.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping("/tariffExtraDescription/{id}")
    public ResponseEntity<?> getTariffExtraDescriptionById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(tariffExtraDescriptionService.getTariffExtraDescriptionById(id));
    }

    @Operation(summary = "Получить все характеристики тарифа по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TariffExtraDescription.class)))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping("/tariffPlan/{id}/tariffExtraDescription")
    public ResponseEntity<?> getTariffExtraDescriptionByPlanId(@PathVariable("id") Long tariffPlanId) {
        return ResponseEntity.ok(tariffExtraDescriptionService.getTariffExtraDescriptionsByTariffPlanId(tariffPlanId));
    }

    @Operation(summary = "Изменить характеристику")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PatchMapping("/tariffExtraDescription/{id}")
    public ResponseEntity<?> updateTariffExtraDescription(@PathVariable("id") Long id,
                                                          TariffExtraDescription tariffExtraDescription) {
        tariffExtraDescription.setId(id);
        tariffExtraDescriptionService.updateTariffExtraDescription(tariffExtraDescription);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить характеристику")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @DeleteMapping("/tariffExtraDescription/{id}")
    public ResponseEntity<?> deleteTariffExtraDescription(@PathVariable("id") Long id) {
        tariffExtraDescriptionService.deleteTariffExtraDescriptionById(id);
        return ResponseEntity.ok().build();
    }
}
