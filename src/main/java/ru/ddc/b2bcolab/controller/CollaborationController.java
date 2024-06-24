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
import ru.ddc.b2bcolab.model.Collaboration;
import ru.ddc.b2bcolab.service.CollaborationService;

@Tag(name = "CollaborationController", description = "Контроллер для работы с коллаборациями")
@RestController
@RequestMapping("/api/collaborations")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
public class CollaborationController {
    private final CollaborationService collaborationService;

    @Operation(summary = "Сохранение коллаборации")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Коллаборация успешно сохранена",
                    content = @Content(schema = @Schema(implementation = Collaboration.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createCollaboration(@Parameter(description = "Информация о коллаборации") @RequestBody Collaboration collaboration) {
        return ResponseEntity.ok(collaborationService.save(collaboration));
    }

    @Operation(summary = "Получение всех коллабораций")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Collaboration.class)))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<?> getAllCollaborations() {
        return ResponseEntity.ok(collaborationService.findAll());
    }

    @Operation(summary = "Получение коллаборации по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(schema = @Schema(implementation = Collaboration.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getCollaborationById(@PathVariable Long id) {
        return ResponseEntity.ok(collaborationService.findById(id));
    }

    @Operation(summary = "Обновление коллаборации")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Коллаборация успешно обновлена",
                    content = @Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCollaboration(@PathVariable Long id, @RequestBody Collaboration updatedCollaboration) {
        updatedCollaboration.setId(id); // Убедимся, что id совпадает с тем, который передан в URL
        collaborationService.update(updatedCollaboration);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Удаление коллаборации")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Коллаборация успешно удалена",
                    content = @Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollaboration(@PathVariable Long id) {
        collaborationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}