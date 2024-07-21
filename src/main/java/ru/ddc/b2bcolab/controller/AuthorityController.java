package ru.ddc.b2bcolab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.b2bcolab.model.Authority;
import ru.ddc.b2bcolab.service.AuthorityService;

import java.util.List;

@RestController
// @RequestMapping("/authorities")
@RequiredArgsConstructor
@Tag(name = "Authority Controller", description = "Контроллер для работы с правами доступа")
public class AuthorityController {
    private final AuthorityService authorityService;

    @Operation(summary = "Сохранение права доступа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Право доступа успешно сохранено"),
            @ApiResponse(responseCode = "400", description = "Неверный запрос")
    })
    @PostMapping
    public ResponseEntity<Authority> saveAuthority(@RequestBody Authority authority) {
        Authority savedAuthority = authorityService.save(authority);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthority);
    }

    @Operation(summary = "Получение всех прав доступа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список прав доступа успешно получен"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/customers/authorities")
    public ResponseEntity<List<Authority>> getAllAuthorities() {
        List<Authority> authorities = authorityService.getAllAuthorities();
        return ResponseEntity.ok(authorities);
    }

    @Operation(summary = "Получение права доступа по номеру телефона")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Право доступа успешно получено"),
            @ApiResponse(responseCode = "404", description = "Право доступа не найдено"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/customers/{phoneNumber}/authorities")
    public ResponseEntity<Authority> getAuthorityByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        return authorityService.getAuthorityByPhoneNumber(phoneNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Обновление права доступа по номеру телефона")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Право доступа успешно обновлено"),
            @ApiResponse(responseCode = "404", description = "Право доступа не найдено"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PutMapping("{phoneNumber}")
    public ResponseEntity<Authority> updateAuthority(@PathVariable String phoneNumber, @RequestBody Authority authority) {
        if (!authorityService.existsById(phoneNumber)) {
            return ResponseEntity.notFound().build();
        }
        authority.setPhoneNumber(phoneNumber);
        Authority updatedAuthority = authorityService.save(authority);
        return ResponseEntity.ok(updatedAuthority);
    }

    @Operation(summary = "Удаление права доступа по номеру телефона")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Право доступа успешно удалено"),
            @ApiResponse(responseCode = "404", description = "Право доступа не найдено"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @DeleteMapping("{phoneNumber}")
    public ResponseEntity<Void> deleteAuthority(@PathVariable String phoneNumber) {
        if (!authorityService.existsById(phoneNumber)) {
            return ResponseEntity.notFound().build();
        }
        authorityService.deleteAuthority(phoneNumber);
        return ResponseEntity.noContent().build();
    }
}