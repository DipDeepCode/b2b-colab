package ru.ddc.b2bcolab.model;

import lombok.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class Like {
    private long id;
    private LocalDateTime timestamp;
    private Long fromBrandId;
    private Long toBrandId;
}
