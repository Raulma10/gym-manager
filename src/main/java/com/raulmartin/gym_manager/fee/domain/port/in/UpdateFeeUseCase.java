package com.raulmartin.gym_manager.fee.domain.port.in;

import java.math.BigDecimal;
import java.util.UUID;

import com.raulmartin.gym_manager.fee.domain.model.Fee;

public interface UpdateFeeUseCase {
    Fee updateFee(UUID id, String name, BigDecimal price, int weeklySessions);
}
