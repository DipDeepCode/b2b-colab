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
import ru.ddc.b2bcolab.model.TariffPlan;
import ru.ddc.b2bcolab.service.TariffPlanService;

@Tag(name = "TariffPlanController", description = "Контроллер тарифов")
@RestController
@RequestMapping("/api/tariffPlan")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
public class TariffPlanController {
    private final TariffPlanService tariffPlanService;

    @Operation(summary = "Сохранить тариф")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Тариф успешно сохранен",
                    content = @Content(schema = @Schema(implementation = TariffPlan.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createTariffPlan(@RequestBody TariffPlan tariffPlan) {
        return ResponseEntity.ok(tariffPlanService.createTariffPlan(tariffPlan));
    }

    @Operation(summary = "Получить все тарифы")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = TariffPlan.class)))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<?> getAllTariffPlans() {
        return ResponseEntity.ok(tariffPlanService.getAllTariffPlans());
    }

    @Operation(summary = "Получить тариф по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(schema = @Schema(implementation = TariffPlan.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getTariffPlanById(@PathVariable Long id) {
        return ResponseEntity.ok(tariffPlanService.getTariffPlanById(id));
    }

    @Operation(summary = "Изменить тариф")
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
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTariffPlan(@PathVariable Long id, @RequestBody TariffPlan tariffPlan) {
        tariffPlan.setId(id);
        tariffPlanService.updateTariffPlan(tariffPlan);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить тариф")
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
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTariffPlan(@PathVariable Long id) {
        tariffPlanService.deleteTariffPlanById(id);
        return ResponseEntity.ok().build();
    }
}
