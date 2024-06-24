package ru.ddc.b2bcolab.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicDiscussion {
    private Long id;
    private String name;
    private Long brandId;
}
