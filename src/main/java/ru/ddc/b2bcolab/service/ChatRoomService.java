package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ddc.b2bcolab.controller.payload.CreateChatRoomRequest;
import ru.ddc.b2bcolab.controller.payload.UpdateChatRoomRequest;
import ru.ddc.b2bcolab.model.ChatRoom;
import ru.ddc.b2bcolab.repository.ChatRoomRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ModelMapper modelMapper;

    public ChatRoom createChatRoom(final CreateChatRoomRequest request) {
        ChatRoom chatRoom = modelMapper.map(request, ChatRoom.class);
        return chatRoomRepository.save(chatRoom);
    }

    public ChatRoom getChatRoomById(final Long id) {
        return chatRoomRepository.findById(id).orElseThrow();
    }

    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    public List<ChatRoom> getChatRoomsByUserUsername(String username) {
        return chatRoomRepository.getChatRoomsByUserUsername(username);
    }

    public ChatRoom updateChatRoom(final Long id, final UpdateChatRoomRequest request) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow();
        modelMapper.map(request, chatRoom);
        chatRoomRepository.update(chatRoom);
        return chatRoom;
    }

    public void deleteChatRoom(final Long id) {
        chatRoomRepository.findById(id).orElseThrow();
        chatRoomRepository.deleteById(id);
    }
}
