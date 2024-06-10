package ru.ddc.b2bcolab.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ddc.b2bcolab.chat.model.ChatRoom;
import ru.ddc.b2bcolab.chat.repository.ChatRoomRepository;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomRepository chatRoomRepository;

    @GetMapping
    public List<ChatRoom> getByPhoneNumber(@RequestParam String phoneNumber) {
        System.out.println(phoneNumber);
        List<ChatRoom> byPhoneNumber = chatRoomRepository.findByPhoneNumber(phoneNumber);
        byPhoneNumber.forEach(System.out::println);
        return byPhoneNumber;
    }
}
