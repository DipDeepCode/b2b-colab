package ru.ddc.b2bcolab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ddc.b2bcolab.service.ChatMessageService;

@RestController
@RequestMapping("/api/chatMessage")
@RequiredArgsConstructor
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    @GetMapping
    public ResponseEntity<?> getChatMessagesByChatRoomId(@RequestParam Long chatRoomId) {
        return ResponseEntity.ok(chatMessageService.getLatestChatMessagesByChatId(chatRoomId));
    }
}
