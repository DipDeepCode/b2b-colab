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
import ru.ddc.b2bcolab.model.TopicDiscussion;
import ru.ddc.b2bcolab.service.TopicDiscussionService;

@Tag(name = "TopicDiscussionController", description = "Контроллер сохранения и получения тем для обсуждения")
@RestController
@RequestMapping("/api/topic-discussions")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
public class TopicDiscussionController {
    private final TopicDiscussionService topicDiscussionService;

    @Operation(summary = "Сохранение темы для обсуждения")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Тема для обсуждения успешно сохранена",
                    content = @Content(schema = @Schema(implementation = TopicDiscussion.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createTopicDiscussion(@Parameter(description = "Тема для обсуждения") @RequestBody TopicDiscussion topicDiscussion) {
        return ResponseEntity.ok(topicDiscussionService.save(topicDiscussion));
    }

    @Operation(summary = "Получение темы для обсуждения по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(schema = @Schema(implementation = TopicDiscussion.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getTopicDiscussionById(@PathVariable Long id) {
        return ResponseEntity.ok(topicDiscussionService.findById(id));
    }

    @Operation(summary = "Получение всех тем для обсуждения")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TopicDiscussion.class)))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<?> getAllTopicDiscussions() {
        return ResponseEntity.ok(topicDiscussionService.findAll());
    }

    @Operation(summary = "Обновление темы для обсуждения")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Тема для обсуждения успешно обновлена",
                    content = @Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTopicDiscussion(@PathVariable Long id, @RequestBody TopicDiscussion updatedTopicDiscussion) {
        updatedTopicDiscussion.setId(id); // Убедимся, что id совпадает с тем, который передан в URL
        topicDiscussionService.update(updatedTopicDiscussion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Удаление темы для обсуждения")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Тема для обсуждения успешно удалена",
                    content = @Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopicDiscussion(@PathVariable Long id) {
        topicDiscussionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
