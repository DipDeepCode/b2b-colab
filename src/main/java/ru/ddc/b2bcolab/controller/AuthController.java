package ru.ddc.b2bcolab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.ddc.b2bcolab.controller.payload.ChangePasswordRequest;
import ru.ddc.b2bcolab.controller.payload.LoginRequest;
import ru.ddc.b2bcolab.controller.payload.RegisterCustomerRequest;
import ru.ddc.b2bcolab.service.CustomerService;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "AuthController", description = "Контроллер аутентификации и авторизации")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final CustomerService customerService;

    @Operation(summary = "Предрегистрация пользователя")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешная предрегистрация"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Запрос не прошел валидацию")
    })
    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody RegisterCustomerRequest request,
                                            BindingResult bindingResult,
                                            HttpServletRequest httpServletRequest,
                                            HttpServletResponse httpServletResponse) throws BindException {
        customerService.createCustomer(request, bindingResult, httpServletRequest, httpServletResponse);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Регистрация пользователя")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешная регистрация"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен, указан неверный проверочный код")
    })
    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestParam String passcode,
                                              HttpServletRequest httpServletRequest,
                                              HttpServletResponse httpServletResponse) {
        customerService.registerCustomer(passcode, httpServletRequest, httpServletResponse);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Вход по номеру телефона и паролю или по почте и паролю")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный вход"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен, указан неверный логин или пароль"
            )
    })
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse) {
        customerService.login(request, httpServletRequest, httpServletResponse);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Смена пароля")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Смена пароля успешна"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен")
    })
    @CrossOrigin
    @PatchMapping("/changePass")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request,
                                            BindingResult bindingResult,
                                            HttpServletRequest httpServletRequest,
                                            HttpServletResponse httpServletResponse) throws BindException {
        customerService.changePassword(request, bindingResult, httpServletRequest, httpServletResponse);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Выход из сервиса")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный выход")
    })
    @CrossOrigin
    @PostMapping("/logout")
    public void fakeLogout() {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

    @Operation(summary = "Получить authorities для пользователя в текущей сессии")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос"), //TODO добавить content
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен, пользователь не аутентифицирован")
    })
    @CrossOrigin
    @GetMapping("/authorities")
    public ResponseEntity<?> getAuthorities() {
        return ResponseEntity.ok(customerService.getAuthorities());
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
