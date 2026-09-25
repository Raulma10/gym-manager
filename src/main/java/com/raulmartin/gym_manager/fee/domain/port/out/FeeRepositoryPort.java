package com.raulmartin.gym_manager.fee.domain.port.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.raulmartin.gym_manager.fee.domain.model.Fee;

public interface FeeRepositoryPort {
    Fee save(Fee fee);
    Optional<Fee> findById(UUID id);
    List<Fee> findAll();
    void deleteById(UUID id);
}
