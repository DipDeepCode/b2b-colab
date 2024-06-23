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
import ru.ddc.b2bcolab.model.GoalCollaboration;
import ru.ddc.b2bcolab.service.GoalCollaborationService;

@Tag(name = "GoalCollaborationController", description = "Контроллер для работы с целями коллабораций")
@RestController
@RequestMapping("/api/goal-collaborations")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
public class GoalCollaborationController {
    private final GoalCollaborationService goalCollaborationService;

    @Operation(summary = "Сохранение цели коллаборации")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Цель коллаборации успешно сохранена",
                    content = @Content(schema = @Schema(implementation = GoalCollaboration.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createGoalCollaboration(@Parameter(description = "Цель коллаборации") @RequestBody GoalCollaboration goalCollaboration) {
        return ResponseEntity.ok(goalCollaborationService.save(goalCollaboration));
    }

    @Operation(summary = "Получение всех целей коллабораций")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = GoalCollaboration.class)))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<?> getAllGoalCollaborations() {
        return ResponseEntity.ok(goalCollaborationService.findAll());
    }

    @Operation(summary = "Получение цели коллаборации по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(schema = @Schema(implementation = GoalCollaboration.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getGoalCollaborationById(@PathVariable Long id) {
        return ResponseEntity.ok(goalCollaborationService.findById(id));
    }

    @Operation(summary = "Обновление цели коллаборации")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Цель коллаборации успешно обновлена",
                    content = @Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGoalCollaboration(@PathVariable Long id, @RequestBody GoalCollaboration updatedGoalCollaboration) {
        updatedGoalCollaboration.setId(id); // Убедимся, что id совпадает с тем, который передан в URL
        goalCollaborationService.update(updatedGoalCollaboration);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Удаление цели коллаборации")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Цель коллаборации успешно удалена",
                    content = @Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoalCollaboration(@PathVariable Long id) {
        goalCollaborationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}