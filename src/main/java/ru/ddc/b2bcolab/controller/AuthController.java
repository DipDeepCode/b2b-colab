package ru.ddc.b2bcolab.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.ddc.b2bcolab.controller.payload.ChangePasswordRequest;
import ru.ddc.b2bcolab.controller.payload.DeleteRequest;
import ru.ddc.b2bcolab.controller.payload.LoginRequest;
import ru.ddc.b2bcolab.controller.payload.RegisterRequest;
import ru.ddc.b2bcolab.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody RegisterRequest request,
                                        BindingResult bindingResult) throws BindException {
        userService.createUser(request, bindingResult);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/auth")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request,
                                            BindingResult bindingResult) throws BindException {
        userService.changePassword(request, bindingResult);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/auth")
    public ResponseEntity<?> deleteUser(@RequestBody DeleteRequest request) {
        userService.deleteUser(request);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<?> handleBindingException(BindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return ResponseEntity.badRequest().body(errors);
    }
}
