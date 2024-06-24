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
import ru.ddc.b2bcolab.model.InteractionFormat;
import ru.ddc.b2bcolab.service.InteractionFormatService;

@Tag(name = "InteractionFormatController", description = "Контроллер для работы с форматами взаимодействия")
@RestController
@RequestMapping("/api/interaction-formats")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
public class InteractionFormatController {
    private final InteractionFormatService interactionFormatService;

    @Operation(summary = "Сохранение формата взаимодействия")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Формат взаимодействия успешно сохранен",
                    content = @Content(schema = @Schema(implementation = InteractionFormat.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createInteractionFormat(@Parameter(description = "Формат взаимодействия") @RequestBody InteractionFormat interactionFormat) {
        return ResponseEntity.ok(interactionFormatService.save(interactionFormat));
    }

    @Operation(summary = "Получение всех форматов взаимодействия")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = InteractionFormat.class)))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<?> getAllInteractionFormats() {
        return ResponseEntity.ok(interactionFormatService.findAll());
    }

    @Operation(summary = "Получение формата взаимодействия по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(schema = @Schema(implementation = InteractionFormat.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getInteractionFormatById(@PathVariable Long id) {
        return ResponseEntity.ok(interactionFormatService.findById(id));
    }

    @Operation(summary = "Обновление формата взаимодействия")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Формат взаимодействия успешно обновлен",
                    content = @Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateInteractionFormat(@PathVariable Long id, @RequestBody InteractionFormat updatedInteractionFormat) {
        updatedInteractionFormat.setId(id); // Убедимся, что id совпадает с тем, который передан в URL
        interactionFormatService.update(updatedInteractionFormat);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Удаление формата взаимодействия")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Формат взаимодействия успешно удален",
                    content = @Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInteractionFormat(@PathVariable Long id) {
        interactionFormatService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}