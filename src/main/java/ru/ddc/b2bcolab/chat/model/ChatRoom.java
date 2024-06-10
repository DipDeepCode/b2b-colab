package ru.ddc.b2bcolab.chat.model;

import lombok.*;
import ru.ddc.b2bcolab.model.Customer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatRoom {
    private Long id;
    private Customer customer1;
    private Customer customer2;
    private String name;
}
