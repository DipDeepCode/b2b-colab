package ru.ddc.b2bcolab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
    private Long id;
    private String phoneNumber1;
    private String phoneNumber2;
    private String roomName;
}
