package ru.ddc.b2bcolab.controller;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.b2bcolab.model.Questionnaire;
import ru.ddc.b2bcolab.service.QuestionnaireService;

@Tag(name = "QuestionnaireController", description = "Контроллер для работы с анкетами")
@RestController
@RequestMapping("/api/questionnaires")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
public class QuestionnaireController {
    private final QuestionnaireService questionnaireService;

    @Operation(summary = "Создать новую анкету")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Анкета успешно создана"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные")
    })
    @PostMapping
    public ResponseEntity<Questionnaire> createQuestionnaire(@RequestBody Questionnaire questionnaire) {
        return ResponseEntity.ok(questionnaireService.saveQuestionnaire(questionnaire));
    }

    @Operation(summary = "Получить анкету по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный запрос"),
            @ApiResponse(responseCode = "404", description = "Анкета не найдена")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Questionnaire> getQuestionnaireById(@PathVariable Long id) {
        return questionnaireService.getQuestionnaireById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Обновить анкету")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Анкета успешно обновлена"),
            @ApiResponse(responseCode = "404", description = "Анкета не найдена")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Questionnaire> updateQuestionnaire(@PathVariable Long id, @RequestBody Questionnaire questionnaire) {
        if (id.equals(questionnaire.getId())) {
            return ResponseEntity.ok(questionnaireService.updateQuestionnaire(questionnaire));
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Удалить анкету")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Анкета успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Анкета не найдена")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestionnaire(@PathVariable Long id) {
        if (questionnaireService.deleteQuestionnaire(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
