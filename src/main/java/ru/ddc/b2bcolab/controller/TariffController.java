package ru.ddc.b2bcolab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.b2bcolab.model.Tariff;
import ru.ddc.b2bcolab.repository.TariffType;
import ru.ddc.b2bcolab.service.TariffService;
import java.time.LocalDate;
import java.util.List;

@Tag(name = "TariffController", description = "Контроллер для работы с тарифами")
@RestController
@RequestMapping("/api/tariffs")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
public class TariffController {
    private final TariffService tariffService;
    @Operation(summary = "Купить тариф")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Тариф успешно куплен"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные")
    })
    @PostMapping("/buy/{type}")
    public ResponseEntity<Tariff> buyTariff(@PathVariable TariffType type) {
        return ResponseEntity.ok(tariffService.buyTariff(type));
    }

    @Operation(summary = "Показать мой тариф")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тариф успешно получен"),
            @ApiResponse(responseCode = "404", description = "Тариф не найден")
    })
    @GetMapping("/my/{id}")
    public ResponseEntity<Tariff> getMyTariff(@PathVariable Long id) {
        Tariff tariff = tariffService.getMyTariff(id);
        return tariff != null ? ResponseEntity.ok(tariff) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Апгрейд тарифа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тариф успешно обновлен"),
            @ApiResponse(responseCode = "404", description = "Тариф не найден")
    })
    @PutMapping("/upgrade/{id}/{newType}")
    public ResponseEntity<Tariff> upgradeTariff(@PathVariable Long id, @PathVariable TariffType newType) {
        Tariff upgradedTariff = tariffService.upgradeTariff(id, newType);
        return upgradedTariff != null ? ResponseEntity.ok(upgradedTariff) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Получить дату окончания тарифа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Дата успешно получена"),
            @ApiResponse(responseCode = "404", description = "Тариф не найден")
    })
    @GetMapping("/enddate/{id}")
    public ResponseEntity<LocalDate> getEndDateOfTariff(@PathVariable Long id) {
        LocalDate endDate = tariffService.getEndDateOfTariff(id);
        return endDate != null ? ResponseEntity.ok(endDate) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Продлить тариф")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тариф успешно продлен"),
            @ApiResponse(responseCode = "404", description = "Тариф не найден")
    })
    @PutMapping("/extend/{id}/{months}")
    public ResponseEntity<Tariff> extendTariff(@PathVariable Long id, @PathVariable int months) {
        Tariff extendedTariff = tariffService.extendTariff(id, months);
        return extendedTariff != null ? ResponseEntity.ok(extendedTariff) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Получить все тарифы")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный запрос"),
            @ApiResponse(responseCode = "204", description = "Нет данных")
    })
    @GetMapping
    public ResponseEntity<List<Tariff>> getAllTariffs() {
        List<Tariff> tariffs = tariffService.getAllTariffs();
        return tariffs != null && !tariffs.isEmpty() ? ResponseEntity.ok(tariffs) : ResponseEntity.noContent().build();
    }
}