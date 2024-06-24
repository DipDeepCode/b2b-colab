package ru.ddc.b2bcolab.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TargetAudienceCategory {
    private Long id;
    private String category;
    private Long brandId;
}