package ru.ddc.b2bcolab.controller.websocket.payload;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PrivateWebSocketMessage {
    private String senderUsername;
    private String receiverUsername;
    private String content;
    private LocalDateTime createdAt;
}
