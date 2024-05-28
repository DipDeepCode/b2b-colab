package ru.ddc.b2bcolab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.b2bcolab.controller.payload.CreateChatRoomRequest;
import ru.ddc.b2bcolab.controller.payload.UpdateChatRoomRequest;
import ru.ddc.b2bcolab.model.ChatRoom;
import ru.ddc.b2bcolab.service.ChatRoomService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @PostMapping("/chatRooms")
    public ResponseEntity<?> createChatRoom(@RequestBody CreateChatRoomRequest request) {
        ChatRoom chatRoom = chatRoomService.createChatRoom(request);
        return new ResponseEntity<>(chatRoom, HttpStatus.CREATED);
    }

    @GetMapping("/chatRooms/{id}")
    public ResponseEntity<?> getChatRoomById(@PathVariable("id") Long id) {
        ChatRoom chatRoom = chatRoomService.getChatRoomById(id);
        return ResponseEntity.ok(chatRoom);
    }

    @GetMapping("/chatRooms")
    public ResponseEntity<?> getAllChatRooms() {
        List<ChatRoom> chatRooms = chatRoomService.getAllChatRooms();
        return ResponseEntity.ok(chatRooms);
    }

    @GetMapping("/chatRooms")
    public ResponseEntity<?> getChatRoomsByUserUsername(@RequestParam String username) {
        List<ChatRoom> chatRooms = chatRoomService.getChatRoomsByUserUsername(username);
        return ResponseEntity.ok(chatRooms);
    }

    @PutMapping("/chatRooms/{id}")
    public ResponseEntity<?> updateChatRoom(@PathVariable("id") Long id,
                                            @RequestBody UpdateChatRoomRequest request) {
        ChatRoom chatRoom = chatRoomService.updateChatRoom(id, request);
        return ResponseEntity.ok(chatRoom);
    }

    @DeleteMapping("/chatRooms/{id}")
    public ResponseEntity<?> deleteChatRoom(@PathVariable("id") Long id) {
        chatRoomService.deleteChatRoom(id);
        return ResponseEntity.noContent().build();
    }
}
