package ru.ddc.b2bcolab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.b2bcolab.model.Like;
import ru.ddc.b2bcolab.service.LikeService;

import java.util.List;

@Tag(name = "LikeController", description = "Контроллер для работы с лайками")
@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
public class LikeController {
    private final LikeService likeService;

    @Operation(summary = "Получение всех лайков")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(schema = @Schema(implementation = Like.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Like>> getAllLikes() {
        return ResponseEntity.ok(likeService.findAll());
    }

    @Operation(summary = "Сохранение лайка")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Лайк успешно сохранен",
                    content = @Content(schema = @Schema(implementation = Like.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Like> saveLike(@Parameter(description = "Лайк") @RequestBody Like like) {
        return ResponseEntity.ok(likeService.save(like));
    }

    @Operation(summary = "Удаление лайка по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Лайк успешно удален",
                    content = @Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long id) {
        likeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение лайка по его id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(schema = @Schema(implementation = Like.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Like> getLikeById(@PathVariable Long id) {
        return ResponseEntity.ok(likeService.findById(id));
    }
}