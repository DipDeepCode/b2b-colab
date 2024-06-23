package ru.ddc.b2bcolab.controller;

import com.stripe.exception.StripeException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.b2bcolab.controller.payload.PaymentChargeRequest;
import ru.ddc.b2bcolab.controller.payload.PaymentCheckoutResponse;
import ru.ddc.b2bcolab.service.PaymentService;

@Tag(name = "PaymentController", description = "Контроллер оплаты")
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"http://localhost:8080", "http://localhost:3000", "https://w2w-project-site.vercel.app"},
        allowCredentials = "true")
public class PaymentController {
    private final PaymentService paymentService;

    @Operation(summary = "Получить данные тарифа для оплаты")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(schema = @Schema(implementation = PaymentCheckoutResponse.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<?> checkout(@RequestParam Long tariffPlanId) {
        return ResponseEntity.ok(paymentService.checkout(tariffPlanId));
    }

    @Operation(summary = "Оплатить тариф")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный запрос",
                    content = @Content(schema = @Schema(implementation = PaymentChargeRequest.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Доступ к запрошенному ресурсу запрещен",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> charge(@RequestBody PaymentChargeRequest paymentChargeRequest) throws StripeException {
        return ResponseEntity.ok(paymentService.charge(paymentChargeRequest));
    }

    @ExceptionHandler(StripeException.class)
    public ResponseEntity<String> handleError(StripeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
