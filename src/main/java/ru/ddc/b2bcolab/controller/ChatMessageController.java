package ru.ddc.b2bcolab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.b2bcolab.controller.payload.CreateChatMessageRequest;
import ru.ddc.b2bcolab.controller.payload.UpdateChatMessageRequest;
import ru.ddc.b2bcolab.model.ChatMessage;
import ru.ddc.b2bcolab.service.ChatMessageService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    @PostMapping("/chatMessages")
    public ResponseEntity<?> createChatMessage(@RequestBody CreateChatMessageRequest request) {
        ChatMessage chatMessage = chatMessageService.createChatMessage(request);
        return new ResponseEntity<>(chatMessage, HttpStatus.CREATED);
    }

    @GetMapping("chatMessages/{id}")
    public ResponseEntity<?> getChatMessageById(@PathVariable("id") Long id) {
        ChatMessage chatMessage = chatMessageService.getChatMessageById(id);
        return ResponseEntity.ok(chatMessage);
    }

    @GetMapping("/chatMessages")
    public ResponseEntity<?> getAllChatMessages() {
        List<ChatMessage> chatMessages = chatMessageService.getAllChatMessages();
        return ResponseEntity.ok(chatMessages);
    }

    @GetMapping("/chatRooms/{chatRoomId}/chatMessages")
    public ResponseEntity<?> getChatMessagesByChatRoomId(@PathVariable("chatRoomId") Long chatRoomId) {
        List<ChatMessage> chatMessages = chatMessageService.getChatMessagesByChatRoomId(chatRoomId);
        return ResponseEntity.ok(chatMessages);
    }

    @PutMapping("/chatMessages/{id}")
    public ResponseEntity<?> updateChatMessage(@PathVariable("id") Long id,
                                               @RequestBody UpdateChatMessageRequest request) {
        ChatMessage chatMessage = chatMessageService.updateChatMessage(id, request);
        return ResponseEntity.ok(chatMessage);
    }

    @DeleteMapping("/chatMessages/{id}")
    public ResponseEntity<?> deleteChatMessage(@PathVariable("id") Long id) {
        chatMessageService.deleteChatMessage(id);
        return ResponseEntity.noContent().build();
    }
}
