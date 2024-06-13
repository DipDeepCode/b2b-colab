package ru.ddc.b2bcolab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.b2bcolab.model.Customer;
import ru.ddc.b2bcolab.service.CustomerService;

import java.util.List;
import java.util.Optional;

@Tag(name = "CustomerController", description = "Контроллер для работы с клиентами")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Получить всех клиентов")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный запрос"),
            @ApiResponse(responseCode = "204", description = "Клиенты не найдены")
    })
    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Customer> customers = customerService.findAllCustomer();
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(customers);
        }
    }

    @Operation(summary = "Получить клиента по номеру телефона")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Клиент найден"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    @GetMapping("/{phoneNumber}")
    public ResponseEntity<?> findCustomerByPhoneNumber(@PathVariable String phoneNumber) {
        Optional<Customer> customer = customerService.findCustomerByPhoneNumber(phoneNumber);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Получить клиента по электронной почте")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Клиент найден"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    @GetMapping("/{email}")
    public ResponseEntity<?> findCustomerByEmail(@PathVariable String email) {
        Optional<Customer> customer = customerService.findCustomerByEmail(email);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Сохранить нового клиента")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Клиент успешно сохранен")
    })
    @PostMapping
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.ok(savedCustomer);
    }

    @Operation(summary = "Обновить информацию о клиенте")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Информация о клиенте успешно обновлена"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    @PutMapping("{phoneNumber}")
    public ResponseEntity<?> updateCustomer(@PathVariable String phoneNumber, @RequestBody Customer customer) {
        Integer updatedCustomer = customerService.updateCustomer(customer);
        return ResponseEntity.ok(updatedCustomer);
    }


    @Operation(summary = "Удалить клиента")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Клиент успешно удален"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    @DeleteMapping("{phoneNumber}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String phoneNumber) {
        int deletedCount = customerService.deleteCustomer(phoneNumber);
        if (deletedCount > 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
