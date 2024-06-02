package ru.ddc.b2bcolab.controller.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCustomerRequest {

    @NotNull(message = "should be not null")
    @NotBlank(message = "should be not empty")
    private String phoneNumber;

    @NotNull(message = "should be not null")
    @NotBlank(message = "should be not empty")
    @Email
    private String email;

    @NotNull(message = "should be not null")
    @NotBlank(message = "should be not empty")
    @Size(min = 3, max = 120, message = "the size should be in the range from {min} to {max}")
    private String password;
}
