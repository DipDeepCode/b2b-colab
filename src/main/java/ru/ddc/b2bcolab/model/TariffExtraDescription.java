package ru.ddc.b2bcolab.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TariffExtraDescription {

    private Long id;

    @NotNull
    private Long tariffPlanId;

    @NotBlank(message = "should be not empty")
    @Size(min = 3, max = 63, message = "the size should be in the range from {min} to {max}")
    private String description;

    @NotNull
    private Long orderBy;

    @NotNull
    private Boolean is_active;

    @NotNull
    private Boolean is_highlighted;
}
