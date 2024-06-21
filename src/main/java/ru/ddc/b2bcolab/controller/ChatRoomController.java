package ru.ddc.b2bcolab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ddc.b2bcolab.controller.payload.ChatRoomSummaryData;
import ru.ddc.b2bcolab.service.ChatRoomService;

@Tag(name = "ChatRoomController", description = "Контроллер для ChatRoom")
@RestController
@RequestMapping("/api/chatRoom")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @Operation(summary = "Получить список ChatRoomSummaryData",
    description = "Объект ChatRoomSummaryData предназначен для отображения имеющихся у пользователя чатов. " +
            "В нем содержатся объекты ChatRoom, Brand (второго участника чата), " +
            "Image (логотип второго участника чата), ChatMessage (последнее сообщение в чате)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(schema = @Schema(implementation = ChatRoomSummaryData.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping("/summary")
    public ResponseEntity<?> getChatRoomsSummaryData() {
        return ResponseEntity.ok(chatRoomService.getChatRoomsSummaryData());
    }
}
