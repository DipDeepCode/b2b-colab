package ru.ddc.b2bcolab.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatMessage {
    private Long id;
    private Long chatRoomId;
    private String username;
    private String content;
    private LocalDateTime createdAt;
}
