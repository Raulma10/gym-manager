package com.raulmartin.gym_manager.fee.domain.port.in;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.raulmartin.gym_manager.fee.domain.model.Fee;
import com.raulmartin.gym_manager.fee.domain.model.FeeStatus;

public interface SearchFeesUseCase {
    Page<Fee> searchFee(String name, BigDecimal price, Integer weeklySessions, FeeStatus status, Pageable pageable);
}
