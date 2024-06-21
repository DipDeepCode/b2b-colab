package ru.ddc.b2bcolab.controller.payload;

import lombok.*;
import ru.ddc.b2bcolab.model.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomSummaryData {
    private ChatRoom chatRoom;
    private Brand brand;
    private Image logoImage;
    private ChatMessage lastChatMessage;
}
