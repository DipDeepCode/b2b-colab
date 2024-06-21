package ru.ddc.b2bcolab.controller.payload;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    private Long id;
    private Long chatRoomId;
    private String senderPhoneNumber;
    private String content;
    private LocalDateTime createdAt = LocalDateTime.now();
}
