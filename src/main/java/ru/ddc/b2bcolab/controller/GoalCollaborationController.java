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
import ru.ddc.b2bcolab.model.GoalCollaboration;
import ru.ddc.b2bcolab.service.GoalCollaborationService;

import java.util.List;

@RestController
@RequestMapping("/api/goal-collaborations")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
@Tag(name = "GoalCollaborationController", description = "Контроллер сохранения и получения целей коллабораций")
public class GoalCollaborationController {
    private final GoalCollaborationService goalCollaborationService;

    @Operation(summary = "Сохранение цели коллаборации")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Цель коллаборации успешно сохранена",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = GoalCollaboration.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @io.swagger.v3.oas.annotations.media.Content)
    })
    @PostMapping
    public ResponseEntity<GoalCollaboration> createGoalCollaboration(@Parameter(description = "Цель коллаборации") @RequestBody GoalCollaboration goalCollaboration) {
        GoalCollaboration createdGoalCollaboration = goalCollaborationService.save(goalCollaboration);
        return new ResponseEntity<>(createdGoalCollaboration, HttpStatus.CREATED);
    }

    @Operation(summary = "Получение всех целей коллабораций")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = GoalCollaboration.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @io.swagger.v3.oas.annotations.media.Content)
    })
    @GetMapping
    public ResponseEntity<List<GoalCollaboration>> getAllGoalCollaborations() {
        List<GoalCollaboration> goalCollaborations = goalCollaborationService.findAll();
        return new ResponseEntity<>(goalCollaborations, HttpStatus.OK);
    }

    @Operation(summary = "Получение цели коллаборации по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = GoalCollaboration.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @io.swagger.v3.oas.annotations.media.Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<GoalCollaboration> getGoalCollaborationById(@PathVariable Long id) {
        GoalCollaboration goalCollaboration = goalCollaborationService.findById(id);
        return new ResponseEntity<>(goalCollaboration, HttpStatus.OK);
    }

    @Operation(summary = "Удаление цели коллаборации по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Цель коллаборации успешно удалена",
                    content = @io.swagger.v3.oas.annotations.media.Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @io.swagger.v3.oas.annotations.media.Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoalCollaboration(@PathVariable Long id) {
        goalCollaborationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
