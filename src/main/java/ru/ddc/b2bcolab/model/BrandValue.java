package ru.ddc.b2bcolab.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandValue {
    private Long id;
    private String value;
    private Long brandId;
}
