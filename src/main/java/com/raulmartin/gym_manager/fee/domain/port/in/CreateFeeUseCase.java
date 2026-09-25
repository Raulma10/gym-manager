package com.raulmartin.gym_manager.fee.domain.port.in;

import java.math.BigDecimal;

import com.raulmartin.gym_manager.fee.domain.model.Fee;

public interface CreateFeeUseCase {
    
    Fee createFee(String name, BigDecimal price, int weeklySessions);
}
