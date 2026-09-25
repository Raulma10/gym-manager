package com.raulmartin.gym_manager.fee.infrastructure.in.web.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.raulmartin.gym_manager.fee.domain.model.Fee;
import com.raulmartin.gym_manager.fee.domain.model.FeeStatus;

public record FeeResponse(
        UUID id,
        String name,
        BigDecimal price,
        int weeklySessions,
        FeeStatus feeStatus
) {
    public static FeeResponse from(Fee fee) {
        return new FeeResponse(
                fee.getId(),
                fee.getName(),
                fee.getPrice(),
                fee.getWeeklySessions(),
                fee.getFeeStatus()
        );
    }
}