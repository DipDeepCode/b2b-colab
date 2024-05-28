package ru.ddc.b2bcolab.controller.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateChatMessageRequest {
    private Long chatId;
    private String senderUsername;
    private String recipientUsername;
    private String content;
}
