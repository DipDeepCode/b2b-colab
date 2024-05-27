package ru.ddc.b2bcolab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import ru.ddc.b2bcolab.controller.payload.DeleteUserRequest;
import ru.ddc.b2bcolab.controller.payload.LoginRequest;
import ru.ddc.b2bcolab.controller.payload.RegisterRequest;
import ru.ddc.b2bcolab.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "AuthController", description = "Контроллер для управления пользователями")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Operation(summary = "Регистрация пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешная регистрация"),
            @ApiResponse(responseCode = "400", description = "Запрос не прошел валидацию")
    })
    @PostMapping("/auth/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody RegisterRequest request,
                                        BindingResult bindingResult) throws BindException {
        userService.createUser(request, bindingResult);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Вход по логину и паролю")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный вход"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен, указан неверный логин или пароль"
            )
    })
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Смена пароля")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Смена пароля успешна"),
            @ApiResponse(responseCode = "403", description = "Доступ к запрошенному ресурсу запрещен")
    })
    @PatchMapping("/auth")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request,
                                            BindingResult bindingResult) throws BindException {
        userService.changePassword(request, bindingResult);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удаление пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Удаление успешно"),
            @ApiResponse(responseCode = "403", description = "Доступ к запрошенному ресурсу запрещен")
    })
    @DeleteMapping("/auth")
    public ResponseEntity<?> deleteUser(@RequestBody DeleteUserRequest request) {
        userService.deleteUser(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Выход из сервиса")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный выход")
    })
    @PostMapping("/logout")
    public void fakeLogout() {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
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
