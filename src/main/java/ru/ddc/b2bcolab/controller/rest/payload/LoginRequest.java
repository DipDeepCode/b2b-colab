package ru.ddc.b2bcolab.controller.rest.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotNull
    @NotBlank(message = "should be not empty")
    private String username;

    @NotNull
    @NotBlank(message = "should be not empty")
    private String password;
}
