package ru.ddc.b2bcolab.controller.rest.payload;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateChatMessageRequest {
    private Long chatRoomId;
    private String username;
    private String content;
    private LocalDateTime createdAt;
}
