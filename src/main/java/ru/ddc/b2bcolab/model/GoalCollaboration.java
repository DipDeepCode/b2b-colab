package ru.ddc.b2bcolab.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoalCollaboration {
    private Long id;
    private String name;
    private Long brandId;
}
