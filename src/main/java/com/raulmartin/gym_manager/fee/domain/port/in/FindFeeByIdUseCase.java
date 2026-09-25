package com.raulmartin.gym_manager.fee.domain.port.in;

import java.util.Optional;
import java.util.UUID;

import com.raulmartin.gym_manager.fee.domain.model.Fee;

public interface FindFeeByIdUseCase {
    Optional<Fee> findById(UUID id);
}
