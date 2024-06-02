package ru.ddc.b2bcolab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {


    @Operation(summary = "Тест доступа",
            description = "Любой пользователь может получить доступ к эндпоинту")
    @ApiResponse(
            responseCode = "200",
            description = "Успешный запрос") //TODO добавить content
    @CrossOrigin
    @GetMapping("/permitAll")
    public String when_any_request_then_test_ok() {
        return "test ok";
    }

    @Operation(summary = "Тест права доступа для ROLE_USER",
            description = "Только пользователь прошедший вход в сервис и имеющий ROLE_USER может получить доступ к эндпоинту")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос"), //TODO добавить content
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен, пользователь не аутентифицирован")
    })
    @CrossOrigin
    @GetMapping("/roleUserOnly")
    public String when_user_request_then_test_ok() {
        return "test ok";
    }
}
