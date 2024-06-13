package ru.ddc.b2bcolab.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Image {
    private Long id;
    private Long brandId;
    private String filename;
    private ImageType imageType;
}
