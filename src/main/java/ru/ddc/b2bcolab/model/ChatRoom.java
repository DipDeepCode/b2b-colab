package ru.ddc.b2bcolab.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatRoom {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}
