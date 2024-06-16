package ru.ddc.b2bcolab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    private Long id;
    private String customerPhoneNumber;
    private String name;
}
