package ru.ddc.b2bcolab.model;

import lombok.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Collaboration {
    private long id;
    private long brandId1;
    private long brandId2;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
