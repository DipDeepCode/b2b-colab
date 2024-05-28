package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ddc.b2bcolab.controller.payload.CreateChatMessageRequest;
import ru.ddc.b2bcolab.controller.payload.UpdateChatMessageRequest;
import ru.ddc.b2bcolab.model.ChatMessage;
import ru.ddc.b2bcolab.repository.ChatMessageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ModelMapper modelMapper;

    public ChatMessage createChatMessage(final CreateChatMessageRequest request) {
        ChatMessage chatMessage = modelMapper.map(request, ChatMessage.class);
        return chatMessageRepository.save(chatMessage);
    }

    public ChatMessage getChatMessageById(final Long id) {
        return chatMessageRepository.findById(id).orElseThrow();
    }

    public List<ChatMessage> getAllChatMessages() {
        return chatMessageRepository.findAll();
    }

    public List<ChatMessage> getChatMessagesByChatRoomId(final Long chatId) {
        return chatMessageRepository.findByChatId(chatId);
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
