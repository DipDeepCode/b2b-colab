package ru.ddc.b2bcolab.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatMessage {
    private Long id;
    private Long chatRoomId;
    private String senderPhoneNumber;
    private String content;
    private LocalDateTime createdAt = LocalDateTime.now();
}
