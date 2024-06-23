package ru.ddc.b2bcolab.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TariffPlan {

    private Long id;

    @NotNull
    @NotBlank(message = "should be not empty")
    @Size(min = 3, max = 20, message = "the size should be in the range from {min} to {max}")
    private String name;

    @NotNull
    @NotBlank(message = "should be not empty")
    @Size(min = 3, max = 63, message = "the size should be in the range from {min} to {max}")
    private String description;

    @NotNull
    private BigDecimal pricePerMonth;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
