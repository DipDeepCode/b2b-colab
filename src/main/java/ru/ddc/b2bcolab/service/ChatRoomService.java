package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.ddc.b2bcolab.controller.payload.ChatRoomSummaryData;
import ru.ddc.b2bcolab.model.*;
import ru.ddc.b2bcolab.repository.BrandRepository;
import ru.ddc.b2bcolab.repository.ChatMessageRepository;
import ru.ddc.b2bcolab.repository.ChatRoomRepository;
import ru.ddc.b2bcolab.repository.ImageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final BrandRepository brandRepository;
    private final ImageRepository imageRepository;
    private final ChatMessageRepository chatMessageRepository;

    public List<ChatRoomSummaryData> getChatRoomsSummaryData() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Customer customer = (Customer) authentication.getPrincipal();
        List<ChatRoom> chatRooms = chatRoomRepository.findByCustomerPhoneNumber(customer.getPhoneNumber());
        return chatRooms.stream()
                .map(chatRoom -> getSummaryData(chatRoom, customer))
                .toList();
    }

    private ChatRoomSummaryData getSummaryData(ChatRoom chatRoom, Customer customer) {
        String secondMemberPhoneNumber =
                chatRoom.getPhoneNumber1().equals(customer.getPhoneNumber()) ?
                        chatRoom.getPhoneNumber2() :
                        chatRoom.getPhoneNumber1();
        Brand brand = brandRepository.findByPhoneNumber(secondMemberPhoneNumber).orElseThrow();
        Image logoImage = imageRepository.findLogoImageByBrandId(brand.getId());
        ChatMessage lastChatMessage = chatMessageRepository.findLastChatMessageByChatRoomId(chatRoom.getId()).orElseThrow();
        return ChatRoomSummaryData.builder()
                .chatRoom(chatRoom)
                .brand(brand)
                .logoImage(logoImage)
                .lastChatMessage(lastChatMessage)
                .build();
    }
}
