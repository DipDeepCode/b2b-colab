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
import ru.ddc.b2bcolab.model.InteractionFormat;
import ru.ddc.b2bcolab.service.InteractionFormatService;

import java.util.List;

@RestController
@RequestMapping("/api/interaction-formats")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
@Tag(name = "InteractionFormatController", description = "Контроллер сохранения и получения форматов взаимодействия")
public class InteractionFormatController {
    private final InteractionFormatService interactionFormatService;

    @Operation(summary = "Сохранение формата взаимодействия")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Формат взаимодействия успешно сохранен",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = InteractionFormat.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @io.swagger.v3.oas.annotations.media.Content)
    })
    @PostMapping
    public ResponseEntity<InteractionFormat> createInteractionFormat(@Parameter(description = "Формат взаимодействия") @RequestBody InteractionFormat interactionFormat) {
        InteractionFormat createdInteractionFormat = interactionFormatService.save(interactionFormat);
        return new ResponseEntity<>(createdInteractionFormat, HttpStatus.CREATED);
    }

    @Operation(summary = "Получение всех форматов взаимодействия")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = InteractionFormat.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @io.swagger.v3.oas.annotations.media.Content)
    })
    @GetMapping
    public ResponseEntity<List<InteractionFormat>> getAllInteractionFormats() {
        List<InteractionFormat> interactionFormats = interactionFormatService.findAll();
        return new ResponseEntity<>(interactionFormats, HttpStatus.OK);
    }

    @Operation(summary = "Получение формата взаимодействия по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = InteractionFormat.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @io.swagger.v3.oas.annotations.media.Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<InteractionFormat> getInteractionFormatById(@PathVariable Long id) {
        InteractionFormat interactionFormat = interactionFormatService.findById(id);
        return new ResponseEntity<>(interactionFormat, HttpStatus.OK);
    }

    @Operation(summary = "Удаление формата взаимодействия по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Формат взаимодействия успешно удален",
                    content = @io.swagger.v3.oas.annotations.media.Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @io.swagger.v3.oas.annotations.media.Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInteractionFormat(@PathVariable Long id) {
        interactionFormatService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
