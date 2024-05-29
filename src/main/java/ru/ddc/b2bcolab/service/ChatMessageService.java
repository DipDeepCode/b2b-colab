package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ddc.b2bcolab.controller.rest.payload.CreateChatMessageRequest;
import ru.ddc.b2bcolab.controller.rest.payload.UpdateChatMessageRequest;
import ru.ddc.b2bcolab.model.ChatMessage;
import ru.ddc.b2bcolab.repository.ChatMessageRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ModelMapper modelMapper;

    public ChatMessage createChatMessage(final CreateChatMessageRequest request) {
        ChatMessage chatMessage = modelMapper.map(request, ChatMessage.class);
        if (chatMessage.getCreatedAt() == null) {
            chatMessage.setCreatedAt(LocalDateTime.now());
        }
        return chatMessageRepository.save(chatMessage);
    }

    public ChatMessage getChatMessageById(final Long id) {
        return chatMessageRepository.findById(id).orElseThrow();
    }

    public List<ChatMessage> getAllChatMessages() {
        return chatMessageRepository.findAll();
    }

    public List<ChatMessage> getChatMessagesByChatRoomId(final Long chatId) {
        return chatMessageRepository.findByChatRoomId(chatId);
    }

    public ChatMessage updateChatMessage(final Long id, final UpdateChatMessageRequest request) {
        ChatMessage chatMessage = chatMessageRepository.findById(id).orElseThrow();
        modelMapper.map(request, chatMessage);
        chatMessageRepository.update(chatMessage);
        return chatMessage;
    }

    public void deleteChatMessage(final Long id) {
        chatMessageRepository.findById(id).orElseThrow();
        chatMessageRepository.deleteById(id);
    }
}
