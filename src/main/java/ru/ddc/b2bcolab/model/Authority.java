package ru.ddc.b2bcolab.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Authority {
    private String phoneNumber;
    private Role role;


    public Authority build() {
        return new Authority(this.phoneNumber, this.role);
    }
}
