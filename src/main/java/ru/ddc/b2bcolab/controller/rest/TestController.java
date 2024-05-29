package ru.ddc.b2bcolab.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Operation(summary = "Тест прав доступа",
            description = "Только пользователь прошедший вход в сервис может получить доступ к эндпоинту")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешно",
                    content = @Content(
                            schema = @Schema(implementation = UsernamePasswordAuthenticationToken.class),
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(responseCode = "403", description = "Пользователь не аутентифицирован")
    })
    @GetMapping
    public ResponseEntity<?> test1(Authentication authentication) {
        return ResponseEntity.ok().body(authentication);
    }
}
