package com.raulmartin.gym_manager.fee.infrastructure.in.web.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateFeeRequest(

    @NotBlank
    String name,

    @NotNull
    @DecimalMin("0.0")
    BigDecimal price,

    @Min(0)
    @Max(2)
    int weeklySessions

) {
}
