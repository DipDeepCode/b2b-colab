package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import ru.ddc.b2bcolab.controller.rest.payload.ChangePasswordRequest;
import ru.ddc.b2bcolab.controller.rest.payload.DeleteUserRequest;
import ru.ddc.b2bcolab.controller.rest.payload.RegisterRequest;
import ru.ddc.b2bcolab.utils.RegistrationRequestValidator;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JdbcUserDetailsManager jdbcUserDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationRequestValidator registrationRequestValidator;

    public void createUser(RegisterRequest request, BindingResult bindingResult) throws BindException {
        registrationRequestValidator.validate(request, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        UserDetails userDetails = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .authorities("ROLE_USER")
                .passwordEncoder(passwordEncoder::encode)
                .build();
        jdbcUserDetailsManager.createUser(userDetails);
    }

    public void deleteUser(DeleteUserRequest request) {
        jdbcUserDetailsManager.deleteUser(request.getUsername());
    }

    public void changePassword(ChangePasswordRequest request, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        jdbcUserDetailsManager.changePassword(request.getOldPassword(), passwordEncoder.encode(request.getNewPassword()));
    }
}