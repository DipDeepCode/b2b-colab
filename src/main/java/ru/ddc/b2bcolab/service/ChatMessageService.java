package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ddc.b2bcolab.model.ChatMessage;
import ru.ddc.b2bcolab.repository.ChatMessageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    public List<ChatMessage> getLatestChatMessagesByChatId(Long chatRoomId) {
        return chatMessageRepository.findLatestChatMessagesByChatRoomId(chatRoomId);
    }

    public ChatMessage addChatMessage(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }
}
